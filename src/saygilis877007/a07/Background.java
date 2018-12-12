package saygilis877007.a07;
import cgtools.Vec3;

/**
 * Created by home on 06.11.18.
 */
public class Background implements Shape {



    private Material material;

    Background(Material material) {

        this.material = material;
    }

    @Override
    public Hit intersect(Ray r) {
        return new Hit(r.tmax, r.pointAt(r.tmax), Vec3.vec3(1,0,0), material);
    }
}
