package saygilis877007.a04;
import cgtools.Vec3;
/**
 * Created by home on 06.11.18.
 */
public class Camera {
    private double phi;
    private int width;
    private int height;

    Camera(double phi, int width, int height) {
        this.phi = phi;
        this.width = width;
        this.height = height;
    }

    Ray generateRay(double x, double y) {
        double a = x - width / 2;
        double b = height / 2 - y;
        double c = -1 * ((width / 2) / Math.tan(phi / 2));

        return new Ray(Vec3.vec3(0, 0, 0), new Vec3(a,b,c));
    }

}
