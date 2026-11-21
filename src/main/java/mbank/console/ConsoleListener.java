package mbank.console;

import exceptions.UnexpectedEndOfInputException;
import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ConsoleListener {
    private final Map<ConsoleOperationType, OperationCommand> commandMap;
    private final Map<String, ConsoleOperationType> stringToOperationTypeMap;
    private final ConsoleInput consoleInput;

    public ConsoleListener(List<OperationCommand> commands, ConsoleInput consoleInput) throws IllegalStateException {
        this.commandMap = new HashMap<>();
        this.consoleInput = consoleInput;
        commands.forEach(command -> commandMap.put(command.getOperationType(), command));

        for (ConsoleOperationType type : ConsoleOperationType.values()) {
            if (!commandMap.containsKey(type)) {
                throw new IllegalStateException("No such method for command: " + type);
            }
        }

        stringToOperationTypeMap = Arrays.stream(ConsoleOperationType.values())
                .collect(Collectors.toMap(
                        OperationType -> OperationType.name(),
                        OperationType -> OperationType)
                );
    }

    public void run() {
        boolean isRunning = true;
        System.out.println("MiniBank running!");
        commandMap.get(ConsoleOperationType.HELP).execute();
        do {
            try {
                String currentLine;
                do {
                    currentLine = consoleInput.readString("Enter command > ");
                } while (currentLine.isBlank());

                Optional<ConsoleOperationType> commandOpt = parse(currentLine);
                if (commandOpt.isEmpty()) {
                    System.out.println("Error: Wrong command: " + currentLine);
                }

                ConsoleOperationType command = commandOpt.orElse(ConsoleOperationType.HELP);
                commandMap.get(command).execute();
                if (command == ConsoleOperationType.EXIT) {
                    isRunning = false;
                }

            } catch (UnexpectedEndOfInputException exception) {
                System.out.println(exception.getMessage() + "До свидания!");
                isRunning = false;
            } catch (RuntimeException runtimeException) {
                System.out.println("Error: " + runtimeException.getMessage());
                System.out.println("Что-то не отловлено!!!");
            }
        } while (isRunning);
    }

    private Optional<ConsoleOperationType> parse(String line) {
        if (line == null) return Optional.empty();
        line = line.strip().toUpperCase(Locale.ROOT);
        if (line.isEmpty()) return Optional.empty();
        return Optional.ofNullable(stringToOperationTypeMap.get(line));
    }

}
