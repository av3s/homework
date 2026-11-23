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
public class AccountTransferCommand implements OperationCommand {
    @Autowired
    private ConsoleInput consoleInput;
    @Autowired
    private AccountService accountService;

    @Override
    public void execute() {
        int accountSenderId = -1;
        int accountReceiverId = -1;
        try {
            accountSenderId = consoleInput.readInt("\tВведите Id аккаунта отправителя > ");
            accountReceiverId = consoleInput.readInt("\tВведите Id аккаунта получателя > ");
//            if (accountReceiverId == accountSenderId) {
//                System.out.println("\tАккаунт получателя и аккаунт отправителя один и тот же аккаунт.");
//                return;
//            }
        } catch (NumberFormatException exception) {
            System.out.println("Неверный формат ID аккаунта");
            return;
        }
        try {
            int transferAmount = consoleInput.readInt("\tВведите сумму перевода > ");
            Account sender = accountService.getAccount(accountSenderId);
            Account receiver = accountService.getAccount(accountReceiverId);
            System.out.println(accountService.transfer(sender, receiver, transferAmount));
        } catch (ValidationException exception) {
            System.out.println(exception.getMessage());
        } catch (NumberFormatException exception) {
            System.out.println("Неверный формат суммы перевода");
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }
}