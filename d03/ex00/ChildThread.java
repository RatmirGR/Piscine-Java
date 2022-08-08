package d03.ex00;

public class ChildThread implements Runnable{
    private final int count;
    private final String name;

    public ChildThread(String name, int count){
        this.name = name;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(name);
        }
    }
}
