package saygilis877007.a04;
import cgtools.Vec3;
/**
 * Created by home on 07.11.18.
 */
public interface Material {

    Vec3 emittedRadiance (Ray r, Hit h);
    Ray scatteredRay(Ray r, Hit h);
    Vec3 albedo (Ray r, Hit h);

}
