package ModelPOJOs;

import RenderingComponents.Pixel;
import java.util.ArrayList;

public class Triangle {
    
    private Point3 one;
    private Point3 two;
    private Point3 three;

    public Triangle(Point3 one, Point3 two, Point3 three) {
        this.one = one;
        this.two = two;
        this.three = three;
    }

    public Point3 getOne() {
        return this.one;
    }
    
    public void setOne(Point3 one) {
        this.one = one;
    }

    public Point3 getTwo() {
        return this.two;
    }

    public void settwo(Point3 two) {
        this.two = two;
    }

    public Point3 getThree() {
        return this.three;
    }

    public void setThree(Point3 three) {
        this.three = three;
    }

    public Point3[] getPoints() {
        return new Point3[]{one,two,three};
    }

    public Point3 getOneTwo() {
        return this.one.getSubtract(this.two).getNormalized();
    }

    public Point3 getTwoThree() {
        return this.two.getSubtract(this.three).getNormalized();
    }

    public Point3 getThreeOne() {
        return this.three.getSubtract(this.one).getNormalized();
    }

    public Point3 getThreeTwo() {
        return this.three.getSubtract(this.two).getNormalized();
    }

    public Point3 getTwoOne() {
        return this.two.getSubtract(this.one).getNormalized();
    }

    public Point3 getOneThree() {
        return this.one.getSubtract(this.three).getNormalized();
    }

    /**
     * Deprecated
     */
    public ArrayList<Pixel> getPixels() {
        ArrayList<Pixel> pixels = new ArrayList<>();
        pixels.addAll(new Edge(one,two).getPixels());
        pixels.addAll(new Edge(two,three).getPixels());
        pixels.addAll(new Edge(three,one).getPixels());
        return pixels;
    }

    public String toString() {
        return String.format("one: (%f,%f), two: (%f,%f), three: (%f,%f)", 
                            one.getX(), one.getY(), two.getX(), two.getY(), three.getX(), three.getY());
    }

    public Triangle scaleTriangle(int scale) {
        return new Triangle(one.getScale(scale), two.getScale(scale), three.getScale(scale));
    }

    public Collision getCollision(Point3 origin, Point3 direction) {
        Point3 ABC = this.getOneTwo().getCross(this.getThreeTwo()).getNormalized();

        if (ABC.getDot(direction) == 0) {
            return null;
        }

        float D = -(ABC.getDot(this.one)); 
        float timeToCollision = (-D - origin.getDot(ABC)) / (direction.getDot(ABC));
        if (timeToCollision <= 0) return null;
        Point3 collisionLocation = origin.getAdd(direction.getScale(timeToCollision));

        Point3 oneCollision = collisionLocation.getSubtract(this.one).getNormalized();
        Point3 twoCollision = collisionLocation.getSubtract(this.two).getNormalized();
        Point3 threeCollision = collisionLocation.getSubtract(this.three).getNormalized();

        Point3 a = this.getOneTwo().getCross(oneCollision);
        Point3 b = this.getTwoThree().getCross(twoCollision);
        Point3 c = this.getThreeOne().getCross(threeCollision);

        float i = a.getDot(ABC);
        float j = b.getDot(ABC);
        float k = c.getDot(ABC);

        if (i < 0 || j < 0 || k < 0) {
            // System.out.printf("(i,j,k) = (%f,%f,%f)\n", i, j, k);
            // System.out.println("no collision...");
            return  null;
        }
        else {
            // System.out.printf("(i,j,k) = (%f,%f,%f)\n", i, j, k);
            // System.out.println("collision!");
            return new Collision(timeToCollision, collisionLocation, ABC);
        }
    }
}
