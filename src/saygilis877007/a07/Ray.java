package saygilis877007.a07;
import cgtools.Vec3;

/**
 * Created by home on 06.11.18.
 */
public class Ray {

    public final Vec3 x0; //Nullpunkt koordinaten
    public final Vec3 d;
    public final double tmin, tmax; //Sind auf dem Nullpunkt

    public Ray(Vec3 x0, Vec3 d) {                   //Vekotroen wurden erstellt
        this.x0 = x0;
        this.d = Vec3.normalize(d);                 //Normalisierte Vektoren wurden erstellt
        this.tmin = 0;
        this.tmax = Double.POSITIVE_INFINITY;       //Geht bis ins Unendliche
    }

    Vec3 pointAt(double t) {
        return Vec3.add(x0, Vec3.multiply(t,d));
    }

}

// für alle pixel in einem Bild wird ein Raystrahl für eine Scene erstellt
//im intersect wird die Lichtstrahl intensität berechnet
// Der Skalarprodukt wird mit dem Kosinus berechnet (Normalenvektor und Richtung zur Lichtquelle