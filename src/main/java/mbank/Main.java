package mbank;

import config.AppConfig;
import mbank.account.AccountProperties;
import mbank.user.User;
import mbank.user.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = ctx.getBean(UserService.class);
        userService.createUser("0");
        userService.createUser("1");
        userService.createUser("2");
        Optional<User> result = userService.findUserById(3);


        System.out.println();
        ctx.close();
    }
}