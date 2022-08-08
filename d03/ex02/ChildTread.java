package d03.ex02;

public class ChildTread implements Runnable{
    private final int[] array;
    private final int begin;
    private final int end;
    private final int threadId;
    long localSum;
    static volatile long sum = 0;

    public ChildTread(int[] array, int begin, int end, int threadId){
        this.array = array;
        this.begin = begin;
        this.end = end;
        this.threadId = threadId;
        this.localSum = 0;
    }

    @Override
    public void run() {
        for (int i = begin; i <= end; i++) {
            synchronized (ChildTread.class){
                sum += array[i];
                localSum += array[i];
            }
        }
        System.out.printf("Thread %d: from %d to %d sum is %d\n", threadId, begin, end, localSum);
    }
}
