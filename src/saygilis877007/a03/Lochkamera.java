package saygilis877007.a03;
import cgtools.Vec3;
/**
 * Created by home on 29.10.18.
 */

public class Lochkamera {

    double winkel;          //Kameraöffnungswinkel
    int h;                  // height der Bildgröße
    int w;                  // width der Bildgröße

    public Lochkamera(double winkel, int h, int w){
        this.h = h;
        this.w = w;
        this.winkel = winkel;
    }

    public Ray generateRay(double x, double y){
        double x1 = x-w/2;
        double y1 = h/2-y;
        double z1 = -1*(w/2)/(Math.tan(winkel/2));

        return new Ray(new Vec3(0,0,0), new Vec3(x1, y1, z1),0,Double.POSITIVE_INFINITY);

    }

}
