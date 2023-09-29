package Lab;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

public class _02_WriteToFile {

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream("04. Java-Advanced-Files-and-Streams-Lab-Resources/input.txt");
        FileOutputStream outputStream = new FileOutputStream("04. Java-Advanced-Files-and-Streams-Lab-Resources/02.WriteToFileOutput.txt");

        Set<Character> punctuations = Set.of(',', '.', '!', '?');

        int oneByte = inputStream.read();

        while (oneByte >= 0) {
            char symbol = (char) oneByte;
            if (!punctuations.contains(symbol)) {
                outputStream.write(symbol);
            }

            oneByte = inputStream.read();
        }

        inputStream.close();
        outputStream.close();
    }
}
