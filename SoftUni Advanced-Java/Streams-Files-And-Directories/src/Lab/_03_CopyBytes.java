package Lab;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class _03_CopyBytes {

    public static void main(String[] args) throws IOException {

        String inPath = "04. Java-Advanced-Files-and-Streams-Lab-Resources/03.CopyBytesOutput.txt";
        String outPath = "04. Java-Advanced-Files-and-Streams-Lab-Resources/input.txt";
        FileInputStream inputStream = new FileInputStream(inPath);
        FileOutputStream outputStream = new FileOutputStream(outPath);

        int oneByte = 0;

        while ((oneByte = inputStream.read()) >= 0) {
            if (oneByte == 13 || oneByte == 32) {
                outputStream.write(oneByte);
            } else {
                String digits = String.valueOf(oneByte);
                for (int i = 0; i < digits.length(); i++) {
                    outputStream.write(digits.charAt(i));
                }
            }
        }

        inputStream.close();
        outputStream.close();
    }

}
