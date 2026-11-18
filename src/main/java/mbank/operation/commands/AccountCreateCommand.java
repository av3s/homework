package mbank.operation.commands;

import exceptions.ValidationException;
import mbank.account.Account;
import mbank.account.AccountService;
import mbank.console.ConsoleInput;
import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountCreateCommand implements OperationCommand {
    @Autowired
    ConsoleInput consoleInput;
    @Autowired
    AccountService accountService;
    @Override
    public void execute() {
        try {
            int userId = consoleInput.readInt("Введите id пользователя > ");
            Account account = accountService.createAccount(userId);
            System.out.println("\t" + account.toString());
        } catch (ValidationException exception) {
            System.out.println(exception.getMessage());
        } catch (NumberFormatException exception){
            System.out.println("Неверный формат ID пользователя");
        }

    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }
}