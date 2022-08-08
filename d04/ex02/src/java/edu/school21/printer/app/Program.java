package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

import edu.school21.printer.logic.ConverterToChars;

import java.io.IOException;

@Parameters(separators = "=")
public class Program {

    private static final String PATH = "src/resources/image.bmp";

    @Parameter(names = {"--white"}, required = true)
    private String whitePxl;
    @Parameter(names = {"--black"}, required = true)
    private String blackPxl;

    public static void main(String[] args) {
        Program program = new Program();
        try {
            JCommander.newBuilder()
                    .addObject(program)
                    .build()
                    .parse(args);
        } catch (ParameterException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        try {
            ConverterToChars converter = new ConverterToChars(program.whitePxl, program.blackPxl, PATH);
            converter.convert();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
