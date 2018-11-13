package saygilis877007.a04;
import cgtools.Vec3;
/**
 * Created by home on 06.11.18.
 */
public class Hit {
    public final double t;
    public final Vec3 position;
    public final Vec3 normal;
    public final Material material;

    public Hit(double t, Vec3 position, Vec3 normal, Material material) {
        this.t = t;
        this.position = position;
        this.normal = normal;
        this.material = material;
    }

}
