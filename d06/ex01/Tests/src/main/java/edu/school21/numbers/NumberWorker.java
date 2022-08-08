package edu.school21.numbers;

public class NumberWorker {

    private static final int CHECK_NUM = 2;
    private static final int DIVIDER = 10;

    public boolean isPrime(int number) {
        if (number < CHECK_NUM) {
            throw new IllegalNumberException("Illegal number");
        }

        boolean flag = true;

        for (int i = 2; i <= number / i; i++) {
            if (number % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public int digitsSum(int number) {
        int result = 0;

        while (number > 0){
            result += number % DIVIDER;
            number /= DIVIDER;
        }
        result += number;
        return result;
    }

    static class IllegalNumberException extends RuntimeException{
        IllegalNumberException (String msgError){
            super(msgError);
        }
    }
}
