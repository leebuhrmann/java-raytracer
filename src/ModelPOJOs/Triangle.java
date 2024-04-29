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
}
