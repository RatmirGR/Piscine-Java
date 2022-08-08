package edu.school21.printer.app;

import edu.school21.printer.logic.ConverterToChars;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {

        if (args.length != 3 || args[0].length() != 1 || args[1].length() != 1) {
            System.err.println("Error: Invalid args");
            System.exit(-1);
        }

        char whitePxl = args[0].charAt(0);
        char blackPxl = args[1].charAt(0);
        String path = args[2];

        try {
            ConverterToChars converter = new ConverterToChars(whitePxl, blackPxl, path);
            converter.convert();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
