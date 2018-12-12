package saygilis877007.a09;

import cgtools.Vec3;

import java.util.Arrays;

import static cgtools.Vec3.*;

/**
 * Created by home on 12.12.18.
 */


public class Kegel implements Shape {

    private double rad;
    private double height;
    private Vec3 center;
    private Material material;
    private Vec3 norm;

    Kegel(double r, double height, Vec3 center, Material material) {
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

        double a = dx * dx + dz * dz - (rad * rad / height * height) * dy * dy;
        double b = 2 * (x0x * dx + x0z * dz + (rad * rad / height * height) * (height - x0y) * dy);
        double c = x0x * x0x + x0z * x0z - (rad * rad / height * height) * (height - x0y) * (height - x0y);

        double denominator = 2 * a;
        if (denominator == 0) return null;

        double dis = (Math.pow(b, 2) - (4 * a * c));
        if (dis < 0) return null;

        double t1 = (-b + Math.sqrt(dis)) / (2 * a);
        double t2 = (-b - Math.sqrt(dis)) / (2 * a);
        double tmin;

        //mantel : sucht den kleineren t wert von beiden
        double min = Math.min(t1, t2);
        double max = Math.max(t1, t2);
        if (min > r.tmin) tmin = min;
        else {
            if (max > r.tmin) tmin = max;
            else return null;
        }

        //boden
        double t3 = (-height - x0y) / dy;

        double[] arr = {tmin, t3};
        Arrays.sort(arr);

        double t = 0;
        Vec3 sch = null;
        for (double x : arr) {
            sch = r.pointAt(x);
            if (x == tmin) {
                if (Math.abs(sch.y - center.y) < height) {
                    t = x;
                    norm = normalize(vec3(sch.x - center.x, 0, sch.z - center.z));
                    break;
                }
            } else {
                if (Math.pow(sch.x - center.x, 2) + Math.pow(sch.z - center.z, 2) - Math.pow(2 * rad, 2) <= 0) {
                    if (x == t3) {
                        norm = vec3(0, -1, 0);
                    }
                    t = x;
                    break;
                }
            }
        }

        if (t == 0 || t < r.tmin) {
            return null;
        } else {
            h = new Hit(t, sch, normalize(norm), material);
        }

        return h;
    }

}