package d00.ex03;

import java.util.Scanner;

public class Program {

    private static final String EXIT = "42";
    private static final int MAX_NUM_WEEKS = 18;
    private static final int MAX_NUM_TESTS = 5;
    private static final int ENCODING_NUM = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long minGrade;
        long packageDate = 0;
        int countWeeks = 1;

        String week = scanner.nextLine();

        while (!week.equals(EXIT) && countWeeks <= MAX_NUM_WEEKS){
            if (!week.equals("Week " + countWeeks)){
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            minGrade = findMinGrade(scanner);
            packageDate += packingData(minGrade, countWeeks);
            scanner.nextLine();
            week = scanner.nextLine();
            countWeeks++;
        }

        for (int i = 1; i < countWeeks; i++) {
            minGrade = unpackingData(packageDate, i);
            System.out.print("Week " + i + " ");
            for (int j = 0; j < minGrade; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }

    private static int findMinGrade(Scanner scanner){
        int max;

        if (!scanner.hasNextInt()){
            System.err.println("Error: The entered data is not a number");
            System.exit(-1);
        }

        int min = scanner.nextInt();

        if (min < 1 || min > 9){
            System.err.println("Error: The specified score is less than 1 or greater than 9");
            System.exit(-1);
        }

        for (int i = 0; i < MAX_NUM_TESTS - 1; i++) {

            if (!scanner.hasNextInt()){
                System.err.println("Error: The entered data is not a number");
                System.exit(-1);
            }

            max = scanner.nextInt();

            if (max < 1 || max > 9){
                System.err.println("Error: The specified score is less than 1 or greater than 9");
                System.exit(-1);
            }

            if (min > max){
                min = max;
            }
        }
        return min;
    }

    private static long packingData(long minGrade, int countWeeks){
        long bitNum = 1;

        for (int i = 0; i < countWeeks - 1; i++) {
            bitNum *= ENCODING_NUM;
        }
        return bitNum * minGrade;
    }

    private static long unpackingData(long pack, int countWeeks){
        for (int i = 0; i < countWeeks - 1; i++) {
            pack /= ENCODING_NUM;
        }
        return pack % ENCODING_NUM;
    }
}
