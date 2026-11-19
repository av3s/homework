package mbank.operation.commands;

import exceptions.ValidationException;
import mbank.account.Account;
import mbank.account.AccountService;
import mbank.console.ConsoleInput;
import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class AccountWithdrawCommand implements OperationCommand {
    @Autowired
    private ConsoleInput consoleInput;
    @Autowired
    private AccountService accountService;

    @Override
    public void execute() {
        System.out.println("AccountWithDrowCommand");

        try {
            int accountId = consoleInput.readInt("\tВведите Id аккаунта > ");
            Account account = accountService.getAccount(accountId);
            double withdrawAmount = consoleInput.readDouble("\tВведите сумму списания > ");
            accountService.accountWithdraw(account, withdrawAmount);

            System.out.println(MessageFormat.format("Снято {0} со счёта {1}. Новый баланс: {2} ",
                    withdrawAmount,
                    account.getId(),
                    account.getMoneyAmmount())
            );
        } catch (
                ValidationException exception) {
            System.out.println(exception.getMessage());
        } catch (
                NumberFormatException exception) {
            System.out.println("неверный формат id аккаунта");
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }
}