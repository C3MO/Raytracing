package saygilis877007.a04;
import cgtools.Vec3;
/**
 * Created by home on 06.11.18.
 */
public class Plane implements Shape{

    private Vec3 tmin;
    private Vec3 tmax;
    private Material material;

    Plane(Vec3 tmin, Vec3 tmax, Material material) {
        this.tmin = tmin;
        this.tmax = tmax;
        this.material = material;
    }

    @Override
    public Hit intersect(Ray r) {

        double t = Vec3.dotProduct(Vec3.subtract(tmin, r.x0), tmax) / Vec3.dotProduct(r.d, tmax);

        if (t > 0 && t < r.tmax) return new Hit(t, r.pointAt(t), tmax, material);

        return null;
    }
}
