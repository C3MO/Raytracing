package saygilis877007.a03;
import cgtools.Vec3;
/**
 * Created by home on 29.10.18.
 */
public class Hit implements Comparable <Hit>{
    public double strahlParameter;
    public Vec3 trefferPunkt;  // Position des Trefferpunkts
    public Vec3 normalerPunkt;  // Normalenvektor der Oberflaeche


    public Hit(double strahlParameter, Vec3 trefferPunkt, Vec3 normalerPunkt){
        this.strahlParameter = strahlParameter;
        this.trefferPunkt = trefferPunkt;
        this.normalerPunkt = normalerPunkt;
    }

    @Override
    public int compareTo(Hit o) {
        return new Double(strahlParameter).compareTo(o.strahlParameter);
    }
}
