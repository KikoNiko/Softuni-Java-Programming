package Lab;

import java.io.*;
import java.util.Scanner;

public class _04_ExtractIntegers {

    public static void main(String[] args) throws FileNotFoundException {

        String readPath = "04. Java-Advanced-Files-and-Streams-Lab-Resources/input.txt";
        String writePath = "04. Java-Advanced-Files-and-Streams-Lab-Resources/04.ExtractIntegersOutput.txt";

        Scanner scanner = new Scanner(new FileInputStream(readPath));

        PrintWriter printer = new PrintWriter(new FileOutputStream(writePath));

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                printer.println(scanner.next());
            }
            scanner.next();
        }

        printer.close();
    }
}
