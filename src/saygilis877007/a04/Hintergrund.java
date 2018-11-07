package saygilis877007.a04;
import cgtools.Vec3;
/**
 * Created by home on 07.11.18.
 */
public class Hintergrund implements Material {

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) { return Vec3.vec3(0.750, 0.889, 0.980); }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        return null;
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return null;
    }
}
