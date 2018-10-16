package saygilis877007.a01;




import cgtools.Vec3;
import saygilis877007.Image;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by home on 10.10.18.
 */
public class ColoredSquare {
    final int width = 160;
    final int height = 90;
    Vec3 color;


    Image image = new Image(width, height);

    ColoredSquare(Vec3 color) {
        this.color = color;
    }

    Vec3 pixelColor(double x, double y) {
        return color;
    }
    public void setPixel(int x, int y, Vec3 color) throws IOException{

        ColoredSquare farben = new ColoredSquare(Vec3.red);
        for (x = 0; x != width; x++) {
            for (y = 0; y != height; y++) {
                image.setPixel(x, y, farben.pixelColor(x, y));
            }
        }

        write(image,"doc/a01-square.png");

            }

    public void write(Image image, String filename) throws IOException {

        File outputfile = new File(filename);
        ImageIO.write((RenderedImage) this.image, "png", outputfile);

    }
        }

