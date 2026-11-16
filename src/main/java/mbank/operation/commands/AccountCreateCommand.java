package mbank.operation.commands;

import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import org.springframework.stereotype.Component;

@Component
public class AccountCreateCommand implements OperationCommand {
    @Override
    public void execute() {
        System.out.println("AccountCreateCommand");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }
}