package mbank.operation.commands;

import exceptions.ValidationException;
import mbank.account.Account;
import mbank.account.AccountService;
import mbank.console.ConsoleInput;
import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import mbank.user.User;
import mbank.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommand implements OperationCommand {
    @Autowired
    private ConsoleInput consoleInput;
    @Autowired
    private UserService userService;
    @Override
    public void execute() {
        String login = consoleInput.readString("Введите логин нового пользователя > ");
        try {
            User user = userService.createUser(login);
            System.out.println("\tСоздан пользователь " + user);
        } catch (
                ValidationException exception) {
            System.out.println(exception.getMessage());
        }


    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.USER_CREATE;
    }
}
