package saygilis877007.a04;
import cgtools.Random;
import cgtools.Vec3;

/**
 * Created by home on 07.11.18.
 */
public class Lambertsches implements Material {

    private Vec3 alb;

    Lambertsches(Vec3 alb) {
        this.alb = alb;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return Vec3.vec3(0);
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Vec3 drnd = Vec3.vec3(Random.random(), Random.random(), Random.random());

        while (drnd.length() > 1 || drnd.length() < -1) drnd = Vec3.vec3(Random.random(), Random.random(), Random.random());

        return new Ray(h.position, Vec3.normalize(Vec3.add(Vec3.normalize(h.normal), drnd)));
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return alb;
    }
}