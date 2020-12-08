import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String integers = scanner.nextLine();
        final int target = Integer.parseInt(scanner.nextLine());

        final List<Integer> integersList = Arrays
                .stream(integers.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        final List<Integer> closestNumbers = new ArrayList<>();
        int closestDistance = -1;

        for (Integer n : integersList) {
            int distance = Math.abs(target - n);

            if (closestDistance == -1) {
                closestDistance = distance;
            }

            if (distance > closestDistance) {
                continue;
            }

            if (distance == closestDistance) {
                closestNumbers.add(n);
            }

            if (distance < closestDistance) {
                closestDistance = distance;
                closestNumbers.clear();
                closestNumbers.add(n);
            }
        }
        closestNumbers.sort((a, b) -> a - b);
        StringBuilder s = new StringBuilder();
        for (Integer n : closestNumbers) {
            s.append(n).append(" ");
        }

        System.out.println(s.toString().trim());
    }
}