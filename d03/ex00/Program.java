package d03.ex00;

public class Program {

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 1){
            System.err.println("Error: Invalid args");
            System.exit(-1);
        }

        int count = 0;
        try {
            count = Integer.parseInt(args[0].substring("--count=".length()));
        }catch (NumberFormatException e){
            System.err.printf("Wrong format. it's not a number: %s\n", args[0].substring("--count=".length()));
            System.exit(-1);
        }

        Thread threadEgg = new Thread(new ChildThread("Egg", count));
        Thread threadHen = new Thread(new ChildThread("Hen", count));

        threadEgg.start();
        threadHen.start();

        threadEgg.join();
        threadHen.join();

        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}
