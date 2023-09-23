import java.util.ArrayDeque;
import java.util.Scanner;

public class _05_PrinterQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        ArrayDeque<String> printerQueue = new ArrayDeque<>();

        while (!input.equals("print")) {
            if (input.equals("cancel")) {
                String firstFile = printerQueue.poll();
                if (firstFile == null) {
                    System.out.println("Printer is on standby");
                } else {
                    System.out.println("Canceled " + firstFile);
                }
            } else {
                printerQueue.offer(input);
            }

            input = scanner.nextLine();
        }

        while (!printerQueue.isEmpty()) {
            System.out.println(printerQueue.pollFirst());
        }
    }
}
