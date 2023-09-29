package Exc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class _05_LineNumbers {

    public static void main(String[] args) {
        String inputPath = "C:\\Users\\Kiko\\Desktop\\CodingStuff\\JavaStudy\\SoftUni Advanced-Java\\Streams-Files-And-Directories\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputLineNumbers.txt";
        String outputPath = "C:\\Users\\Kiko\\Desktop\\CodingStuff\\JavaStudy\\SoftUni Advanced-Java\\Streams-Files-And-Directories\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\output.txt";


        try (BufferedReader reader = Files.newBufferedReader(Path.of(inputPath));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(outputPath))) {

            String line = reader.readLine();
            int counter = 1;
            while (line != null) {
                writer.write(counter++ + ". ");
                writer.write(line);
                writer.newLine();

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
