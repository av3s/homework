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

    private final UserService userService;
    private final AccountProperties accountProperties;
    private final Map<Integer, Account> accountMap;
    int idCounter;

    public AccountService(UserService userService, AccountProperties accountProperties) {
        this.userService = userService;
        this.accountProperties = accountProperties;
        accountMap = new HashMap<>();
        idCounter = -1;
    }

    public void accountDeposit(Account account, int depositAmount){
        requirePositive(depositAmount, "depositAmount");
        account.setMoneyAmount(account.getMoneyAmount()+depositAmount);
    }
    public void accountWithdraw(Account account, int withdrawAmount){
        requirePositive(withdrawAmount, "withdrawAmount");
       if (checkFunds(account, withdrawAmount)){
           account.setMoneyAmount(account.getMoneyAmount()-withdrawAmount);
       } else{
           throw new ValidationException(
                   MessageFormat.format("\tНедостаточно средств на счете, id={0}, баланс={1}, попытка вывода={2}.",
                           account.getId(),
                           account.getMoneyAmount(),
                           withdrawAmount)
           );
       }
    }

    public String transfer(Account sender, Account receiver, int transferAmount){
        requirePositive(transferAmount, "transferAmount");
        if (sender.getId() == receiver.getId()) {
            throw new ValidationException("\tПеревод со счёта на тот же счёт недопустим!");
        }

        int fundsForReceiver = transferAmount;
        int commission;
        String commissionMessage = "(перевод без комиссии)";

        if (sender.getUserId() != receiver.getUserId()) {
            commission = (int) Math.round(transferAmount * accountProperties.getTransferCommission());
            fundsForReceiver -= commission;
            if (fundsForReceiver <= 0) {
                throw new ValidationException("\tЗа вычетом комиссии нет средств к зачислению!");
            }
            commissionMessage = ", комиссия: " + commission + ", Зачислено: " + (transferAmount - commission);
        }
        accountWithdraw(sender, transferAmount);
        accountDeposit(receiver, fundsForReceiver);
        return MessageFormat.format("\tПеревод с аккаунта id={0} на аккаунт id={1} выполнен. Баланс: {2}{3}",
                sender.getId(),
                receiver.getId(),
                receiver.getMoneyAmount(),
                commissionMessage);
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

    public void closeAccount(Account account) throws ValidationException {
        User user = userService.getUser(account.getUserId());
        Account primaryAccount = user.getFirstAccount();
        if (primaryAccount.getId() == account.getId()){
            throw new ValidationException("Нельзя удалить основной аккаунт пользователя");
        }
        if(account.getMoneyAmount() > 0) {
            transfer(account, user.getFirstAccount(), account.getMoneyAmount());
        }
        user.removeAccountFromList(account);
        accountMap.remove(account.getId());
    }

    public Account getAccount(int accountId){
        return findAccountById(accountId)
                .orElseThrow(() -> new ValidationException("\tАккаунта с id=" + accountId + " не существует. "));
    }
    private Optional<Account> findAccountById(int accountId){
        return Optional.ofNullable(accountMap.get(accountId));
    }
    private boolean checkFunds(Account account, int amount){
        return account.getMoneyAmount() >= amount;
    }
    private static void requirePositive(int amount, String field) {
        if (amount <= 0) {
            throw new ValidationException("\tСумма операции должна быть > 0: " + field + "=" + amount);
        }
    }
}
