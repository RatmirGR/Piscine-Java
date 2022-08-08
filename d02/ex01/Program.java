package d02.ex01;

public class Program {

    public static void main(String[] args) {
        if (args.length != 2){
            System.err.println("Wrong number of arguments");
        }else{
            WordAnalyzer wordAnalyzer = new WordAnalyzer();
            wordAnalyzer.init(args[0], args[1]);
        }
    }
}
