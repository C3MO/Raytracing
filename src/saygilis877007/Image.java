package saygilis877007;

import cgtools.Vec3;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class Image {

    int width = 640;
    int height = 320;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    public Image(int width, int height) {
        //System.out.println("Image not yet implemented.");
    }

    public void setPixel(int x, int y, Vec3 color) {
        //System.out.println("Image not yet implemented.");
    }

    public void write(String filename) throws IOException {

        File outputfile = new File(filename);
        ImageIO.write(image, "png", outputfile);

    }

    private static double clamp(double v) {
        return Math.min(Math.max(0, v), 1);
    }
}
