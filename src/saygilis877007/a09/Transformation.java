package saygilis877007.a09;
import cgtools.Mat4;
import cgtools.Vec3;

/**
 * Created by home on 12.12.18.
 */


public class Transformation {

    Mat4 inv;
    Mat4 transpo;
    Mat4 transfo;

    public Transformation(Mat4 transfo) {
        this.transfo = transfo;
        this.inv = transfo.invertFull();
        this.transpo = inv.transpose();
    }

    public Ray transformRay(Ray r) {

        Vec3 ori = inv.transformPoint(r.x0);
        Vec3 dir = inv.transformDirection(r.d);

        return new Ray(ori, dir);
    }

    public Hit transformHit(Hit h) {

        Vec3 pos = transfo.transformPoint(h.position);
        Vec3 nor = transpo.transformDirection(h.normal);

        return new Hit(h.t, pos, nor, h.material);
    }

}
