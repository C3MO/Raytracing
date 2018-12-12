package saygilis877007.a07;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by home on 06.11.18.
 */


public class Group implements Shape {

    private ArrayList<Shape> formList;

    Group(Shape... shapes) {
        this.formList = new ArrayList<>();
        formList.addAll(Arrays.asList(shapes));
    }

    ArrayList<Shape> getFormList() {
        return formList;
    }

    public void setFormList(ArrayList<Shape> formList) {
        this.formList = formList;
    }

    void addShape(Shape shape) {
        this.formList.add(shape);
    }

    @Override
    public Hit intersect(Ray r) {
        Hit hit = null;

        for (Shape shape: formList) {
            Hit hitShape = shape.intersect(r);
            if (hit == null) {
                if (hitShape != null) hit = hitShape;
            } else if (hitShape != null && hitShape.t < hit.t) hit = hitShape;
        }

        return hit;
    }

}
