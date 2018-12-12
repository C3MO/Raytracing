package saygilis877007.a09;

import cgtools.Random;
import cgtools.Vec3;
import saygilis877007.Image;

import java.io.IOException;

import static cgtools.Vec3.vec3;
import cgtools.Mat4;
import cgtools.Random;
import cgtools.Vec3;
import lam856667.Image;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static cgtools.Mat4.rotate;
import static cgtools.Mat4.translate;
import static cgtools.Vec3.*;


public class Main {

    private static int sampling;
    private static Camera camera;
    private static Group group;
    private static int z = 4;

    public static void main(String[] args) {
        int width = 720;
        int height = 405;
        Image image = new Image(width, height);

        Mat4 cameraT;
        Mat4 cameraR1;
        Mat4 cameraR2;

        cameraR1 = rotate(vec3(1, 0, 0), -10);
        cameraT = translate(vec3(0, 5, 20));
        Mat4 cameraPos = cameraT.multiply(cameraR1);

        /*cameraR1 = rotate(vec3(0, 1, 0), -45);
        cameraR2 = rotate(vec3(1, 0, 0), -10);
        cameraT = translate(vec3(-15, 5, 10));*/
        //Mat4 cameraPos = cameraT.multiply(cameraR1.multiply(cameraR2));

        //Mat4 cameraPos = Mat4.translate(vec3(0, 0, 20));

        raytrace(new Camera(Math.PI / 2, width, height, cameraPos), genObjects(), 100);


        int cores = Runtime.getRuntime().availableProcessors();
        int threads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        Future[] futures = new Future[threads];

        double start = System.currentTimeMillis();

        for (int i = 0; i != threads; i++) {
            final int core = i;
            futures[i] = executorService.submit(() -> {
                for (int x = (width / threads) * core; x < (width / threads) * core + (width / threads); x++) {
                    for (int y = 0; y != height; y++) image.setPixelGamma(x, y, pixelColor(x, y));
                }
            });
        }

        for (int j = 0; j != threads; j++) {
            try {
                try {
                    futures[j].get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();

        double end = System.currentTimeMillis();
        double time = end - start;

        double sekunde = time / 1000;
        double minute = time * 1.6666666666667E-5;
        System.out.println("sek: " + sekunde + ", min: " + minute);

        String filename = "doc/a09-1.png";

        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }

    }

    private static void raytrace(Camera c, Group g, int s) {
        camera = c;
        group = g;
        sampling = s;
    }

    @NotNull
    private static Group genObjects() {
        Group scene = new Group(new Transformation(Mat4.translate(vec3(0))),
                new Plane(vec3(0, -0.5, 0), vec3(0, 1, 0), new Lambertsches(vec3(0.8))),
                new Background(new Hintergrund(vec3(0.2))));

        Mat4 ktrans = Mat4.translate(vec3(0,0,5));
        Group kfamily = new Group(new Transformation(ktrans));

        kfamily.addShape(new Kugel(2, vec3(0,5,0), new Light(white)));

        for (int i = 0; i < 6; i++) {
            Mat4 kugeln = Mat4.rotate(vec3(0, 1, 0), i * (360 / 6));
            Group kirby = new Group(
                    new Transformation(kugeln),

                    new Kugel(0.4, vec3(-0.5, -0.4, -2.5), new Lambertsches(vec3(0.701, 0.160, 0.290))), // linkes bein
                    new Kugel(0.4, vec3(0.5, -0.4, -2.5), new Lambertsches(vec3(0.701, 0.160, 0.290))), // rechtes bein

                    new Kugel(0.18, vec3(-0.8, 0.1, -2.2), new Lambertsches(vec3(0.807, 0.294, 0.415))), // linker arm
                    new Kugel(0.18, vec3(0.8, 0.1, -2.2), new Lambertsches(vec3(0.807, 0.294, 0.415))), // rechter arm

                    new Kugel(0.2, vec3(-0.22, 0.9, -2.35), new Lambertsches(vec3(0))), // linkes auge
                    new Kugel(0.2, vec3(0.22, 0.9, -2.35), new Lambertsches(vec3(0))), // rechtes auge

                    new Kugel(0.08, vec3(-0.19, 1, -2.25), new Lambertsches(vec3(1))), // linkes auge weiss
                    new Kugel(0.08, vec3(0.19, 1, -2.25), new Lambertsches(vec3(1))), // rechtes auge weiss

                    new Kugel(0.07, vec3(0.0, 0.6, -2.0), new Lambertsches(vec3(0))), // mund
                    new Kugel(0.15, vec3(-0.5, 0.6, -2.2), new Lambertsches(vec3(0.964, 0.756, 0.819))), // linkes blush
                    new Kugel(0.15, vec3(0.5, 0.6, -2.2), new Lambertsches(vec3(0.964, 0.756, 0.819))) // rechtes blush
            );
            if (i == 0 || i == 2 || i == 4)
                kirby.addShape(new Kugel(1, vec3(0.0, 0.3, -3), new Glass(vec3(0.913, 0.584, 0.686)))); // body glass
            else
                kirby.addShape(new Kugel(1, vec3(0.0, 0.3, -3), new Lambertsches(vec3(0.913, 0.584, 0.686)))); // body lamb

            kfamily.addShape(kirby);
        }

        scene.addShape(kfamily);

        return scene;
    }

    private static Group fibonacci(int numberOfSeeds, Mat4 trans, Vec3 colorOne, Vec3 colorTwo) {
        double angle, c, r;
        c = (Math.sqrt(5) + 1) / 2;
        double x, y;

        Group fib = new Group(new Transformation(trans));
        for (int i = 0; i < numberOfSeeds; i++) {
            r = Math.pow(i, c) / (numberOfSeeds * 2.5);
            angle = 2 * Math.PI * c * i;
            x = r * Math.sin(angle);
            y = r * Math.cos(angle);
            if (i % 2 == 0)
                fib.addShape(new Kugel(i / (numberOfSeeds / 10) * 0.1, vec3(x, y, 0), new Lambertsches(colorOne)));
            else fib.addShape(new Kugel(i / (numberOfSeeds / 10) * 0.1, vec3(x, y, 0), new Lambertsches(colorTwo)));
        }

        return fib;
    }

    private static Vec3 pixelColor(int x, int y) {
        Vec3 bgColor = vec3(1);

        double n = Math.sqrt(sampling);
        for (int xi = 0; xi < n; xi++) {
            for (int yi = 0; yi < n; yi++) {
                double rx = Random.random();
                double ry = Random.random();
                double xs = x + ((xi + rx) / n);
                double ys = y + ((yi + ry) / n);
                bgColor = add(bgColor, pixelColor(xs, ys));
            }
        }

        bgColor = divide(bgColor, sampling);
        return bgColor;
    }

    private static Vec3 radiance(Ray r, Shape g, int depth) {
        if (depth == 0) return black;

        Hit hit = g.intersect(r);

        Vec3 emission = hit.material.emittedRadiance(r, hit);
        Ray scattered = hit.material.scatteredRay(r, hit);

        if (scattered != null) {
            return add(emission,
                    multiply(hit.material.albedo(r, hit), radiance(scattered, g, depth - 1)));
        } else return emission;
    }

    private static Vec3 pixelColor(double x, double y) {
        Vec3 bgColor;
        Ray ray = camera.generateRay(x, y);
        bgColor = radiance(ray, group, 5);
        return bgColor;
    }

}

/**
 * Created by home on 06.11.18.
 */
/*
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
*/
