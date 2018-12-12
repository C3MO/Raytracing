package saygilis877007.a07;
import cgtools.Vec3;

/**
 * Created by home on 06.11.18.
 */
public class Plane implements Shape{

    private Vec3 tmin;
    private Vec3 tmax;
    private Material material;                                                                                          //material = Lambertsches (alb)

    Plane(Vec3 tmin, Vec3 tmax, Material material) {
        this.tmin = tmin;
        this.tmax = tmax;
        this.material = material;
    }

    @Override
    public Hit intersect(Ray r) {               // Hit intersect Ray
                                                                                                            //Ray von x0 ist auf 0,0,0 koordinaten
        double t = Vec3.dotProduct(Vec3.subtract(tmin, r.x0), tmax) / Vec3.dotProduct(r.d, tmax);          //tmin  aus Vec3 (Vektoren) und tmax (Vec3) & Methode Dotproduct wird aufgerufen
                                                                                                            //einzelne Punkte an den koordinaten werden erstellt (koordienaten werden subtrahiert von Vec3 Klasse
        if (t > 0 && t < r.tmax) return new Hit(t, r.pointAt(t), tmax, material);                            //Ray von tmax geht ins Unendliche
                                                                                                    //Wenn t größer als 0 & t kleiner als tmax von Ray
        return null;                                                                                //Gebe den neuen Hit zurück mit den Werten (Ray zeigt auf t, tmax, material und t)
    }
}
