package mbank.console;

import exceptions.UnexpectedEndOfInputException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    public String reedString(String prompt){
        System.out.print(prompt);
        if(!scanner.hasNextLine()){
            throw new UnexpectedEndOfInputException();
        }


        return scanner.nextLine().trim();
    }


}
