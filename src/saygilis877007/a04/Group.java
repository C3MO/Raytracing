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

        for (Shape s: formList) {
            Hit hitShape = s.intersect(r);
            if (hit == null) {
                if (hitShape != null) hit = hitShape; //treffer wird abgespeichert
            } else {
                if (hitShape != null && hitShape.t < hit.t) hit = hitShape; //treffer wird nur dann abgespeichert, wenn der neue treffpunkt kleineeres t hat
            }
        }

        return hit; // null, wenn nie ein shape getroffen wurde, ansonsten hit vom jeweiligen shape
    }

}
