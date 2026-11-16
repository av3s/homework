package mbank;

import config.AppConfig;
import mbank.account.AccountProperties;
import mbank.console.ConsoleListener;
import mbank.user.User;
import mbank.user.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.getBean(ConsoleListener.class).run();
        ctx.close();
    }
}