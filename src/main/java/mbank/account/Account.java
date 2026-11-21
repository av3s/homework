package mbank.account;

import java.text.MessageFormat;

public class Account {
    private int id;
    private int userId;

    public Account(int id, int userId, int moneyAmount) {
        this.id = id;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    int moneyAmount;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Account'{'id={0}, userId={1}, moneyAmount={2}'}'", id, userId, moneyAmount);
    }
}
