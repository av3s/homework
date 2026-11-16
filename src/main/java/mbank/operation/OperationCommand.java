package mbank.operation;

public interface OperationCommand {
    void execute();
    ConsoleOperationType getOperationType();
}
