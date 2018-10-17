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

    public static void main(String[] args) {
        Image image = new Image(width, height);

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
            System.out.println(String.format("Something went wrong writing ", filename, err));
        }
    }

    static Vec3 pixelColor(int x, int y) {      //new vector with the given coordinates and choose color
        z = x / a + y / a;
        if (z % b == 0) {
            return new Vec3(0, 0, 0); // Schachbrettmuster (Black)
        } else {
            return new Vec3(1, 1, (x)); //Schachbrettmuster (White)
        }

    }

}

