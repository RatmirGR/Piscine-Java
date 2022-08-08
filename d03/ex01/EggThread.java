package d03.ex01;

public class EggThread implements Runnable{
    private final SyncPrinter printer;
    private final int count;

    public EggThread(SyncPrinter printer, int count){
        this.printer = printer;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            printer.printEgg();
        }
    }
}