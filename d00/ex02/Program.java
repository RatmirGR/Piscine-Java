package d00.ex02;

import java.util.Scanner;

public class Program{

    private static final int DIVIDER = 10;
    private static final int NUM_EXIT = 42;
    private static final int CHECK_NUM = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 0;
        long number;

        while (scanner.hasNextLong()){

            number = scanner.nextLong();
            if (number == NUM_EXIT){
                break;
            }
            if (isPrimeNumber(getSumNumber(number))){
                count++;
            }
        }

        System.out.println("Count of coffee-request - " + count);
    }

    private static long getSumNumber(long number){
        int result = 0;

        while (number > 0){
            result += number % DIVIDER;
            number /= DIVIDER;
        }
        result += number;
        return result;
    }

    private static boolean isPrimeNumber(long number){
        boolean flag = true;

        if (number < CHECK_NUM) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        for (int i = 2; i <= number / i; i++) {
            if (number % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}

