package saygilis877007.a03;
import cgtools.Vec3;


/**
 * Created by home on 29.10.18.
 */
public class Kugel implements Comparable <Kugel> {
    public final double radius;
    public final Vec3 mitte;
    public final Vec3 color;

    public Kugel(double radius, Vec3 mitte) {
        this.radius = radius;
        this.mitte = mitte;
        this.color = new Vec3(0, 0, 0);

    }
    public Hit intersect(Ray ray) {

        double t0;
        double t1;
        double t2;
        Vec3 n0;
        Vec3 n1;
        Vec3 n2;

        double p = Vec3.dotProduct(Vec3.multiply(2, ray.d), Vec3.subtract(ray.x0, mitte));
        double q = Vec3.squaredLength(Vec3.subtract(ray.x0, mitte)) - Math.pow(radius, 2);
        double w = Math.pow(p / 2, 2) - q;



        // 1 Schnittpunkt
        if (w == 0){
            t0 = -p / 2;
            n0 = Vec3.divide(Vec3.subtract(ray.pointAt(t0), mitte), radius);
            return new Hit(t0, ray.pointAt(t0), n0);
        }
        // 2 Schnittpunkte
        if (w > 0){
            t1 = -p / 2 + Math.sqrt(w);
            t2 = -p / 2 - Math.sqrt(w);

            n1 = Vec3.divide(Vec3.subtract(ray.pointAt(t1), mitte), radius);
            n2 = Vec3.divide(Vec3.subtract(ray.pointAt(t2), mitte), radius);



            if(t1 < t2){
                return new Hit(t1, ray.pointAt(t1), n1);
            }else{

                return new Hit(t2, ray.pointAt(t2), n2);
            }

        }
        return null;
    }


        @Override
    public int compareTo(Kugel o) {
        return new Double(radius).compareTo(o.radius);
    }
}
