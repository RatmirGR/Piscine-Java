package edu.school21.printer.logic;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConverterToChars {
    private final Attribute whitePxl;
    private final Attribute blackPxl;
    private final File path;

    public ConverterToChars(String whitePxl, String blackPxl, String path){
        this.whitePxl = getAttributeColor(whitePxl);
        this.blackPxl = getAttributeColor(blackPxl);
        this.path = new File(path);
    }

    public void convert() throws IOException {
        BufferedImage image = ImageIO.read(path);

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                if (image.getRGB(j, i) == Color.WHITE.getRGB()){
                    System.out.print(Ansi.colorize("  ", whitePxl));
                }else{
                    System.out.print(Ansi.colorize("  ", blackPxl));
                }
            }
            System.out.println();
        }
    }

    private Attribute getAttributeColor(String color){
        switch (color) {
            case "RED":
                return Attribute.RED_BACK();
            case "GREEN":
                return Attribute.GREEN_BACK();
            case "BLUE":
                return Attribute.BLUE_BACK();
            case "BLACK":
                return Attribute.BLACK_BACK();
            case "WHITE":
                return Attribute.WHITE_BACK();
            case "BRIGHT_RED":
                return Attribute.BRIGHT_RED_BACK();
            case "BRIGHT_GREEN":
                return Attribute.BRIGHT_GREEN_BACK();
            case "BRIGHT_BLUE":
                return Attribute.BRIGHT_BLUE_BACK();
            case "BRIGHT_BLACK":
                return Attribute.BRIGHT_BLACK_BACK();
            case "BRIGHT_WHITE":
                return Attribute.BRIGHT_WHITE_BACK();
            default:
                throw new IllegalArgumentException("Invalid argument");
        }
    }
}
