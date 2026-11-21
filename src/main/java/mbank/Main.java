package mbank;

import config.AppConfig;
import mbank.console.ConsoleListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

            ctx.getBean(ConsoleListener.class).run();

        ctx.close();
    }
}