package ModelPOJOs;

public class Point2 {
    
    private float x;
    private float y;

    public Point2(float x, float y) {
        this.x = x;
        this.y = y;
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

    public Point2 scalePoint2(int scale) {
        return new Point2(this.x * scale, this.y * scale);
    }
}
