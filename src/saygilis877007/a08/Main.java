package saygilis877007.a08;

import cgtools.Random;
import cgtools.Vec3;
import saygilis877007.Image;

import java.io.IOException;

import static cgtools.Vec3.vec3;

/**
 * Created by home on 06.11.18.
 */
public class Main {

    private static int width = 360;
    private static int height = 240;
    private static Image image;
    private static int sampling;
    private static Camera camera;
    private static Group group;

    public static void main(String[] args) {
        image = new Image(width, height);

        String filename = "doc/a09.png";

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

            Group gr = new Group();

            double angle, c, r;
            c = (Math.sqrt(5) + 1) / 2;
            double x, y;
            int numberOfSeeds = 100;

            for (int i = 0; i < numberOfSeeds; i++) {
                r = Math.pow(i, c) / (numberOfSeeds / 2);
                angle = 2 * Math.PI * c * i;
                x = r * Math.sin(angle);
                y = r * Math.cos(angle);
                if (i % 2 == 0) gr.addShape(new Kugel(i / (numberOfSeeds / 10) * 0.5, vec3(x, y + 40, -50), new Lambertsches(vec3(0.839, 0.443, 0.474))));
                else gr.addShape(new Kugel(i / (numberOfSeeds / 10) * 0.5, vec3(x, y + 40, -50), new Lambertsches(vec3(0.839, 0.658, 0.443))));
                //gr.addShape(new Kugel(i / (numberOfSeeds / 10) * 0.5, vec3(x, y + 40, -2), new Glass(vec3(0.839, 0.443, 0.474))));
            }

            //links
            gr.addShape(new Zylinder(10, 100, vec3(-60, 0, -70), new Lambertsches(vec3(0.443, 0.705, 0.839))));
            gr.addShape(new Zylinder(10, 100, vec3(-60, 0, -30), new Lambertsches(vec3(0.509, 0.839, 0.443))));

            //rechts
            gr.addShape(new Zylinder(10, 100, vec3(60, 0, -70), new Lambertsches(vec3(0.509, 0.839, 0.443))));
            gr.addShape(new Zylinder(10, 100, vec3(60, 0, -30), new Lambertsches(vec3(0.443, 0.705, 0.839))));

            //links nach rechts
            gr.addShape(new Kegel(1.5, 4, vec3(-30, 0.5, 0), new Lambertsches(vec3(0.721, 0.443, 0.839))));
            gr.addShape(new Kegel(1.5, 4, vec3(0, 0.5, 0), new Lambertsches(vec3(0.839, 0.443, 0.756))));
            gr.addShape(new Kegel(1.5, 4, vec3(30, 0.5, 0), new Lambertsches(vec3(0.721, 0.443, 0.839))));

            gr.addShape(new Plane(vec3(0, -1, 0), vec3(0, 1, 0), new Lambertsches(vec3(0.396, 0.498, 0.603))));
            gr.addShape(new Background(new Hintergrund()));

            return gr;
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

        Hit hit = g.intersect(r);           //die Gruppe g schneidet den Ray r
                                            //Query material auf dem hit punkt
        Vec3 emission = hit.material.emittedRadiance(r, hit);           //kombiniert die emission und der reflektion        Strahldichte
        Ray scattered = hit.material.scatteredRay(r, hit);              //zerstreuung vom Strahl

        if (scattered != null) {                                                        //Wenn die Zerstreuung nicht null ist
            return Vec3.add(emission, Vec3.multiply(hit.material.albedo(r, hit), radiance(scattered, g, depth - 1)));       //Vektor(add funktion), Vektor(multipliziert funktion), die oberflächeneigenschaften werden vergeben
        } else return emission;                                 //ansonsten gebe die Strahlung zurück
    }


    private static Vec3 pixelColor(double x, double y) {
        Vec3 bgColor;

        Ray ray = camera.generateRay(x, y);             //Ray wird erstellt von den zurückgegebenen Werten aus der Camera Klasse
        bgColor = radiance(ray, group, 5);          // stelle den radiance für die Gruppe und dem Ray ein
                                                            //Radiance funktion
        return bgColor;                                     //Die Rekursionstiefe ist dabei auf den Wert depth begrenzt
                                                            //5 abtastpunkte pro pixel
    }

}
