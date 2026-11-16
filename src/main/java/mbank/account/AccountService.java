package mbank.account;

import mbank.user.User;
import mbank.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public int createAccount(int userId) {

        Optional<User> result = userService.findUserById(userId);
        if (result.isPresent()) {
            User user = result.get();
            int newAccountAmount = 0;

            if (user.isFirstAccount()) {
                newAccountAmount = accountProperties.defaultAmount;
            }
            idCounter++;
            accountMap.put(idCounter, new Account(idCounter, userId, newAccountAmount));
            userService.addAccountbyUserId(idCounter);
            return idCounter;
        } else {
            throw new RuntimeException("пользователь не найден");
        }

    }
}
//ACCOUNT_CREATE,
//ACCOUNT_DEPOSIT,
//ACCOUNT_WITHDROW,
//ACCOUNT_TRANSFER,
//ACCOUNT_CLOSE,