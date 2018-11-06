package saygilis877007.a04;
import cgtools.Vec3;
/**
 * Created by home on 06.11.18.
 */
public class Ray {

    public final Vec3 x0;
    public final Vec3 d;
    public final double t0, t1;

    public Ray(Vec3 x0, Vec3 d) {
        this.x0 = x0;
        this.d = Vec3.normalize(d);
        this.t0 = 0;
        this.t1 = Double.POSITIVE_INFINITY;
    }

    Vec3 pointAt(double t) {
        return Vec3.add(x0, Vec3.multiply(t,d));
    }

}
