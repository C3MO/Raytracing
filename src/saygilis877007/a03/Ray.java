package saygilis877007.a03;

import cgtools.Vec3;
/**
 * Created by home on 29.10.18.
 */
public class Ray {
    public final Vec3 x0;  // Ursprung der Richtung
    public final Vec3 d;  // Normalisierte Richtung
    public final double tmin, tmax;


    public Ray(Vec3 x0, Vec3 d, double tmin, double tmax){
        this.x0 = x0;
        this.d = Vec3.normalize(d);
        this.tmin = tmin;
        this.tmax = tmax;
    }
     	public Vec3 pointAt(double t){
     		return Vec3.add(x0, Vec3.multiply(t, d));
     	}
     	  	public boolean contains(double t) {
     	  		if(tmin <= t && t <= tmax ){
     	  			return true;
     	  		}else{
     	  			return false;
     	  		}
     	  		
}
}