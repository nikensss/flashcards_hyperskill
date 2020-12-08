import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int a, b, n, sum = 0;
        n = scanner.nextInt();
        a = scanner.nextInt();
        b = scanner.nextInt();
        Random r = new Random(a + b);

        for (int i = 0; i < n; i++) {
            sum += r.nextInt(b - a + 1) + a;
        }

        System.out.println(sum);
    }
}