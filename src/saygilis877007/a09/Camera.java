package saygilis877007.a09;
import cgtools.Vec3;

/**
 * Created by home on 06.11.18.
 */
public class Camera {
    private double phi;
    private int width;
    private int height;

    Camera(double phi, int width, int height) {
        this.phi = phi;                             //Winkel
        this.width = width;                         //breite
        this.height = height;                       //höhe
    }

    Ray generateRay(double x, double y) {
        double a = x - width / 2;                                    //x - breite / 2 = a
        double b = height / 2 - y;                                  //(höhe /2) - y = b
        double c = -1 * ((width / 2) / Math.tan(phi / 2));          //(breite / 2) / (tangens aus dem Winkel/2) = c

        return new Ray(Vec3.vec3(0, 0, 0), new Vec3(a,b,c));            //Das erzeugte Ray wird zurückgegeben (a,b,c)
    }

}

//Kamera ist das Auge, die Planer in der das Bild ist, dann der Ray Strahl zum eigentlichen Objekt. Der Hit ist der Punkt wo Hit auf die Scene trifft. (Lichtquelle ist auch vorhanden)
