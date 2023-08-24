package MoreExc;

import java.util.Scanner;

public class GuineaPig {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double food = Double.parseDouble(scanner.nextLine()) * 1000;
        double hay = Double.parseDouble(scanner.nextLine()) * 1000;
        double cover = Double.parseDouble(scanner.nextLine()) * 1000;
        double gpWeight = Double.parseDouble(scanner.nextLine()) * 1000;

        double dailyFood = 300;
        boolean isEnough = true;

        for (int i = 1; i <= 30; i++) {
            food -= dailyFood;
            if (food <= 0) {
                isEnough = false;
                break;
            }
            if (i % 2 == 0) {
                double hayToGive = food * 0.05;
                hay -= hayToGive;
                if (hay <= 0) {
                    isEnough = false;
                    break;
                }
            }
            if (i % 3 == 0) {
                double coverToPut = gpWeight / 3;
                cover -= coverToPut;
                if (cover <= 0) {
                    isEnough = false;
                    break;
                }
            }
        }
        if (isEnough) {
            System.out.printf("Everything is fine! Puppy is happy! Food: %.2f, Hay: %.2f, Cover: %.2f."
                    , food / 1000, hay / 1000, cover / 1000);
        } else {
            System.out.println("Merry must go to the pet store!");
        }
    }
}
