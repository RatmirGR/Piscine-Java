package d03.ex03;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class FileDownloader implements Runnable{
    private final int threadId;
    private final List<URL> urlList;
    private static volatile int currIndex = 0;

    public FileDownloader(int threadId, List<URL> urlList){
        this.threadId = threadId;
        this.urlList = urlList;
    }

    @Override
    public void run() {
        int index;
        URL url;
        InputStream inputStream;
        File file;

        while (true) {
            index = getCurrentIndex();

            if (index == -1){
                break;
            }
            System.out.printf("Thread-%d start download file number %d\n", threadId, index + 1);
            url = urlList.get(index);
            try {
                inputStream = url.openStream();
                file = new File(url.getFile().substring(url.getFile().lastIndexOf("/") + 1));

                Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                inputStream.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.printf("Thread-%d finish download file number %d\n", threadId, index + 1);
        }
    }

    private synchronized int getCurrentIndex(){
        if (currIndex < urlList.size()){
            return currIndex++;
        }else{
            return -1;
        }
    }
}
