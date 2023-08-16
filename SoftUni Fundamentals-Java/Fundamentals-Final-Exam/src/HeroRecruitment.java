import java.util.*;

public class HeroRecruitment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> heroMap = new LinkedHashMap<>();

        String input = scanner.nextLine();

        while (!input.equals("End")) {
            String[] tokens = input.split(" ");
            String command = tokens[0];
            String hero = tokens[1];
            if (command.equals("Enroll")) {
                if (heroMap.containsKey(hero)) {
                    System.out.println(hero + " is already enrolled.");
                } else {
                    heroMap.put(hero, new ArrayList<>());
                }
            } else if (command.equals("Learn")) {
                String spell = tokens[2];
                if (!heroMap.containsKey(hero)) {
                    System.out.println(hero + " doesn't exist.");
                } else {
                    if (heroMap.get(hero).contains(spell)) {
                        System.out.printf("%s has already learnt %s.%n", hero, spell);
                    } else {
                        heroMap.get(hero).add(spell);
                    }
                }
            } else if (command.equals("Unlearn")) {
                String spell = tokens[2];
                if (!heroMap.containsKey(hero)) {
                    System.out.println(hero + " doesn't exist.");
                } else {
                    if (!heroMap.get(hero).contains(spell)) {
                        System.out.printf("%s doesn't know %s.%n", hero, spell);
                    } else {
                        heroMap.get(hero).remove(spell);
                    }
                }
            }

            input = scanner.nextLine();
        }

        System.out.println("Heroes:");
        for (Map.Entry<String, List<String>> entry : heroMap.entrySet()) {
            System.out.printf("== %s: ", entry.getKey());
            System.out.println(String.join(", ", entry.getValue()));
        }

    }
}
