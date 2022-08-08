package d01.ex05;

public class Program {
    public static void main(String[] args) {
        Menu.Mode mode = Menu.Mode.PRODUCTION;

        if (args.length == 1 && (args[0].equals("--profile=dev"))) {
            mode = Menu.Mode.DEV;
        }
        TransactionsService service = new TransactionsService();
        Menu menu = new Menu(service, mode);
        menu.launch();
    }
}