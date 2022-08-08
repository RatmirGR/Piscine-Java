package d03.ex01;

public class Program {

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 1) {
            System.err.println("Error: Invalid args");
            System.exit(-1);
        }

        int count = 0;

        try {
            count = Integer.parseInt(args[0].substring("--count=".length()));
        } catch (NumberFormatException e) {
            System.err.printf("Wrong format. it's not a number: %s\n", args[0].substring("--count=".length()));
            System.exit(-1);
        }

        SyncPrinter printer = new SyncPrinter();

        Thread egg = new Thread(new EggThread(printer, count));
        Thread hen = new Thread(new HenThread(printer, count));

        egg.start();
        hen.start();
    }
}