package saygilis877007.a04;
import cgtools.Vec3;

/**
 * Created by home on 06.11.18.
 */
public class Background implements Shape{


    private Vec3 backgroundColor;

    Background(Vec3 backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public Hit intersect(Ray r) {
        return new Hit(r.t1, r.pointAt(r.t1), Vec3.vec3(0,0,0), backgroundColor);
    }
}
