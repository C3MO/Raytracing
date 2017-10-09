//
// Author: Henrik Tramberend <tramberend@beuth-hochschule.de>
//

package cgtools;

import cgtools.Mat4;
import cgtools.Vec3;

public class BoundingBox {
    final Vec3 min, max;

    public BoundingBox() {
        min = new Vec3(Double.POSITIVE_INFINITY);
        max = new Vec3(Double.NEGATIVE_INFINITY);
    }

    public BoundingBox(Vec3 min, Vec3 max) {
        this.min = min;
        this.max = max;
    }

    public BoundingBox extend(BoundingBox bb) {
        if (bb == null)
            return this;
        BoundingBox result = new BoundingBox();
        result.min.x = Math.min(min.x, bb.min.x);
        result.min.y = Math.min(min.y, bb.min.y);
        result.min.z = Math.min(min.z, bb.min.z);
        result.max.x = Math.max(max.x, bb.max.x);
        result.max.y = Math.max(max.y, bb.max.y);
        result.max.z = Math.max(max.z, bb.max.z);
        return result;
    }

    public BoundingBox extend(Vec3 p) {
        BoundingBox result = new BoundingBox();
        result.min.x = Math.min(min.x, p.x);
        result.min.y = Math.min(min.y, p.y);
        result.min.z = Math.min(min.z, p.z);
        result.max.x = Math.max(max.x, p.x);
        result.max.y = Math.max(max.y, p.y);
        result.max.z = Math.max(max.z, p.z);
        return result;
    }

    public BoundingBox splitLeft() {
        Vec3 size2 = Vec3.sub(max, min).div(2);
        if (size2.x >= size2.y && size2.x >= size2.z) {
            return new BoundingBox(min, new Vec3(min.x + size2.x, max.y, max.z));
        } else if (size2.y >= size2.x && size2.y >= size2.z) {
            return new BoundingBox(min, new Vec3(max.x, min.y + size2.y, max.z));
        } else {
            return new BoundingBox(min, new Vec3(max.x, max.y, min.z + size2.z));
        }
    }

    public BoundingBox splitRight() {
        Vec3 size2 = Vec3.sub(max, min).div(2);
        if (size2.x >= size2.y && size2.x >= size2.z) {
            return new BoundingBox(new Vec3(min.x + size2.x, min.y, min.z), max);
        } else if (size2.y >= size2.x && size2.y >= size2.z) {
            return new BoundingBox(new Vec3(min.x, min.y + size2.y, min.z), max);
        } else {
            return new BoundingBox(new Vec3(min.x, min.y, min.z + size2.z), max);
        }
    }

    public static BoundingBox transform(Mat4 xform, BoundingBox bb) {
        if (bb == null)
            return null;

        BoundingBox result = new BoundingBox();

        result = result.extend(xform.transformPoint(bb.min));
        result = result.extend(xform.transformPoint(new Vec3(bb.min.x, bb.min.y, bb.max.z)));
        result = result.extend(xform.transformPoint(new Vec3(bb.min.x, bb.max.y, bb.min.z)));
        result = result.extend(xform.transformPoint(new Vec3(bb.min.x, bb.max.y, bb.max.z)));
        result = result.extend(xform.transformPoint(new Vec3(bb.max.x, bb.min.y, bb.min.z)));
        result = result.extend(xform.transformPoint(new Vec3(bb.max.x, bb.min.y, bb.max.z)));
        result = result.extend(xform.transformPoint(new Vec3(bb.max.x, bb.max.y, bb.min.z)));
        result = result.extend(xform.transformPoint(bb.max));

        return result;
    }

    public boolean contains(Vec3 v) {
        return min.x <= v.x && min.y <= v.y && min.z <= v.z && max.x >= v.x && max.y >= v.y && max.z >= v.z;
    }

    public boolean contains(BoundingBox bb) {
        if (bb == null)
            return false;
        return min.x <= bb.min.x && min.y <= bb.min.y && min.z <= bb.min.z && max.x >= bb.max.x && max.y >= bb.max.y
                && max.z >= bb.max.z;
    }

    //
    // Adapted from
    // https://tavianator.com/cgit/dimension.git/tree/libdimension/bvh/bvh.c
    //
    public boolean intersect(Ray ray) {
        if (this.contains(ray.pointAt(ray.t0)))
            return true;
        if (this.contains(ray.pointAt(ray.t1)))
            return true;

        double dix = 1.0 / ray.d.x;
        double diy = 1.0 / ray.d.y;
        double diz = 1.0 / ray.d.z;

        double tx1 = (min.x - ray.x0.x) * dix;
        double tx2 = (max.x - ray.x0.x) * dix;

        double tmin = Math.min(tx1, tx2);
        double tmax = Math.max(tx1, tx2);

        double ty1 = (min.y - ray.x0.y) * diy;
        double ty2 = (max.y - ray.x0.y) * diy;

        tmin = Math.max(tmin, Math.min(ty1, ty2));
        tmax = Math.min(tmax, Math.max(ty1, ty2));

        double tz1 = (min.z - ray.x0.z) * diz;
        double tz2 = (max.z - ray.x0.z) * diz;

        tmin = Math.max(tmin, Math.min(tz1, tz2));
        tmax = Math.min(tmax, Math.max(tz1, tz2));

        return tmax >= tmin && ray.contains(tmin);
    }

    public Vec3 size() {
        return Vec3.sub(max, min);
    }

    public Vec3 center() {
        return Vec3.add(max, min).div(2);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BoundingBox))
            return false;
        if (o == this)
            return true;
        BoundingBox v = (BoundingBox) o;
        return min.equals(v.min) && max.equals(v.max);
    }

    @Override
    public String toString() {
        return String.format("(BBox: %s %s)", min, max);
    }
}
