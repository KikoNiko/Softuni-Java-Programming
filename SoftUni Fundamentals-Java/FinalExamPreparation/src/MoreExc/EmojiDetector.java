package MoreExc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputText = scanner.nextLine();

        long threshold = 1;
        List<String> coolEmojisList = new ArrayList<>();
        int emojisCount = 0;

        String regex = "(:{2}|\\*{2})(?<emoji>[A-Z][a-z]{2,})\\1";
        Pattern pattern = Pattern.compile(regex);

//        for (int i = 0; i < inputText.length(); i++) {
//            if (Character.isDigit(inputText.charAt(i))) {
//                byte currentDigit = Byte.parseByte((inputText.charAt(i) + ""));
//                threshold *= currentDigit;
//            }
//        }
        String digitRegex = "[0-9]";
        Pattern pattern1 = Pattern.compile(digitRegex);
        Matcher matcher1 = pattern1.matcher(inputText);
        while (matcher1.find()) {
            int digit = Integer.parseInt(matcher1.group(0));
            threshold *= digit;
        }

        Matcher matcher = pattern.matcher(inputText);
        while (matcher.find()) {
            emojisCount++;
            String emoji = matcher.group();
            long coolness = getCoolness(emoji);
            if (coolness >= threshold) {
                coolEmojisList.add(emoji);
            }
        }

        System.out.println("Cool threshold: " + threshold);
        System.out.println(emojisCount + " emojis found in the text. The cool ones are:");
        if (!coolEmojisList.isEmpty()) {
            coolEmojisList.forEach(System.out::println);
        }

    }

    private static long getCoolness(String emoji) {
        long coolnessCount = 0;
        for (char c : emoji.toCharArray()) {
            coolnessCount += c;
        }
        return coolnessCount;
    }
}
