package d03.ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {

    private static final int MAX_NUM_ELEM = 2000000;
    private static final int MAX_MOD_VALUE = 1000;

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 2) {
            System.err.println("Error: Invalid args");
            System.exit(-1);
        }

        int arrSize = checkArg(args[0], "--arraySize=");

        int numThreads = checkArg(args[1], "--threadsCount=");

        if ((arrSize <= 0 || arrSize > MAX_NUM_ELEM) || (numThreads <= 0 || numThreads > arrSize)){
            System.err.println("Error: " + "Invalid values");
            System.exit(1);
        }

        int[] array = generateArray(arrSize);

        int sectionSize = arrSize / numThreads;

        long sum = 0;

        for (int i = 0; i < arrSize; i++) {
            sum += array[i];
        }

        System.out.printf("Sum: %d\n", sum);

        List<Thread> threadList = new ArrayList<>(numThreads);

        int begin = 0;
        int end = 0;

        for (int i = 1; i <= numThreads; i++) {
            if (sectionSize * numThreads != arrSize){
                end = begin + sectionSize;
                if (i == numThreads){
                    end = arrSize - 1;
                }
            }else{
                if (i == 1){
                    end = sectionSize - 1;
                }else{
                    end += sectionSize;
                }
            }
            threadList.add(new Thread(new ChildTread(array, begin, end, i)));
            if (sectionSize * numThreads != arrSize){
                begin += sectionSize + 1;
            }else{
                begin += sectionSize;
            }
        }

        for (Thread thread : threadList){
            thread.start();
        }

        for (Thread thread : threadList){
            thread.join();
        }

        System.out.printf("Sum by threads: %d\n", ChildTread.sum);
    }

    private static int checkArg(String args, String flag){
        try {
            return Integer.parseInt(args.substring(flag.length()));
        } catch (NumberFormatException e) {
            System.err.printf("Wrong format. it's not a number: %s\n", args.substring(flag.length()));
            System.exit(-1);
        }
        return 0;
    }

    private static int[] generateArray(int arrSize){
        int[] array = new int[arrSize];
        Random random = new Random();

        for (int i = 0; i < arrSize; i++) {
            array[i] = random.nextInt(MAX_MOD_VALUE);
        }
        return array;
    }
}