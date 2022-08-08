package d03.ex01;

public class SyncPrinter{
    private boolean flag = false;

    public synchronized void printEgg(){
        while (flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Egg");
        flag = true;
        notify();
    }

    public synchronized void printHen(){
        while (!flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Hen");
        flag = false;
        notify();
    }
}
