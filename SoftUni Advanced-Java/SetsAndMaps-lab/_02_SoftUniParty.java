import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class _02_SoftUniParty {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String guest = scanner.nextLine();

        Set<String> vip = new TreeSet<>();
        Set<String> regular = new TreeSet<>();

        while (!"PARTY".equals(guest)) {

            if (Character.isDigit(guest.charAt(0))) {
                vip.add(guest);
            } else {
                regular.add(guest);
            }

            guest = scanner.nextLine();
        }

        guest = scanner.nextLine();
        while (!"END".equals(guest)) {
            vip.remove(guest);
            regular.remove(guest);
            
            guest = scanner.nextLine();
        }

        System.out.println(vip.size() + regular.size());

        for (String g : vip) {
            System.out.println(g);
        }

        for (String g : regular) {
            System.out.println(g);
        }
    }
}
