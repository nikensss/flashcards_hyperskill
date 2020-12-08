class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        long firstOperand = Long.parseLong(args[1]);
        long secondOperand = Long.parseLong(args[2]);

        switch (operator) {
            case "+":
                System.out.println(sum(firstOperand, secondOperand));
                break;
            case "-":
                System.out.println(subtract(firstOperand, secondOperand));
                break;
            case "*":
                System.out.println(multiply(firstOperand, secondOperand));
                break;
            default:
                System.out.println("Unknown operator");
                break;
        }
    }

    private static long sum(long a, long b) {
        return a + b;
    }

    private static long subtract(long a, long b) {
        return a - b;
    }

    private static long multiply(long a, long b) {
        return a * b;
    }
}