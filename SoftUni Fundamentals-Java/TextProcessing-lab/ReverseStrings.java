import java.util.Scanner;

public class ReverseStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        while (!text.equals("end")) {
            String reversed = "";
            for (int i = text.length() - 1; i >= 0; i--) {
                reversed += text.charAt(i);
            }
            System.out.println(text + " = " + reversed);
            text = scanner.nextLine();
        }
    }
}
