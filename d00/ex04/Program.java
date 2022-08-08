package d00.ex04;

import java.util.Scanner;

public class Program {

    private static final int WIDTH_HISTO = 10;
    private static final int MAX_CODE_VALUE = 65535;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        if (str.length() == 0){
            System.err.println("Error: String is empty");
            System.exit(-1);
        }

        short[] arrCountChars = new short[MAX_CODE_VALUE];
        countNumRepeatChars(str, arrCountChars);

        char[] topChars = getTopChars(arrCountChars);

        System.out.println();
        printHistogram(topChars, arrCountChars);
    }

    private static void countNumRepeatChars(String str, short[] arrCountChars){
        char[] arrChars = str.toCharArray();

        int i = 0;

        while (i < str.length()){
            arrCountChars[arrChars[i++]] += 1;
        }
    }

    private static char[] getTopChars(short[] arrCountChars){
        char[] topChars = new char[WIDTH_HISTO];

        int min;
        char codeSimbol;
        int posSimbol;

        for (int i = 0; i < MAX_CODE_VALUE; i++) {
            if (arrCountChars[i] > 0){

                min = arrCountChars[i];

                for (int j = 0; j < WIDTH_HISTO; j++) {
                    if (min > arrCountChars[topChars[j]]){
                        codeSimbol = (char)i;
                        posSimbol = j;
                        topChars = getTopSortChars(topChars, codeSimbol, posSimbol);
                        break;
                    }
                }
            }
        }

        return topChars;
    }

    private static char[] getTopSortChars(char[] topChars, char codeSimbol, int posSimbol){
        char[] tmp = new char[WIDTH_HISTO];

        int i = 0;
        while (i < posSimbol){
            tmp[i] = topChars[i];
            i++;
        }
        tmp[i] = codeSimbol;
        while (++i < WIDTH_HISTO){
            tmp[i] = topChars[i - 1];
        }
        return tmp;
    }

    private static void printHistogram(char[] topChars, short[] arrCountChars){
        int[] arrAmountHash = new int[WIDTH_HISTO];
        int heightHisto = WIDTH_HISTO;

        int maxCountChars = arrCountChars[topChars[0]];

        if (maxCountChars < heightHisto){
            heightHisto = maxCountChars;
        }

        for (int i = 0; i < WIDTH_HISTO; i++) {
            arrAmountHash[i] = arrCountChars[topChars[i]];
            if (maxCountChars > WIDTH_HISTO){
                arrAmountHash[i] = arrAmountHash[i] * 10 / maxCountChars;
            }
        }

        for (int i = 0; i < heightHisto + 2; i++) {
            for (int j = 0; j < WIDTH_HISTO; j++) {
                if (topChars[j] > 0){
                    if ((i + arrAmountHash[j]) == heightHisto){
                        System.out.printf("%3d", arrCountChars[topChars[j]]);
                    }else if ((i + arrAmountHash[j]) >= heightHisto && i != heightHisto + 1){
                        System.out.printf("%3c", '#');
                    }else if (i == heightHisto + 1){
                        System.out.printf("%3c", topChars[j]);
                    }

                    if (j != WIDTH_HISTO - 1 && topChars[j + 1] > 0){
                        System.out.printf("%c", ' ');
                    }
                }
            }
            System.out.println();
        }
    }
}
