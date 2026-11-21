package mbank.user;

import exceptions.ValidationException;
import mbank.account.Account;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class User {
    private final int id;
    private final String login;
    private final List<Account> accountList;

    public User(int id, String login) {
        this.id = id;
        this.login = login;
        this.accountList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Account getFirstAccount() {
        try {
            return accountList.getFirst();
        } catch (NoSuchElementException exception) {
            throw new ValidationException("У пользователя id=" + getId() + "нет ни одного аккаунта!!!");
        }
    }

    public boolean isFirstAccount() {
        return accountList.isEmpty();
    }

    public void removeAccountFromList(Account account) {
        if (getFirstAccount().getId() != account.getId()) {
            accountList.remove(account);
        } else {
            throw new ValidationException("Не возможно удалить первый аккаунт аккаунт!");
        }
    }

    public void addAccount(Account account) {
        if (account == null) {
            throw new NullPointerException();
        }
        accountList.add(account);
    }

    @Override
    public String toString() {
        String accounts = accountList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        return MessageFormat.format("User'{'id={0}, login=''{1}'', accounts=[{2}]'}'",
                id,
                login,
                accounts);
    }
}

