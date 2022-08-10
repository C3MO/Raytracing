package saygilis877007.a01;

import cgtools.Vec3;
import java.io.IOException;
import saygilis877007.Image;


public class Main {

    static int width = 360;
    static int height = 180;
    static int z;
    static int a = 10;
    static int b = 2;

    /**
     * It creates a new image, sets the color of each pixel, and then writes the image to a file
     */
    public static void main(String[] args) {
        Image image = new Image(width, height);

        // This is a nested for loop. The outer loop is iterating over the width of the image, and the inner loop is
        // iterating over the height of the image. The pixelColor method is being called for each pixel in the image.
        for (int i = 0; i != width; i++) {
            for (int j = 0; j != height; j++) {
                image.setPixel(i, j, pixelColor(i, j));
            }
        }

        String filename = "doc/a01-checkered-background.png";
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException err) {
            System.out.printf("Something went wrong writing %n", filename, err);
        }
    }

    /**
     * If the value of z is divisible by b, then return a vector with the x, y, and z values of 0, 0, and 0. Otherwise,
     * return a vector with the x, y, and z values of 1, 1, and x
     *
     * @param x The x value of the pixel.
     * @param y The y value of the pixel.
     * @return The pixelColor method is returning a vector with the x, y, and z values of 0, 0, and 0.
     */
    static Vec3 pixelColor(int x, int y) {
        z = x / a + y / a;
        if (z % b == 0) {
            // Returning a new vector with the x, y, and z values of 0, 0, and 0.
            return new Vec3(0, 0, 0);
        } else {
            // Creating a new vector with the x, y, and z values of 1, 1, and x.
            return new Vec3(1, 1, (x));
        }

    }

}

