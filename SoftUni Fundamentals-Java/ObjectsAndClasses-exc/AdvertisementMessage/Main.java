package AdvertisementMessage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfMessages = Integer.parseInt(scanner.nextLine());
        AdMessage msg = new AdMessage();

        for (int i = 0; i < numberOfMessages; i++) {
            System.out.println(msg.getRandomMessage());
        }
    }
}
