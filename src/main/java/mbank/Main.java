package mbank;

import config.AppConfig;
import mbank.account.AccountProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
          AccountProperties prp= context.getBean(AccountProperties.class);
        System.out.println(prp);
        context.close();
    }
}