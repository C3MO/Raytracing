/**
 * A simple 3D vector class. Vector objects are muatble for performance reasons. Be careful!
 *
 * @author henrik
 */
package cgtools;

public class Vec3 {
    public double x, y, z;

    /** Creates a new vector with the given coordinates */
    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /** Creates a copy of the given vector. */
    public Vec3(Vec3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    /** Adds the given vector to this vector. This vector is modified. */
    public Vec3 add(Vec3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    /** Adds the given vectors and returns a newly allocated vector. */
    public static Vec3 add(Vec3 a, Vec3 b) {
        return new Vec3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    /** Substracts the given vector from this vector. This vector is modified! */
    public Vec3 sub(Vec3 v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    /** Subtracts the first vector from the second and returns a newly allocated vector. */
    public static Vec3 sub(Vec3 a, Vec3 b) {
        return new Vec3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    /** Multiplies the given vector componentwise to this vector. This vector is modified! */
    public Vec3 mul(Vec3 v) {
        x *= v.x;
        y *= v.y;
        z *= v.z;
        return this;
    }

    /** Multiplies the given vectors and returns a newly allocated result vector. */
    public static Vec3 mul(Vec3 a, Vec3 b) {
        return new Vec3(a.x * b.x, a.y * b.y, a.z * b.z);
    }

    /** Multiplies this vector with the given scalar. This vector is modified! */
    public Vec3 mul(double s) {
        x *= s;
        y *= s;
        z *= s;
        return this;
    }

    /** Multplies the given vector with the scalar value. */
    public static Vec3 mul(double s, Vec3 a) {
        return new Vec3(s * a.x, s * a.y, s * a.z);
    }

    /** Divides this vector by the provided scalar value. This vector is modified! */
    public Vec3 div(double s) {
        x /= s;
        y /= s;
        z /= s;
        return this;
    }

    /** Divides the given vector by the scalar value. */
    public static Vec3 div(double s, Vec3 a) {
        return new Vec3(a.x / s, a.y / s, a.z / s);
    }

    /** Calculates the dot product. */
    public double dot(Vec3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    /** Calculates the dot product. */
    public static double dot(Vec3 a, Vec3 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    /**
     * Calculates the dot product between this vector and the provided vector. This vector is
     * modified!
     */
    public Vec3 cross(Vec3 v) {
        double xn = y * v.z - z * v.y;
        double yn = z * v.x - x * v.z;
        double zn = x * v.y - y * v.x;
        x = xn;
        y = yn;
        z = zn;
        return this;
    }

    /** Calculates the dot product between the two provided vectors. */
    public static Vec3 cross(Vec3 a, Vec3 b) {
        return new Vec3(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
    }

    /** Returns the lenght of this vector. */
    public double len() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /** Returns the lenght of the provided vector. */
    public static double len(Vec3 a) {
        return a.len();
    }

    /** Returns the squared lenght of this vector. */
    public double sqrlen() {
        return x * x + y * y + z * z;
    }

    /** Returns the squared lenght of the provided vector. */
    public static double sqrlen(Vec3 a) {
        return a.sqrlen();
    }

    /** Normalized this vector. This vector is modified */
    public void norm() {
        div(len());
    }

    /** Normalizes the provided vector */
    public static Vec3 norm(Vec3 a) {
        return new Vec3(a).div(a.len());
    }

    @Override
    public String toString() {
        return String.format("(Vec3: %.2f %.2f %.2f)", x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vec3)) return false;
        if (o == this) return true;
        Vec3 v = (Vec3) o;
        return v.x == x && v.y == y && v.z == z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Double.valueOf(x).hashCode();
        result = prime * result + Double.valueOf(y).hashCode();
        result = prime * result + Double.valueOf(z).hashCode();
        return result;
    }
}
