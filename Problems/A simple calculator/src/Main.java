import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        long firstOperand = scanner.nextLong();
        String operation = scanner.next();
        long secondOperand = scanner.nextLong();
        switch (operation) {
            case "+":
                System.out.println(firstOperand + secondOperand);
                break;
            case "-":
                System.out.println(firstOperand - secondOperand);
                break;
            case "*":
                System.out.println(firstOperand * secondOperand);
                break;
            case "/":
                if (secondOperand == 0) {
                    System.out.println("Division by 0!");
                    break;
                }
                System.out.println(firstOperand / secondOperand);
                break;
            default:
                System.out.println("Unknown operator");
                break;
        }
    }
}