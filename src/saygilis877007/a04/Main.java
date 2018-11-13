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

        String filename = "doc/a05-diffuse-spheres.png";

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

    //Die Raytrace funktion ist leicht modifiziert worden (Rekursives Raytracing)


    private static Group genObjects() {
        return new Group(

                new Plane(vec3(0.0, -0.5, 0.0), vec3(0, 1, 0), new Lambertsches(vec3(0.5, 0.5, 0.5))),
                //new Kugel(0.5, vec3(1, 0.3, -3), new Lambertsches(vec3(1, 0, 0))), // body
               // new Kugel(0.25, vec3(1, 0.9, -3), new Lambertsches(vec3(1, 1, 1))), // body

               /* new Kugel(0.22, vec3(-0.5, -0.2, -2.5), new Lambertsches(vec3(1, 1, 0))), // linkes bein
                new Kugel(0.22, vec3(0.5, -0.2, -2.5), new Lambertsches(vec3(1, 1, 0))), // rechtes bein

                new Kugel(0.18, vec3(-0.8, 0.1, -2.2), new Lambertsches(vec3(1, 1, 0))), // linker arm
                new Kugel(0.18, vec3(0.8, 0.1, -2.2), new Lambertsches(vec3(1, 1, 0))), // rechter arm

                new Kugel(0.18, vec3(-0.4, 0.4, -2.2), new Lambertsches(vec3(1, 1, 0))), // linker arm
                new Kugel(0.18, vec3(0.4, 0.4, -2.2), new Lambertsches(vec3(1, 1, 0))),

*/

                //new Kugel(0.25, vec3(-1, 0.9, -3), new Lambertsches(vec3(1, 1, 1))), // body
                new Kugel(0.7, vec3(-1, 1.2, -3), new Lambertsches(vec3(1, 1, 1))), // body
                new Kugel(0.7, vec3(1, 1.2, -3), new Lambertsches(vec3(1, 1, 1))), // body
                new Kugel(0.7, vec3(-1.7, 0.4, -3), new Lambertsches(vec3(1, 1, 1))), // body

                new Kugel(0.7, vec3(-1.3, -0.8, -3), new Lambertsches(vec3(1, 1, 1))), // body
                new Kugel(0.7, vec3(1.3, -0.8, -3), new Lambertsches(vec3(1, 1, 1))), // body
                new Kugel(0.7, vec3(1.7, 0.4, -3), new Lambertsches(vec3(1, 1, 1))), // body


                new Kugel(0.22, vec3(0.5, -0.2, -2.5), new Lambertsches(vec3(1, 0, 0))), // linkes bein
                new Kugel(0.22, vec3(0, 0, -2.5), new Lambertsches(vec3(1, 0, 0))), // linkes bein
                new Kugel(0.15, vec3(0, 0, -2), new Lambertsches(vec3(0, 0, 0))), // linkes bein

                new Kugel(0.22, vec3(0, 0.6, -2.5), new Lambertsches(vec3(1, 0, 0))), // linkes bein
                new Kugel(0.22, vec3(0, -0.6, -2.5), new Lambertsches(vec3(1, 0, 0))), // linkes bein
                new Kugel(0.22, vec3(-0.5, -0.2, -2.5), new Lambertsches(vec3(1, 0, 0))), // rechtes bein

                new Kugel(0.18, vec3(0.8, 0.1, -2.2), new Lambertsches(vec3(1, 0, 0))), // linker arm
                new Kugel(0.18, vec3(-0.8, 0.1, -2.2), new Lambertsches(vec3(1, 0, 0))), // rechter arm

                new Kugel(0.18, vec3(0.4, 0.4, -2.2), new Lambertsches(vec3(1, 0, 0))), // linker arm
                new Kugel(0.18, vec3(-0.4, 0.4, -2.2), new Lambertsches(vec3(1, 0, 0))),

                new Background(new Hintergrund())
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
    private static Vec3 radiance(Ray r, Shape g, int depth) {
        if (depth == 0) return Vec3.zero;               //Wenn die maximale Tiefe gleich 0 beträgt gebe zero aus der Vec3 Klasse zurück

        Hit hit = g.intersect(r);           //die Gruppe g intersect den Ray r
                                            //Query material auf dem hit punkt
        Vec3 emission = hit.material.emittedRadiance(r, hit);           //kombiniert die emission und der reflektion
        Ray scattered = hit.material.scatteredRay(r, hit);

        if (scattered != null) {
            return Vec3.add(emission,
                    Vec3.multiply(hit.material.albedo(r, hit), radiance(scattered, g, depth - 1)));
        } else return emission;
    }
    private static Vec3 pixelColor(double x, double y) {
        Vec3 bgColor;

        Ray ray = camera.generateRay(x, y);             //Ray wird erstellt von den zurückgegebenen Werten aus der Camera Klasse
        bgColor = radiance(ray, group, 5);          // stelle den radiance für die Gruppe und dem Ray ein
                                                            //Radiance funktion
        return bgColor;                                     //Die Rekursionstiefe ist dabei auf den Wert depth begrenzt
    }

}
