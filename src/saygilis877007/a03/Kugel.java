package saygilis877007.a03;
import cgtools.Vec3;


/**
 * Created by home on 29.10.18.
 */
public class Kugel implements Comparable <Kugel> {
    public final double radius;
    public final Vec3 mitte;
    public final Vec3 color;

    public Kugel(double radius, Vec3 mitte) {
        this.radius = radius;
        this.mitte = mitte;
        this.color = new Vec3(0, 0, 0);

    }

    @Override
    public int compareTo(Kugel o) {
        return 0;
    }
}
