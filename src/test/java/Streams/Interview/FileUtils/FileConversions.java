package Streams.Interview.FileUtils;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileConversions {


    @Test
    public void testing(){

        File inputFile = new File("src/test/resources/CREDIT_CARD.bmp");
        File outputFile = new File("src/test/resources/output.png");

        try {
            // Read BMP image
            BufferedImage image = ImageIO.read(inputFile);

            // Write PNG image
            ImageIO.write(image, "PNG", outputFile);

            System.out.println("Image conversion successful.");
        } catch (
                IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
