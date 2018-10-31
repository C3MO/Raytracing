package saygilis877007.a03;

import cgtools.Vec3;
/**
 * Created by home on 29.10.18.
 */
public class Ray {
    public Vec3 x0;  // Ursprung der Richtung
    public Vec3 d;  // Normalisierte Richtung
    public double tmin;
    public double tmax;

    public Ray(Vec3 x0, Vec3 d){
        this.x0 = x0;
        this.d = Vec3.normalize(d);
    }

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
     	  		
}         	}