package saygilis877007.a03;
import cgtools.Vec3;
/**
 * Created by home on 29.10.18.
 */
public class Hit implements Comparable <Hit>{
    public double t; //Strahlparmater
    public Vec3 x;  //Position des Trefferpunkts
    public Vec3 n;  // Normalenvektor
    public Vec3 c; //Farbe der Oberfläche


    public Hit(double t, Vec3 x, Vec3 n){
        this.t = t;
        this.x = x;
        this.n = n;
    }

    @Override
    public int compareTo(Hit o) {
        return new Double(t).compareTo(o.t);
    }
}
