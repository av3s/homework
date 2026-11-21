package mbank.operation.commands;

import exceptions.ValidationException;
import mbank.account.Account;
import mbank.account.AccountService;
import mbank.console.ConsoleInput;
import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import mbank.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountCloseCommand implements OperationCommand {
    @Autowired
    private ConsoleInput consoleInput;
    @Autowired
    private AccountService accountService;

    @Override
    public void execute() {
        try {
            int accountId = consoleInput.readInt("\tВведите id удаляемого аккаунта > ");
            Account account = accountService.getAccount(accountId);
            accountService.closeAccount(account);
            System.out.println("\tАккаунт id=" + account.getId() + " удалён");
        } catch (ValidationException exception) {
            System.out.println(exception.getMessage());
        } catch (NumberFormatException exception) {
            System.out.println("\tНеверный формат id аккаунта");
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}