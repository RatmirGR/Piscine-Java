package d03.ex03;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Program2 {

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 1) {
            System.err.println("Error: Invalid args");
            System.exit(-1);
        }

        int threadsCount = 0;

        try {
            threadsCount = Integer.parseInt(args[0].substring("--threadsCount=".length()));
        } catch (NumberFormatException e) {
            System.err.printf("Wrong format. it's not a number: %s\n", args[0].substring("--threadsCount=".length()));
            System.exit(-1);
        }

        List<URL> urlList = new ArrayList<>();
        String str;
        String[] tmp;

        try(BufferedReader buffReader = new BufferedReader(new FileReader("src/d03/ex03/files_urls.txt"))) {
            while (buffReader.ready()) {
                str = buffReader.readLine();
                if (str == null) {
                    continue;
                }
                tmp = str.split(" ");
                if (tmp.length != 2) {
                    continue;
                }
                urlList.add(new URL(tmp[1]));
            }
        } catch (MalformedURLException e){
            System.err.println(e.getMessage());
        } catch (IOException e){
            System.err.println("" +e.getMessage());
        }

        List<Thread> threadList = new ArrayList<>(threadsCount);

        for (int i = 1; i <= threadsCount; i++) {
            threadList.add(new Thread(new FileDownloader(i, urlList)));
        }

        for (Thread thread : threadList){
            thread.start();
        }

        for (Thread thread : threadList){
            thread.join();
        }
    }
}
