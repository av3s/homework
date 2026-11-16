package mbank.operation.commands;

import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements OperationCommand {
    @Override
    public void execute() {
        System.out.println("""
                                Available commands:
                                USER_CREATE, SHOW_ALL_USERS, ACCOUNT_CREATE, ACCOUNT_DEPOSIT, ACCOUNT_WITH_DROW, ACCOUNT_TRANSFER, ACCOUNT_CLOSE, HELP, EXIT""");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.HELP;
    }
}