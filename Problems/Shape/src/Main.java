import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();
        String shape = null;
        switch (selection) {
            case 1:
                shape = "square";
                break;
            case 2:
                shape = "circle";
                break;
            case 3:
                shape = "triangle";
                break;
            case 4:
                shape = "rhombus";
                break;
            default:
                System.out.println("There is no such shape!");
                break;
        }

        if (shape != null) {
            System.out.printf("You have chosen a %s%n", shape);
        }
    }
}