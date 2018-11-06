package saygilis877007.a04;
import static cgtools.Vec3.*;
import cgtools.Random;
import cgtools.Vec3;
import saygilis877007.Image;

import java.io.IOException;
/**
 * Created by home on 06.11.18.
 */
public class Main {

    private static int width = 1600;
    private static int height = 900;
    private static Image image;
    private static int sampling;
    private static Camera camera;
    private static Group group;

    public static void main(String[] args) {
        image = new Image(width, height);

        String filename = "doc/a04-scene.png";

        try {
            raytrace(new Camera(Math.PI / 2, width, height), genObjects(),100).write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }

    }

    private static Image raytrace(Camera c, Group g, int s) {
        camera = c;
        group = g;
        sampling = s;

        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, pixelColor(x, y));
            }
        }

        return image;
    }

    private static Group genObjects() {
        return new Group(
                new Background(vec3(0, 0, 0)),
                new Plane(vec3(0.0, -0.5, 0.0), vec3(0, 1, 0), gray), // boden


                new Kugel(0.5, vec3(0.0, -0.25, -2.5), green),
                new Kugel(0.7, vec3(-1.0, -0.25, -2.5), red),
                new Kugel(0.7, vec3(1.0, -0.25, -2.5), blue)

        );
    }

    private static Vec3 pixelColor(int x, int y) {
        Vec3 bgColor = vec3(0);

        double n = Math.sqrt(sampling);
        for (int xi = 0; xi < n; xi++) {
            for (int yi = 0; yi < n; yi++) {
                double rx = Random.random();
                double ry = Random.random();
                double xs = x + ((xi + rx) / n);
                double ys = y + ((yi + ry) / n);
                Vec3 temp = pixelColor(xs, ys);
                bgColor = Vec3.add(bgColor, temp);
            }
        }

        bgColor = Vec3.divide(bgColor, sampling);
        return bgColor;
    }

    private static Vec3 pixelColor(double x, double y) {
        Vec3 bgColor = vec3(0);

        Ray ray = camera.generateRay(x, y);
        Hit hit = group.intersect(ray);

        if (hit != null)  bgColor = hit.color;

        return bgColor;
    }

}
