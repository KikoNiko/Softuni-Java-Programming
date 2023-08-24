package Exc1;

import java.util.Scanner;

public class Shopping {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budget = Double.parseDouble(scanner.nextLine());
        int graphicCards = Integer.parseInt(scanner.nextLine());
        int processors = Integer.parseInt(scanner.nextLine());
        int rams = Integer.parseInt(scanner.nextLine());

        int graphicCardsPrice = 250 * graphicCards;
        double processorPrice = (0.35 * graphicCardsPrice) * processors;
        double ramsPrice = (0.10 * graphicCardsPrice) * rams;

        double totalSum = graphicCardsPrice + processorPrice + ramsPrice;

        if (graphicCards > processors) {
            totalSum -= (0.15 * totalSum);
        }
        if (budget >= totalSum) {
            System.out.printf("You have %.2f leva left!", budget - totalSum);
        } else {
            System.out.printf("Not enough money! You need %.2f leva more!", totalSum - budget);
        }
    }
}
