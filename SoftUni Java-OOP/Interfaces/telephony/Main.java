package telephony;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> numbersToCall = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());
        List<String> sitesToVisit = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());

        Smartphone smartphone = new Smartphone(numbersToCall, sitesToVisit);

        smartphone.getNumbers()
                .forEach(n -> {
                    if (n.matches("\\d+")) {
                        System.out.println(smartphone.call() + n);
                    } else {
                        System.out.println("Invalid number!");
                    }
                });

        smartphone.getUrls()
                .forEach(u -> {
                    if (u.matches("^\\D*$")) {
                        System.out.println(smartphone.browse() + u + "!");
                    } else {
                        System.out.println("Invalid URL!");
                    }
                });
    }
}
