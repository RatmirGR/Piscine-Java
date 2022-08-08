package d00.ex00;

public class Program {

    private static final int DIVIDER = 10;

    public static void main(String[] args) {
        int number = 479598;
        int result = 0;

        result += number % DIVIDER;
        number /= DIVIDER;
        result += number % DIVIDER;
        number /= DIVIDER;
        result += number % DIVIDER;
        number /= DIVIDER;
        result += number % DIVIDER;
        number /= DIVIDER;
        result += number % DIVIDER;
        number /= DIVIDER;
        result += number;

        System.out.println(result);
    }
}