package d03.ex01;

public class HenThread implements Runnable{
    private final SyncPrinter printer;
    private final int count;

    public HenThread(SyncPrinter printer, int count){
        this.printer = printer;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            printer.printHen();
        }
    }
}