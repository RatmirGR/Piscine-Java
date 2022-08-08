package d02.ex01;

import java.io.*;
import java.util.*;

public class WordAnalyzer {
    private final Set<String> mainDictionary;
    private final List<String> dictionaryOne;
    private final List<String> dictionaryTwo;
    private final List<Long> vectorOne;
    private final List<Long> vectorTwo;

    public WordAnalyzer(){
        mainDictionary = new TreeSet<>();
        this.dictionaryOne = new ArrayList<>();
        this.dictionaryTwo = new ArrayList<>();
        this.vectorOne = new ArrayList<>();
        this.vectorTwo = new ArrayList<>();
    }

    public void init(String fileNameOne, String fileNameTwo){
        readFile(fileNameOne, fileNameTwo);
        createVector(vectorOne, dictionaryOne);
        createVector(vectorTwo, dictionaryTwo);
        getResult();
        writeFile();
    }

    private void readFile(String fileNameOne, String fileNameTwo){
        try(BufferedReader fileOne = new BufferedReader(new FileReader(fileNameOne));
            BufferedReader fileTwo = new BufferedReader(new FileReader(fileNameTwo))){
            createDictionary(fileOne, dictionaryOne);
            createDictionary(fileTwo, dictionaryTwo);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void createDictionary(BufferedReader file, List<String> dictionary) throws IOException{
        StringBuilder buff = new StringBuilder();
        int ch;

        while (file.ready()){
            ch = file.read();
            if (ch != 32){
                buff.append((char)ch);
            }else{
                dictionary.add(buff.toString());
                mainDictionary.add(buff.toString());
                buff = new StringBuilder();
            }
        }
        dictionary.add(buff.toString());
        mainDictionary.add(buff.toString());
    }

    private void createVector(List<Long> vector, List<String> dictionary){
        Iterator<String> iter = mainDictionary.iterator();
        long countWord;

        while (iter.hasNext()) {
            String str = iter.next();
            countWord = 0;
            for (int i = 0; i < dictionary.size(); i++) {
                if (dictionary.get(i).compareTo(str) == 0) {
                    countWord++;
                }
            }
            vector.add(countWord);
        }
    }

    private void getResult(){
        int fractionLength;
        long numerator = 0, numOne = 0, numTwo = 0;

        for (int i = 0; i < vectorOne.size(); i++) {
            numerator += vectorOne.get(i) * vectorTwo.get(i);
            numOne += vectorOne.get(i) * vectorOne.get(i);
            numTwo += vectorTwo.get(i) * vectorTwo.get(i);
        }

        double denominator = Math.sqrt(numOne) * Math.sqrt(numTwo);
        fractionLength = 10;
        denominator = getNum(denominator, fractionLength);
        denominator = numerator / denominator;
        fractionLength = 100;
        denominator = getNum(denominator, fractionLength);
        System.out.printf("Similarity = %.2f\n", denominator);

    }

    private double getNum(double num, int m){
        num = num * m;
        num = Math.floor(num);
        num = num / m;
        return num;
    }

    private void writeFile() {
        try (BufferedWriter buff = new BufferedWriter(new FileWriter("src/d02/ex01/dictionary.txt"))) {
            for (String s : mainDictionary) {
                buff.write(s);
                buff.write('\n');
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
