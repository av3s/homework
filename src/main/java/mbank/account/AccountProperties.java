package mbank.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {

    final int defaultAmount;
    final float transferCommission;

    public AccountProperties(@Value("${account.default-amount:0}") int defaultAmount,
                             @Value("${account.transfer-commission:0.0}") float transferCommission) {
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
