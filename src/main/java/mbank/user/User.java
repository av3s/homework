package mbank.user;

import mbank.account.Account;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String login;
    private List<Account> accountList;

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Account> getAccountList() {

       return new ArrayList<>(accountList);
    }

    public void addAccount(Account account) {
        if(account!=null){
            this.accountList.add(account);
        }

    }
}
