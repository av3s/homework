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

    public void accountDeposit(Account account, double depositAmount){
        account.moneyAmmount = account.moneyAmmount + depositAmount;
    }
    public void accountWithdraw(Account account, double withdrawAmount){
       if (checkFunds(account, withdrawAmount)){
           accountDeposit(account, -withdrawAmount);
       } else{
           throw new ValidationException(
                   MessageFormat.format("недостаточно средств на счете, id={0}, баланс={1}}, попытка вывода={2}}",
                           account.getId(),
                           account.moneyAmmount,
                           withdrawAmount)
           );
       }

    }
    public Account createAccount(int userId) {
        Optional<User> result = userService.findUserById(userId);
        if (result.isPresent()) {
            User user = result.get();
            double amount = 0;

            if (user.isFirstAccount()) {
                amount = accountProperties.defaultAmount;
            }
            idCounter++;
            Account account = new Account(idCounter, userId, amount ) ;
            accountMap.put(idCounter, account);
            userService.addAccountByUserId(userId, account);
            return account;
        } else {
            throw new ValidationException("\tпользователь не найден!");
        }
    }
    public Account getAccount(int accountId){
        return findAccountById(accountId)
                .orElseThrow(() -> new ValidationException("Аккаунта с id=" + accountId + " не существует. "));
    }
    private Optional<Account> findAccountById(int accountId){
        return Optional.ofNullable(accountMap.get(accountId));
    }
    private boolean checkFunds(Account account, double amount){
        return account.getMoneyAmmount() > amount;
    }
}
//ACCOUNT_CREATE,
//ACCOUNT_DEPOSIT,
//ACCOUNT_WITHDROW,
//ACCOUNT_TRANSFER,
//ACCOUNT_CLOSE,