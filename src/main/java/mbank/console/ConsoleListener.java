package mbank.console;

import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConsoleListener {
    private final Map<ConsoleOperationType, OperationCommand> commandMap;

    public ConsoleListener(List<OperationCommand> commands){
        this.commandMap = new HashMap<>();
        commands.forEach(command -> commandMap.put(command.getOperationType(), command));
    }
}
