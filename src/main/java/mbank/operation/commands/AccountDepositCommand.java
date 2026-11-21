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
public class AccountDepositCommand implements OperationCommand {
    @Autowired
    private ConsoleInput consoleInput;
    @Autowired
    private AccountService accountService;
    @Override
    public void execute() {
        try {
            int accountId = consoleInput.readInt("\tВведите Id аккаунта > ");
            int deposit = consoleInput.readInt("\tВведите сумму пополнения > ");
            Account account = accountService.getAccount(accountId);

            accountService.accountDeposit(account, deposit);
            System.out.println(MessageFormat.format("Зачислено {0} на счёт {1}. Новый баланс: {2} ",
                    deposit,
                    account.getId(),
                    account.getMoneyAmount())
            );
        } catch (ValidationException exception) {
            System.out.println(exception.getMessage());
        }catch (NumberFormatException exception) {
            System.out.println("неверный формат id аккаунта");
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_DEPOSIT;
    }
}