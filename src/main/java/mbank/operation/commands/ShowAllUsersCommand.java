package mbank.operation.commands;

import mbank.operation.ConsoleOperationType;
import mbank.operation.OperationCommand;
import mbank.user.User;
import mbank.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowAllUsersCommand implements OperationCommand {

    @Autowired
    private UserService userService;
    @Override
    public void execute() {
        List<User> userList = userService.getUserList();
        if(userList.isEmpty()){
            System.out.println("\tВ системе нет пользователей!");
        }else {
            System.out.println(
                    userList.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(",\n\t", "\t", ""))
            );
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS;
    }
}