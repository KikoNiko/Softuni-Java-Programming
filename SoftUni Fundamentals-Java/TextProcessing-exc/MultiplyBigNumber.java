import java.math.BigInteger;
import java.util.Scanner;

public class MultiplyBigNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String bigNum = scanner.nextLine();
        String smallNum = scanner.nextLine();

        BigInteger bN = new BigInteger(bigNum);
        BigInteger sN = new BigInteger(smallNum);

        System.out.println(bN.multiply(sN));
    }
}
