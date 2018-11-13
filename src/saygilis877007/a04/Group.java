package saygilis877007.a04;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by home on 06.11.18.
 */
public class Group implements Shape{
    private final ArrayList<Shape> formList;

    Group(Shape... shapes) {
        this.formList = new ArrayList<>();
        formList.addAll(Arrays.asList(shapes));
    }

    @Override
    public Hit intersect(Ray r) {
        Hit hit = null;

        for (Shape s: formList) {               // Anzahl der formen in einer for each schleife (0-179 in diesem Fall
            Hit hitShape = s.intersect(r);      //tmin, tmax und material von Plane s & Ray r (x0, d, tmin und tmax) werden intersectet (Hit ist immer noch null
            if (hit == null) {
                if (hitShape != null) hit = hitShape;
            } else {
                if (hitShape != null && hitShape.t < hit.t) hit = hitShape;
            }
        }

        return hit;
    }

}
