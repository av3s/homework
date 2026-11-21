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
        String errorMessage = "";
        boolean isIncorrectParameters = false;
        if (defaultAmount < 0) {
            errorMessage = "\nПараметр default-amount должен быть >= 0";
            isIncorrectParameters = !isIncorrectParameters;
        }
        if (transferCommission < 0) {
            errorMessage = errorMessage + "\nПараметр transfer-commission должен быть >= 0";
            if (!isIncorrectParameters) {
                isIncorrectParameters = !isIncorrectParameters;
            }
        }
        if (isIncorrectParameters) {
            throw new IllegalArgumentException(errorMessage);
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
