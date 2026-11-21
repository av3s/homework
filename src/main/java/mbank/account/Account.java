package mbank.account;

import java.text.MessageFormat;

public class Account {
    private final int id;
    private final int userId;
    private int moneyAmount;
    public Account(int id, int userId, int moneyAmount) {
        this.id = id;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Account'{'id={0}, userId={1}, moneyAmount={2}'}'", id, userId, moneyAmount);
    }
}
