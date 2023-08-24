import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, String> parkingMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split(" ");
            String username = data[1];
            if (data[0].equals("register")) {
                String licensePlate = data[2];
                if (!parkingMap.containsKey(username)) {
                    parkingMap.put(username, licensePlate);
                    System.out.printf("%s registered %s successfully%n", username, licensePlate);
                } else {
                    System.out.println("ERROR: already registered with plate number " + parkingMap.get(username));
                }
            } else {
                if (!parkingMap.containsKey(username)) {
                    System.out.printf("ERROR: user %s not found%n", username);
                } else {
                    parkingMap.remove(username);
                    System.out.println(username + " unregistered successfully");
                }
            }
        }

        parkingMap.forEach((key, value) -> System.out.println(key + " => " + value));
    }
}
