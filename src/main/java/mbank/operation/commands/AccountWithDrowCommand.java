package mbank.operation.commands;

import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import org.springframework.stereotype.Component;

@Component
public class AccountWithDrowCommand implements OperationCommand {
    @Override
    public void execute() {
        System.out.println("AccountWithDrowCommand");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITH_DROW;
    }
}