package saygilis877007.a03;

import cgtools.Vec3;
/**
 * Created by home on 29.10.18.
 */
public class Ray {
    public Vec3 orgViktor;  // origin
    public Vec3 dirVektor;  // direction
    public double t0;
    public double t1;

    public Ray(Vec3 orgViktor, Vec3 dirVektor){
        this.orgViktor = orgViktor;
        this.dirVektor = Vec3.normalize(dirVektor);
    }

    public Ray(Vec3 orgViktor, Vec3 dirVektor, double t0, double t1){
        this.orgViktor = orgViktor;
        this.dirVektor = Vec3.normalize(dirVektor);
        this.t0 = t0;
        this.t1 = t1;
    }
     	public Vec3 pointAt(double t){
     		return Vec3.add(orgViktor, Vec3.multiply(t, dirVektor)); 
     	}
     	  	public boolean contains(double t) {
     	  		if(t0 <= t && t <= t1 ){
     	  			return true;
     	  		}else{
     	  			return false;
     	  		}
     	  		
}         	}