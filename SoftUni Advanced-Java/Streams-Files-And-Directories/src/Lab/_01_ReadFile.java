package Lab;

import java.io.FileInputStream;
import java.io.IOException;

public class _01_ReadFile {

    public static void main(String[] args) throws IOException {

        String path = "04. Java-Advanced-Files-and-Streams-Lab-Resources/input.txt";

        FileInputStream inputStream = new FileInputStream(path);

        int oneByte = inputStream.read();

        while (oneByte >= 0) {
            System.out.print(Integer.toBinaryString(oneByte) + " ");
            oneByte = inputStream.read();
        }

        inputStream.close();
    }
}
