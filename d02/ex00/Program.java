package d02.ex00;

import java.io.IOException;

public class Program {
    private static final String SIGNATURE_FILE = "src/d02/ex00/signatures.txt";
    private static final String RESULT_FILE = "src/d02/ex00/result.txt";

    public static void main(String[] args) throws IOException {
        SignatureVerifier signVerifier = new SignatureVerifier();
        signVerifier.exec(SIGNATURE_FILE, RESULT_FILE);
    }
}