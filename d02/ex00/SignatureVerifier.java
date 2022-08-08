package d02.ex00;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class SignatureVerifier {

    private final Map<String, int[]> mapSign;
    private String path;

    public SignatureVerifier(){
        mapSign = new HashMap<>();
    }

    public void exec(String inputFile, String outputFile){
        readFile(inputFile);
        path = outputFile;
        Scanner scanner = new Scanner(System.in);
        String fileName;

        while (true){
            fileName = scanner.nextLine();
            if (fileName.equals("42")){
                scanner.close();
                break;
            }
            checkFileFormat(fileName);
        }
    }

    private void readFile(String inputFile){
        try (FileInputStream inputStream = new FileInputStream(inputFile)){
            StringBuilder buff = new StringBuilder();
            int ch;

            do{
                ch = inputStream.read();
                if (ch == '\n' || ch == -1){
                    if (!isValidSign(buff, inputFile)) {
                        break;
                    }
                    buff = new StringBuilder();
                }else{
                    buff.append((char)ch);
                }
            }while (ch != -1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e){
            System.err.println("" + e.getMessage());
        }
    }

    private boolean isValidSign(StringBuilder s, String inputFile){
        boolean flag = true;

        String[] arrStr = s.toString().split(",");

        if (arrStr.length != 2){
            System.err.printf("Error: Invalid format file: %s\n", inputFile);
            flag = false;
        }else{
            String nameType = arrStr[0];
            String[] arrByte = arrStr[1].trim().split("\\s+");

            if (arrByte.length == 0){
                System.err.println("Error: Invalid signature format");
                flag = false;
            }else{
                int[] arrHexSign = new int[arrByte.length];
                for (int i = 0; i < arrByte.length; i++) {
                    arrHexSign[i] = Integer.parseInt(arrByte[i], 16);
                }
                mapSign.put(nameType, arrHexSign);
            }
        }
        return flag;
    }

    private void checkFileFormat(String fileName){
        try (FileInputStream inputStream = new FileInputStream(fileName)){
            List<Integer> arrayList = new ArrayList<>();
            int ch;
            do{
                ch = inputStream.read();
                if (ch != -1){
                    if (ch != 0) {
                        arrayList.add(ch);
                    }
                }
            }while (ch != -1 && ch != '\n');
            for (Map.Entry<String, int[]> entry : mapSign.entrySet()){
                int[] arrCurrent = entry.getValue();
                int size;
                if (arrCurrent.length < arrayList.size()){
                    size = arrCurrent.length;
                }else{
                   size = arrayList.size();
                }
                int i;
                for (i = 0; i < size; i++) {
                    if (arrCurrent[i] != arrayList.get(i)){
                        break;
                    }
                }
                if (i == size) {
                    System.out.println("PROCESSED");
                    writeFile(entry.getKey());
                    return;
                }
            }
            System.out.println("UNDEFINED");
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e){
            System.err.println("" + e.getMessage());
        }
    }

    private void writeFile(String value) {
        try (FileOutputStream outputStream = new FileOutputStream(path, true)) {
            outputStream.write(value.getBytes());
            outputStream.write('\n');
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("" + e.getMessage());
        }
    }
}
