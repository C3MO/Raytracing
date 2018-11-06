package saygilis877007.a04;
import cgtools.Vec3;
/**
 * Created by home on 06.11.18.
 */
public class Plane implements Shape{

    private Vec3 tmin;
    private Vec3 tmax;
    private Vec3 backgroundcolor;

    Plane(Vec3 tmin, Vec3 tmax, Vec3 backgrcolor) {
        this.tmin = tmin;
        this.tmax = tmax;
        this.backgroundcolor = backgrcolor;
    }

    @Override
    public Hit intersect(Ray r) {

        double t = Vec3.dotProduct(Vec3.subtract(tmin, r.x0), tmax) / Vec3.dotProduct(r.d, tmax);

        if (t > 0) return new Hit(t, r.pointAt(t), tmax, backgroundcolor);

        return null;
    }
}
