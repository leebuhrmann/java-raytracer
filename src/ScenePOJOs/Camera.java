package ScenePOJOs;

import ModelPOJOs.Point3;

public class Camera {
    private boolean type;
    private Point3 origin;
    private Point3 direction;
    private Point3 up;
    private float angle;

    public Camera(boolean type, Point3 origin, Point3 direction, Point3 up, float angle) {
        this.type = type;
        this.origin = origin;
        this.direction = direction;
        this.up = up;
        this.angle = angle;
    }

    public Point3 getOrigin(int x, int y) {
        if (type) { // true == perspective camera 
            return this.origin;
        }
        else { // false == orthographic
            return new Point3(x,y,this.origin.getZ());
        }
    }

    public Point3 getDirection(int x, int y) {
        if (type) { // true == perspective camera
            Point3 right = this.direction.getCross(this.up).getNormalized();
            Point3 _up = right.getCross(this.direction).getNormalized(); // this shouldnt be needed i think?
            
            // System.out.println("up: " + up + " _up: " + _up);

            float cos = (float)Math.cos(this.angle); // adj over hypot
            float sin = (float)Math.sin(this.angle); // opp over hypot

            Point3 xOffset = right.getScale(x).getScale(sin);
            Point3 yOffset = up.getScale(y).getScale(sin);

            Point3 pDirection = this.direction.getScale(cos).getAdd(xOffset).getAdd(yOffset).getNormalized(); // calculated direction with perspective
            return pDirection;
        }
        else { // false == orthographic
            return this.direction;
        }
    }
}
