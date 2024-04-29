package ModelPOJOs;

public class Point3 {
        
    private float x;
    private float y;
    private float z;

    public Point3 (float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return this.z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Point3 getScale(float scaler) {
        return new Point3(this.x * scaler, this.y * scaler, this.z * scaler);
    }

    public Point3 getSubtract(Point3 p) {
        return new Point3(x - p.getX(), y - p.getY(), z - p.getZ());
    }

    public Point3 getAdd(Point3 p) {
        return new Point3(x + p.getX(), y + p.getY(), z + p.getZ());
    }

    public Point3 getCross(Point3 p) {
        float x = (this.y * p.getZ()) - (this.z * p.getY());
        float y = (this.z * p.getX()) - (this.x * p.getZ());
        float z = (this.x * p.getY()) - (this.y * p.getX());
        return new Point3(x, y, z);
    }

    public double getLength() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    public Point3 getNormalized() {
        double length = getLength();
        return new Point3((float)(this.x / length), (float)(this.y / length), (float)(this.z / length));
    }

    public float getDot(Point3 p) {
        return (this.x * p.getX()) + (this.y * p.getY()) + (this.z * p.getZ());
    }

    public Point3 clone() {
        return new Point3(this.x, this.y, this.z);
    }
}
