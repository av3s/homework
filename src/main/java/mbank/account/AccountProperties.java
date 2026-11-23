package mbank.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {

    public int getDefaultAmount() {
        return defaultAmount;
    }

    public double getTransferCommission() {
        return transferCommission;
    }

    private final int defaultAmount;
    private final double transferCommission;

    public AccountProperties(@Value("${account.default-amount}") int defaultAmount,
                             @Value("${account.transfer-commission}") double transferCommission) {

        if (defaultAmount < 0) {
            throw new IllegalArgumentException("\nПараметр default-amount должен быть >= 0");
        }
        if ((transferCommission < 0) || (transferCommission >= 1)) {
            throw new IllegalArgumentException("\nПараметр transfer-commission должен принимать значения в полуинтервале [0; 1)");
        }

        this.defaultAmount = defaultAmount;
        this.transferCommission = transferCommission;
    }

    @Override
    public String toString() {
        return "AccountProperties{" +
                "defaultAmount=" + defaultAmount +
                ", transferCommission=" + transferCommission +
                '}';
    }
}
