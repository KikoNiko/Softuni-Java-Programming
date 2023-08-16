package MoreExc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MirrorWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        String regex = "([@#])(?<firstWord>[A-Za-z]{3,})\\1\\1(?<secondWord>[A-Za-z]{3,})\\1";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        int pairsCount = 0;

        List<String> mirrorWords = new ArrayList<>();

        while (matcher.find()) {
            pairsCount++;
            String firstWord = matcher.group("firstWord");
            String secondWord = matcher.group("secondWord");
            boolean isValidMirrorWord = isMirrorWord(firstWord, secondWord);
            if (isValidMirrorWord) {
                mirrorWords.add(String.format("%s <=> %s", firstWord, secondWord));
            }
        }

        if (pairsCount == 0) {
            System.out.println("No word pairs found!");
        } else {
            System.out.println(pairsCount + " word pairs found!");
        }

        if (mirrorWords.size() == 0) {
            System.out.println("No mirror words!");
        } else {
            System.out.println("The mirror words are:");
            System.out.println(String.join(", ", mirrorWords));
        }

    }

    private static boolean isMirrorWord(String first, String second) {
        StringBuilder sb = new StringBuilder(second);
        String reversed = sb.reverse().toString();
        if (first.equals(reversed)) {
            return true;
        }
        return false;
    }
}
