package mbank.console;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    public String reedString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine().trim();  //TODO: починить CTRL+D
    }


}
