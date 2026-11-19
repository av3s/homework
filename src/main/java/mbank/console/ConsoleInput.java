package mbank.console;

import exceptions.UnexpectedEndOfInputException;
import exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print(prompt);
        for (; ; ) {
            if (!scanner.hasNextLine()) {
                throw new UnexpectedEndOfInputException();
            }
            String currentLine = scanner.nextLine().trim();
            if (!currentLine.isEmpty()) return currentLine;
        }
    }

    public double readDouble(String prompt){
        return Double.parseDouble(readString(prompt));
    }

    public int readInt(String prompt){
        return Integer.parseInt(readString(prompt));
    }
}
