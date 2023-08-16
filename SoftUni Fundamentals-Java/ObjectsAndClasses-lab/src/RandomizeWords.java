import java.util.Random;
import java.util.Scanner;

public class RandomizeWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().split("\\s+");

        Random rnd = new Random();

        for (int i = 0; i < words.length; i++) {
            int rndIndex1 = rnd.nextInt(words.length);
            int rndIndex2 = rnd.nextInt(words.length);
            String oldWord = words[rndIndex1];
            words[rndIndex1] = words[rndIndex2];
            words[rndIndex2] = oldWord;
        }
        System.out.println(String.join(System.lineSeparator(), words));
    }
}
