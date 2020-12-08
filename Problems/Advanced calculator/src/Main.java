import java.util.Arrays;

/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        // write your code here
        long[] numbers = toLongArray(Arrays.copyOfRange(args, 1, args.length));
        switch (operator) {
            case "MAX":
                System.out.println(max(numbers));
                break;
            case "MIN":
                System.out.println(min(numbers));
                break;
            case "SUM":
                System.out.println(sum(numbers));
                break;
            default:
                System.out.println("Unknown operator");
                break;
        }
    }

    private static long[] toLongArray(String... numbers) {
        return Arrays.stream(numbers).mapToLong(Long::parseLong).toArray();
    }

    private static long sum(long... numbers) {
        return Arrays.stream(numbers).sum();
    }

    private static long max(long... numbers) {
        return Arrays.stream(numbers).max().getAsLong();
    }

    private static long min(long... numbers) {
        return Arrays.stream(numbers).min().getAsLong();
    }
}