package mbank.operation.commands;

import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class HelpCommand implements OperationCommand {
    @Override
    public void execute() {
        String commands  = Arrays.stream(ConsoleOperationType.values())
                .map(Enum::name)
                .collect(Collectors.joining(", "));
        System.out.println("Available commands:" + commands);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.HELP;
    }
}