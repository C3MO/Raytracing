package saygilis877007.a08;
import cgtools.Vec3;

import static cgtools.Vec3.*;
/**
 * Created by home on 12.12.18.
 */


/**
 * Created by youngie 2017....
 */
public class Disk implements Shape {

    Vec3 position;
    double radius;
    Vec3 normal;
    Material material;

    public Disk(Vec3 position, double radius, Vec3 normal, Material material) {
        this.position = position;
        this.radius = radius;
        this.normal = normal;
        this.material = material;
    }


    @Override
    public Hit intersect(Ray r) {
        if (dotProduct(r.d, normal) == 0) return null;

        double t = dotProduct(subtract(position, r.x0), normal) / dotProduct(r.d, normal);

        if (t > r.tmin && t < r.tmax) {
            Vec3 point = r.pointAt(t);
            Vec3 dis = subtract(point, position);
            double d = length(dis);

            if (d <= radius) return new Hit(t, r.pointAt(t), normalize(normal), material);
        }

        return null;
    }
}
