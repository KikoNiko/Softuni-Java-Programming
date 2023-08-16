import java.util.Scanner;

public class Substring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String subStr = scanner.nextLine();
        String fullStr = scanner.nextLine();

        while (fullStr.contains(subStr)) {
            fullStr = fullStr.replace(subStr, "");
        }

        System.out.println(fullStr);
    }
}
