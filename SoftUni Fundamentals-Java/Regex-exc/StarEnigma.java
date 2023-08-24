import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarEnigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex = "@(?<planetName>[A-Za-z]+)[^@\\-!:>]*:(?<planePopulation>[0-9]+)[^@\\-!:>]*!(?<attackType>[AD])![^@\\-!:>]*->(?<soldierCount>[0-9]+)";
        Pattern pattern = Pattern.compile(regex);

        List<String> attackedPlanets = new ArrayList<>();
        List<String> destroyedPlanets = new ArrayList<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String encryptedMessage = scanner.nextLine();
            String decryptedMessage = getDecryptedMessage(encryptedMessage);

            Matcher matcher = pattern.matcher(decryptedMessage);

            while (matcher.find()) {
                String planetName = matcher.group("planetName");
                String attackType = matcher.group("attackType");
                if (attackType.equals("A")) {
                    attackedPlanets.add(planetName);
                } else if (attackType.equals("D")) {
                    destroyedPlanets.add(planetName);
                }
            }
        }
        System.out.println("Attacked planets: " + attackedPlanets.size());
        Collections.sort(attackedPlanets);
        attackedPlanets.forEach(p -> System.out.println("-> " + p));
        System.out.println("Destroyed planets: " + destroyedPlanets.size());
        Collections.sort(destroyedPlanets);
        destroyedPlanets.forEach(p -> System.out.println("-> " + p));
    }

    private static String getDecryptedMessage(String encryptedMessage) {
        StringBuilder decryptedMessage = new StringBuilder();
        int key = getDecryptionKey(encryptedMessage);
        for (char c : encryptedMessage.toCharArray()) {
            char decryptedChar = (char) (c - key);
            decryptedMessage.append(decryptedChar);
        }

        return decryptedMessage.toString();
    }

    private static int getDecryptionKey(String encryptedMessage) {
        int count = 0;
        for (char c : encryptedMessage.toCharArray()) {
            switch (c) {
                case 's':
                case 't':
                case 'a':
                case 'r':
                case 'S':
                case 'T':
                case 'A':
                case 'R':
                    count++;
                    break;
            }
        }
        return count;
    }
}
