import java.util.Scanner;

public class CharacterMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] strArr = scanner.nextLine().split(" ");
        String str1 = strArr[0];
        String str2 = strArr[1];

        System.out.println(multiplyStrings(str1, str2));

    }

    public static int multiplyStrings(String str1, String str2) {
        int sum = 0;
        int index = 0;
        int longer = Math.max(str1.length(), str2.length());
        int shorter = Math.min(str1.length(), str2.length());
        while (index != shorter) {
            sum += str1.charAt(index) * str2.charAt(index);
            index++;
        }
        int diff = longer - shorter;
        if (diff != 0) {
            if (str1.length() > str2.length()) {
                for (int i = str1.length() - 1; i >= str1.length() - diff; i--) {
                    sum += str1.charAt(i);
                }
            } else {
                for (int i = str2.length() - 1; i >= str2.length() - diff; i--) {
                    sum += str2.charAt(i);
                }
            }
        }

        return sum;
    }

}
