package Exc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class _02_SumBytes {

    public static void main(String[] args) {

        String path = "C:\\Users\\Kiko\\Desktop\\CodingStuff\\JavaStudy\\SoftUni Advanced-Java\\Streams-Files-And-Directories\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            long sum = 0;

            while (line != null) {

                for (char c : line.toCharArray()) {
                    sum += c;
                }

                line = br.readLine();
            }
            System.out.println(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
