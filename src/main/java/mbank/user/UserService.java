package mbank.user;

import exceptions.ValidationException;
import mbank.account.Account;
import mbank.account.AccountService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserService {
    private final Map<Integer, User> userRepositoryInMemory;

    private final AccountService accountService;
    private int userIdCounter;

    public UserService(@Lazy AccountService accountService) {
        this.userRepositoryInMemory = new HashMap<>();
        this.accountService = accountService;
        userIdCounter = -1;
    }

    public User createUser(String login) {

        if (findUserByLogin(login).isEmpty()) {

            userIdCounter++;

                User user = new User(userIdCounter, login);
                userRepositoryInMemory.put(userIdCounter, user);
                accountService.createAccount(userIdCounter);
            return user;
        } else {
            throw new ValidationException(MessageFormat.format("\tЛогин \"{0}\" уже занят!", login));
        }
    }

    Optional<User> findUserByLogin(String login) {
        if (login == null || login.isBlank()) {
            throw new ValidationException("\tЛогин не содержит ни одного значимого символа");
        }

        return userRepositoryInMemory.values().stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }

    public Optional<User> findUserById(int id) {
        return Optional.ofNullable(userRepositoryInMemory.get(id));//.get(id);
    }

    public User getUser(int userId) {
        return findUserById(userId)
                .orElseThrow(() -> new ValidationException("Пользователь id=" + userId + " не найден. "));
    }


    public List<User> getUserList() {
        return userRepositoryInMemory.values().stream().toList();
    }

    public void addAccountByUserId(int userId, Account account) {
        userRepositoryInMemory.get(userId).addAccount(account);
    }
}
