package saygilis877007.a09;
import cgtools.Vec3;
/**
 * Created by home on 12.12.18.
 */



public class Light implements Material {

    private Vec3 alb;

    Light(Vec3 alb) {
        this.alb = alb;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return alb;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        /*Vec3 drnd = vec3(Random.random() * 2 - 1, Random.random() * 2 - 1, Random.random() * 2 - 1);

        while (drnd.length() > 1 || drnd.length() < -1)
            drnd = vec3(Random.random() * 2 - 1, Random.random() * 2 - 1, Random.random() * 2 - 1);

        return new Ray(h.position, normalize(add(normalize(h.normal), drnd)));*/
        return null;
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return null;
    }
}
