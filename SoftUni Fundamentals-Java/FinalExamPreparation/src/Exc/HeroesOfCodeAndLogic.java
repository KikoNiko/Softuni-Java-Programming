package Exc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class HeroesOfCodeAndLogic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfHeroes = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> heroesHP = new LinkedHashMap<>();
        Map<String, Integer> heroesMP = new LinkedHashMap<>();

        for (int i = 0; i < numberOfHeroes; i++) {
            String[] tokens = scanner.nextLine().split(" ");
            String heroName = tokens[0];
            int hitPoints = Integer.parseInt(tokens[1]);
            int manaPoints = Integer.parseInt(tokens[2]);
            heroesHP.put(heroName, hitPoints);
            heroesMP.put(heroName, manaPoints);
        }

        String actions = scanner.nextLine();
        while (!actions.equals("End")) {
            String[] tokens = actions.split(" - ");
            String command = tokens[0];
            String heroName = tokens[1];
            switch (command) {
                case "CastSpell":
                    int mpNeeded = Integer.parseInt(tokens[2]);
                    String spell = tokens[3];
                    int currentMana = heroesMP.get(heroName);
                    if (currentMana >= mpNeeded) {
                        int manaLeft = currentMana - mpNeeded;
                        heroesMP.put(heroName, manaLeft);
                        System.out.printf("%s has successfully cast %s and now has %d MP!%n", heroName, spell, manaLeft);
                    } else {
                        System.out.printf("%s does not have enough MP to cast %s!%n", heroName, spell);
                    }
                    break;

                case "TakeDamage":
                    int damage = Integer.parseInt(tokens[2]);
                    String attacker = tokens[3];
                    int currentHealth = heroesHP.get(heroName);
                    if (currentHealth > damage) {
                        int hpLeft = currentHealth - damage;
                        heroesHP.put(heroName, hpLeft);
                        System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n", heroName, damage, attacker, hpLeft);
                    } else {
                        heroesHP.remove(heroName);
                        heroesMP.remove(heroName);
                        System.out.printf("%s has been killed by %s!%n", heroName, attacker);
                    }
                    break;

                case "Recharge":
                    int rechargeAmount = Integer.parseInt(tokens[2]);
                    int currentMP = heroesMP.get(heroName);
                    int rechargedMana = currentMP + rechargeAmount;
                    if (rechargedMana > 200) {
                        rechargedMana = 200;
                    }
                    System.out.printf("%s recharged for %d MP!%n", heroName, rechargedMana - currentMP);
                    heroesMP.put(heroName, rechargedMana);
                    break;

                case "Heal":
                    int healAmount = Integer.parseInt(tokens[2]);
                    int currentHP = heroesHP.get(heroName);
                    int pointsAfterHealing = currentHP + healAmount;
                    if (pointsAfterHealing > 100) {
                        pointsAfterHealing = 100;
                    }
                    System.out.printf("%s healed for %d HP!%n", heroName, pointsAfterHealing - currentHP);
                    heroesHP.put(heroName, pointsAfterHealing);
                    break;
            }
            actions = scanner.nextLine();
        }
        for (Map.Entry<String, Integer> entry : heroesHP.entrySet()) {
            System.out.println(entry.getKey());
            System.out.printf("  HP: %d%n", entry.getValue());
            System.out.printf("  MP: %d%n", heroesMP.get(entry.getKey()));
        }

    }
}
