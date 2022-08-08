package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConverterToChars {
    private final char whitePxl;
    private final char blackPxl;
    private final File path;

    public ConverterToChars(char whitePxl, char blackPxl, String path){
        this.whitePxl = whitePxl;
        this.blackPxl = blackPxl;
        this.path = new File(path);
    }

    public void convert() throws IOException {
        BufferedImage image = ImageIO.read(path.getAbsoluteFile());
        int[][] coordinates = new int[image.getHeight()][image.getWidth()];

        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates[0].length; j++) {
                if (image.getRGB(j, i) == Color.WHITE.getRGB()){
                    System.out.print(whitePxl);
                }else{
                    System.out.print(blackPxl);
                }
            }
            System.out.println();
        }
    }
}
