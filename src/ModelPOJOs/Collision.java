package ModelPOJOs;

public class Collision {
    private float timeToCollision;
    private Point3 collisionLocation;
    private Point3 normalAtCollision;
    
        public Collision(float timeToCollision, Point3 collisionLocation, Point3 normalAtCollision) {
            this.timeToCollision = timeToCollision;
            this.collisionLocation = collisionLocation;
            this.normalAtCollision = normalAtCollision;
        }

    public float getTime() {
        return this.timeToCollision;
    }

    public Point3 getLocation() {
        return this.collisionLocation;
    }

    public Point3 getNormal() {
        return this.normalAtCollision;
    }
}
