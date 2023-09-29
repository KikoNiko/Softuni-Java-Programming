package Exc;

import java.io.*;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class _06_WordCount {

    public static void main(String[] args) throws FileNotFoundException {

        String wordsInput = "C:\\Users\\Kiko\\Desktop\\CodingStuff\\JavaStudy\\SoftUni Advanced-Java\\Streams-Files-And-Directories\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\words.txt";
        String textInput = "C:\\Users\\Kiko\\Desktop\\CodingStuff\\JavaStudy\\SoftUni Advanced-Java\\Streams-Files-And-Directories\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\text.txt";
        String results = "C:\\Users\\Kiko\\Desktop\\CodingStuff\\JavaStudy\\SoftUni Advanced-Java\\Streams-Files-And-Directories\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\results.txt";

        Map<String, Integer> wordsMap = new TreeMap<>();

        Scanner sc1 = new Scanner(new File(wordsInput));
        Scanner sc2 = new Scanner(new File(textInput));

        while (sc1.hasNext()) {
            wordsMap.put(sc1.next(), 0);
        }

        while (sc2.hasNext()) {
            String word = sc2.next();
            if (wordsMap.containsKey(word)) {
                wordsMap.put(word, wordsMap.get(word) + 1);
            }
        }

        try (FileWriter fileWriter = new FileWriter(results);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            wordsMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEach(e -> printWriter.printf("%s - %d%n", e.getKey(), e.getValue()));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
