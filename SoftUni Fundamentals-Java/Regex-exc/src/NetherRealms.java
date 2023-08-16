import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NetherRealms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> demonNames = Arrays
                        .stream(scanner.nextLine()
                        .split("\\s*,\\s*"))
                        .collect(Collectors.toList());

        for (String name : demonNames) {

            int demonHP = 0;
            String healthRegex = "[^0-9\\+\\-*/.]";
            Pattern patternHealth = Pattern.compile(healthRegex);
            Matcher matcherHealth = patternHealth.matcher(name);
            while (matcherHealth.find()) {
                String matchedChars = matcherHealth.group();
                for (char c : matchedChars.toCharArray()) {
                    demonHP += c;
                }
            }

            double demonDamage = 0.0;
            String damageRegex = "\\+?\\-?\\d+\\.?\\d*";
            Pattern patternDamage = Pattern.compile(damageRegex);
            Matcher matcherDamage = patternDamage.matcher(name);
            while (matcherDamage.find()) {
                double currentDamage = Double.parseDouble(matcherDamage.group());
                demonDamage += currentDamage;
            }
            for (char c : name.toCharArray()) {
                if (c == '*') {
                    demonDamage *= 2;
                } else if (c == '/') {
                    demonDamage /= 2;
                }
            }
            //{demon name} - {health points} health, {damage points} damage
            System.out.printf("%s - %d health, %.2f damage%n", name, demonHP, demonDamage);
        }

    }
}
