package saygilis877007.a02;

import cgtools.Vec3;

/**
 * Created by home on 17.10.18.
 */
public class Circle implements Comparable <Circle> {

    public double x;
    public double y;
    public double radius;
    public Vec3 circleColor;

    public Circle(double x, double y, double radius, Vec3 color){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.circleColor = color;
    }
    public double getX(){
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public Vec3 getCircleColor() {
        return circleColor;
    }

    @Override
    public int compareTo(Circle cc) {
        return new Double(radius).compareTo(cc.radius);
    }
}
