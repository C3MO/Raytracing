package saygilis877007.a02;

import cgtools.Vec3;
import cgtools.Random;
import saygilis877007.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by home on 17.10.18.
 */
public class Main {
    static int width = 360;
    static int heigth = 240;
    static int circles = 10; //Anzahl der Kreise
    static double n = 10;   //Sampling der Kreise

    public static void main(String[] args) {
        ArrayList<Circle> listCircles = listCircles(circles);
        Image image = new Image(width, heigth);


        String filename = "doc/a02-discs.png";
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }
    }



    static ArrayList<Circle> listCircles (int anzahlKreise) {                           //Es werden die Kreise erstellt
        ArrayList<Circle> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < anzahlKreise; i++) {
            list.add(new Circle (random.nextInt(555), random.nextInt(200), random.nextInt(40) + 10,
                    new Vec3(random.nextDouble(), random.nextDouble(), random.nextDouble())));
        }
        Collections.sort(list);
        return list;
    }

    static Vec3 pixelColor(double x, double y, ArrayList<Circle> kreisListe) {          //Den einzelnen Pixeln farben versehen
        for (Circle kr : kreisListe) {
            if (Math.sqrt((kr.getX() - x) * (kr.getX() - x) + (kr.getY() - y) * (kr.getY() - y)) <= kr.getRadius()) {
                return kr.getCircleColor();
            }
        }
        return new Vec3(1,1,1);
    }


    static Vec3 gamma(Vec3 color, double gamma) {
        color.x = Math.pow(color.x, 1.0 / gamma);
        color.y = Math.pow(color.y, 1.0 / gamma);
        color.z = Math.pow(color.z, 1.0 / gamma);

        return color;
    }

}