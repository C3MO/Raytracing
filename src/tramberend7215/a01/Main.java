package tramberend7215.a01;

import cgtools.Vec3;
import static cgtools.Vec3.*;
import java.io.IOException;
import tramberend7215.Image;

public class Main {
    static int width = 160;
    static int height = 90;

    public static void main(String[] args) {
        Image image = new Image(width, height);

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, pixelColor(x, y));
            }
        }

        String filename = "doc/a01.png";
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }
    }

    static Vec3 pixelColor(double x, double y) {
        return vec3(0.5, 0.5, 0.5);
    }
}
