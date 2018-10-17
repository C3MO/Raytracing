package saygilis877007.a01;

import cgtools.ImageWriter;
import cgtools.Vec3;
import saygilis877007.Image;

import java.io.IOException;

/**
 * Created by home on 10.10.18.
 */


public class ColoredSquare {

    static int width = 360;
    static int height = 240;

    static int z;
    static int b = 0;
    static int c = 2;
    static int d = 3;

    static int squarePart = 40;
    Vec3 color;

    public static void main(String[] args) {
        Image image = new Image(width, height);


        class colorChoice {
            Vec3 color;

            colorChoice(Vec3 color) {
                this.color = color;
            }


            Vec3 pixelColor(int x, int y) {

                double zposition = height / d;
                z = x / squarePart + y / squarePart;
                if ((x > width / c - zposition) && (x < width / c + zposition) && (y > height / c - zposition) && (y < height / c + zposition)) {
                    return new Vec3(1, 0, 0.9);
                } else if (z % c == b) {            //Background Schachbrett
                    return new Vec3(0, 0, 0);
                } else {

                    return color;
                }


            }
        }


        colorChoice color = new colorChoice(Vec3.white);
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, color.pixelColor(x, y));
            }
        }
        write(image, "doc/a01-square.png");
    }

    static void write(Image image, String filename) {
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException err) {
            System.out.println(String.format("Something went wrong writing: ", filename, err));
        }
    }

}

