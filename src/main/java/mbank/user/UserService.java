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
    final private Map<Integer, User> userRepositoryInMemory;

    final private AccountService accountService;
    private int idCounter;

    public UserService(@Lazy AccountService accountService) {
        this.userRepositoryInMemory = new HashMap<>();
        this.accountService = accountService;
        idCounter = -1;

    }

    public User createUser(String login) {

        if (findUserByLogin(login).isEmpty()) {
            idCounter++;
            User user = new User(idCounter, login);
            userRepositoryInMemory.put(idCounter, user);
            accountService.createAccount(idCounter);
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

    public boolean isFirstAccount(int userId) {
        return userRepositoryInMemory.get(userId).getAccountList().isEmpty();
    }


    public List<User> getUserList() {
        return userRepositoryInMemory.values().stream().toList();
    }

    public void addAccountByUserId(int userId, Account account) {
        userRepositoryInMemory.get(userId).addAccount(account);
    }

    public void addAccount(int userId, Account account) {
        if (account != null) {
            userRepositoryInMemory.get(userId).addAccount(account);
        } else {
            throw new NullPointerException();
        }

    }
}
