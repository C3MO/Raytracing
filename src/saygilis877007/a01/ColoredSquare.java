package saygilis877007.a01;
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


        /**
         * The class colorChoice is a class that takes in a color and returns a color based on the position of the pixel
         */
        class colorChoice {
            final Vec3 color;

            // A constructor that takes in a color and assigns it to the variable color.
            colorChoice(Vec3 color) {
                this.color = color;
            }


            /**
             * If the pixel is in the middle of the image, it returns a color. If it is not, it returns a different color
             *
             * @param x The x coordinate of the pixel
             * @param y The y coordinate of the pixel.
             * @return The color of the pixel.
             */
            Vec3 pixelColor(int x, int y) {

                double zposition = height / d;
                z = x / squarePart + y / squarePart;
                // This is a conditional statement that checks if the pixel is in the middle of the image. If it is, it
                // returns a color.
                if ((x > width / c - zposition) && (x < width / c + zposition) && (y > height / c - zposition) && (y < height / c + zposition)) {
                    return new Vec3(1, 0, 0.9);
                } else if (z % c == b) {
                    return new Vec3(0, 0, 0);
                } else {

                    return color;
                }
                   

            }
        }


        // Creating a new instance of the class colorChoice and assigning it to the variable color.
        colorChoice color = new colorChoice(Vec3.white);
        // This is a nested for loop that goes through every pixel in the image and sets the color of the pixel to the
        // color that is returned by the pixelColor method.
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, color.pixelColor(x, y));
            }
        }
        write(image, "doc/a01-checkered-background.png");
    }

    /**
     * It writes an image to a file
     *
     * @param image the image to write
     * @param filename The name of the file to write to.
     */
    static void write(Image image, String filename) {
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException err) {
            System.out.printf("Something went wrong writing: %n", filename, err);
        }
    }

}

