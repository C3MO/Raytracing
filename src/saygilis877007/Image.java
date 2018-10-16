package saygilis877007;

import cgtools.Vec3;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class Image {

    int width = 15;
    int height = 7;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    public Image(int width, int height) {
        //System.out.println("Image not yet implemented.");
    }



    public void setPixel(int x, int y, Vec3 color) {
        for (y = 0; y < height; y++) {
            for (x = 0; x < width; x++) {

                int a = (int) (Math.random() * 256); //alpha
                int r = (int) (y * 256); //red
                int g = (int) (Math.random() * 256); //green
                int b = (int) (Math.random() * 256); //blue

                int p = (r << 24) | (r << 16) | (g << 8) | b; //pixel

                image.setRGB(x, y, p);
            }
        }
    }

    public void write(String filename) throws IOException {

        File outputfile = new File(filename);
        ImageIO.write(image, "png", outputfile);

    }


}
