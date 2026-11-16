package mbank.operation.commands;

import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommand implements OperationCommand {
    @Override
    public void execute() {
        System.out.println("jOK");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.USER_CREATE;
    }
}
