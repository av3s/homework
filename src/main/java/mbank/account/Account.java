package mbank.account;

import java.text.MessageFormat;

public class Account {
    int id;
    int userId;

    public Account(int id, int userId, double moneyAmmount) {
        this.id = id;
        this.userId = userId;
        this.moneyAmmount = moneyAmmount;
    }

    double moneyAmmount;

    public int getUserId() {
        return userId;
    }



    public int getId() {
        return id;
    }



    public double getMoneyAmmount() {
        return moneyAmmount;
    }

    public void setMoneyAmmount(double moneyAmmount) {
        this.moneyAmmount = moneyAmmount;
    }
    @Override
    public String toString(){
        return MessageFormat.format("Account'{'id={0}, userId={1}, moneyAmount={2}'}'", id, userId, moneyAmmount );
    }
}
