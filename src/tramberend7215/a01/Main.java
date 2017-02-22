package tramberend7215.a01;

import cgtools.Vec3;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int width = 160;
        int height = 90;

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
            System.out.println(
                    String.format("Something went wrong writing: %s: %s", filename, error));
        }
    }

    static Vec3 pixelColor(int x, int y) {
        return new Vec3(0.5, 0.5, 0.5);
    }
}
