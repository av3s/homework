package mbank.account;

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
}
