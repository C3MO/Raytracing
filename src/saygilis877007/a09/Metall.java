package saygilis877007.a09;

import cgtools.Random;
import cgtools.Vec3;

import static cgtools.Vec3.*;

/**
 * Created by home on 12.12.18.
 */

public class Metall implements Material {

    private Vec3 albedo;
    private double s;

    public Metall(Vec3 albedo, double s) {
        this.albedo = albedo;
        this.s = s;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return Vec3.black;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {

        Vec3 xrnd = vec3(Random.random()*2-1, Random.random()*2-1, Random.random()*2-1);
        while (xrnd.length() > 1 || xrnd.length() < -1)
            xrnd = vec3(Random.random()*2-1, Random.random()*2-1, Random.random()*2-1);

        Vec3 d = r.d;
        Vec3 n = normalize(h.normal);
        double skalarND = dotProduct(d, n);

        Vec3 dr = subtract(d, multiply(skalarND * 2, n));
        Vec3 ds = add(multiply(s, xrnd), normalize(dr));
        if (dotProduct(ds, n) < 0) return null;
        return new Ray(h.position, ds);
    }

    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return albedo;
    }
}
