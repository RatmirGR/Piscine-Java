package d00.ex01;

import java.util.Scanner;

public class Program {

    private static final int CHECK_NUM = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextLong()){
            System.err.println("Error: The entered data is not a number");
            System.exit(-1);
        }

        long number = scanner.nextLong();

        if (number < CHECK_NUM) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        int countSteps = 1;
        boolean flag = true;

        for (int i = 2; i <= number / i; i++) {
            if (number % i == 0) {
                flag = false;
                break;
            }
            countSteps++;
        }
        System.out.println(flag +": " + countSteps);
    }
}