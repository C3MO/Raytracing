package saygilis877007.a12;
import cgtools.Vec3;
/**
 * Created by home on 14.11.18.
 */
public class Spiegel implements Material{


    private static final double GLAS = 1.5;
    private static final double LUFT = 1.0;

    Vec3 albedo;

    public Spiegel(Vec3 albedo) {
        this.albedo = albedo;
    }

    @Override
    public Vec3 emittedRadiance(Ray r, Hit h) {
        return Vec3.white;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Vec3 d = r.d;
        Vec3 n = h.normal;

        double luft = LUFT;
        double glas = GLAS;

        return r;
    }
        Vec3 scattered;

    public Vec3 refract(Vec3 d, Vec3 n, double luft, double glas) {

        //Implementierung Brechungsgesetz
        double rt = luft / glas;

        double c = Vec3.dotProduct(Vec3.multiply(n, -1), d);

        Vec3 rd = Vec3.multiply(rt, d);
        double rc = rt * c;
        double disc = 1 - rt * rt * (1 - c * c);

        if (disc >= 0) {
            double sqrt = Math.sqrt(disc);
            Vec3 dt = Vec3.add(rd, Vec3.multiply(rc - sqrt, n)); //Gebrochener Strahl
            return dt;
        } else {
            return null;
        }

    }
    @Override
    public Vec3 albedo(Ray r, Hit h) {
        return albedo;
    }
    public double schlick(Vec3 d, Vec3 n, double n1, double n2) {
        double r0 = Math.pow(((n1 - n2) / (n1 + n2)), 2); //ist der reflektierte Anteil
        return r0 + (1 - r0) * Math.pow(1 + Vec3.dotProduct(n, d), 5);

    }
    public Vec3 reflect(Vec3 d,Vec3 n) {
        return Vec3.subtract(d, Vec3.multiply(2, Vec3.multiply(Vec3.dotProduct(n, d), n)));

    }
}

