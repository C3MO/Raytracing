package saygilis877007.a09;

import cgtools.Vec3;

import java.util.Arrays;

import static cgtools.Vec3.*;

/**
 * Created by home on 12.12.18.
 */
public class Zylinder implements Shape {

    private double rad;
    private double height;
    private Vec3 center;
    private Material material;
    private Vec3 norm;

    Zylinder(double r, double height, Vec3 center, Material material) {
        this.rad = r;
        this.height = height;
        this.center = center;
        this.material = material;
    }

    public Hit intersect(Ray r) {

        Hit h = null;

        double dx = r.d.x;
        double dy = r.d.y;
        double dz = r.d.z;

        double x0x = subtract(r.x0, center).x;
        double x0y = subtract(r.x0, center).y;
        double x0z = subtract(r.x0, center).z;

        double a = dx * dx + dz * dz;
        double b = 2 * x0x * dx + 2 * x0z * dz;
        double c = x0x * x0x + x0z * x0z - rad * rad;

        double denominator = 2 * a;
        if (denominator == 0) return null;

        double dis = (Math.pow(b, 2) - (4 * a * c));

        double t1 = Math.min((-b + Math.sqrt(dis)) / (2 * a), (-b - Math.sqrt(dis)) / (2 * a));
        double t2 = (height / 2.0 - x0y) / dy;
        double t3 = (-height / 2.0 - x0y) / dy;

        double[] arr = {t1, t2, t3};
        Arrays.sort(arr);

        double t = 0;
        Vec3 sch = null;
        for (double x : arr) {
            sch = r.pointAt(x);
            if (x == t1) {
                if (Math.abs(sch.y - center.y) < height / 2.0) {
                    t = x;
                    norm = divide(subtract(r.pointAt(x), center), rad);
                    break;
                }
            } else {
                if (Math.pow(sch.x - center.x, 2)
                        + Math.pow(sch.z - center.z, 2)
                        - Math.pow(rad, 2) <= 0) {
                    if (x == t2) {
                        norm = vec3(0, 1, 0);
                    } else if (x == t3) {
                        norm = vec3(0, -1, 0);
                    }
                    t = x;
                    break;
                }
            }
        }

        if (t > r.tmin && t < r.tmax) {
            h = new Hit(t, sch, normalize(norm), material);
        }

        return h;
    }

}