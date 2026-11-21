package mbank.account;

import exceptions.ValidationException;
import mbank.user.User;
import mbank.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class AccountService {

    final UserService userService;
    @Autowired
    private AccountProperties accountProperties;
    private final Map<Integer, Account> accountMap;
    int idCounter;

    public AccountService(UserService userService) {
        this.userService = userService;
        accountMap = new HashMap<>();
        idCounter = -1;
    }

    public void accountDeposit(Account account, int depositAmount){
        account.setMoneyAmount(account.getMoneyAmount()+depositAmount);
    }
    public void accountWithdraw(Account account, int withdrawAmount){
       if (checkFunds(account, withdrawAmount)){
           account.setMoneyAmount(account.getMoneyAmount()-withdrawAmount);
       } else{
           throw new ValidationException(
                   MessageFormat.format("\tНедостаточно средств на счете, id={0}, баланс={1}, попытка вывода={2}.",
                           account.getId(),
                           account.moneyAmount,
                           withdrawAmount)
           );
       }
    }

    public void transfer(Account sender,Account receiver, int transferAmount) throws ValidationException{
        if(checkFunds(sender, transferAmount)){
            int commission = (int) Math.round(transferAmount*accountProperties.getTransferCommission());
            String commissionMessage;
            accountWithdraw(sender,transferAmount);
            if (sender.getUserId() == receiver.getUserId()){
                    accountDeposit(receiver,transferAmount);
                    commissionMessage = "(перевод без комиссии)";
            }else {
                accountDeposit(receiver,transferAmount-commission);
                commissionMessage = ", комиссия: "+ commission + ", Зачислено: " + (transferAmount-commission);
            }
            System.out.println(MessageFormat.format("\tПеревод с аккаунта id={0} на аккаунт id={1} выполнен. Баланс: {2}{3}",
                sender.getId(),
                receiver.getId(),
                receiver.getMoneyAmount(),
                commissionMessage)
            );
        }else {
            throw new ValidationException("\tНедостаточно средств для перевода на счёте id=" + sender.getId());
        }
    }

    public Account createAccount(int userId) throws ValidationException {
        User user = userService.getUser(userId);
        int amount = 0;

        if (user.isFirstAccount()) {
            amount = accountProperties.getDefaultAmount();
        }
        idCounter++;
        Account account = new Account(idCounter, userId, amount);
        accountMap.put(idCounter, account);
        userService.addAccountByUserId(userId, account);
        return account;
    }

    public void closeAccount(Account account)throws ValidationException{
       try {
           User user = userService.getUser(account.getUserId());
           user.removeAccountFromList(account);
           transfer(account, user.getFirstAccount(), account.getMoneyAmount() );
           accountMap.remove(account.getId());
       }catch (ValidationException exception){
           System.out.println(exception.getMessage());
       }
    }

    public Account getAccount(int accountId){
        return findAccountById(accountId)
                .orElseThrow(() -> new ValidationException("\tАккаунта с id=" + accountId + " не существует. "));
    }
    private Optional<Account> findAccountById(int accountId){
        return Optional.ofNullable(accountMap.get(accountId));
    }
    private boolean checkFunds(Account account, double amount){
        return account.getMoneyAmount() >= amount;
    }
}
