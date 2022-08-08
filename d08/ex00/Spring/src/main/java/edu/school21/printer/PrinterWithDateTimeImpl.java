package edu.school21.printer;

import edu.school21.renderer.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer{

    private final Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer){
        this.renderer = renderer;
    }

    @Override
    public void print(String text) {
        renderer.printMsgToConsole(String.format("%s %s", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd hh:mm:ss")), text));
    }
}
