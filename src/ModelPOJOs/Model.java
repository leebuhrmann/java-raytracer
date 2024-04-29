package ModelPOJOs;

import java.util.ArrayList;
import RenderingComponents.Pixel;

public class Model {
    
    private ArrayList<Point3> vs;
    private ArrayList<Point3> vns;
    private ArrayList<Point2> vts;
    private ArrayList<int[][]> f;
    private ArrayList<Triangle> t;

    public Model(ArrayList<Point3> vs, ArrayList<Point3> vns, ArrayList<Point2> vts, ArrayList<int[][]> f) {
        this.vs = vs;
        this.vns = vns;
        this.vts = vts;
        this.f = f;
        this.t = new ArrayList<>();
        constructTriangles();
    }

    public void setVS(ArrayList<Point3> vs) {
        this.vs = vs;
        constructTriangles();
    }

    public ArrayList<Point3> getVS() {
        return this.vs;
    }

    public void setVNS(ArrayList<Point3> vns) {
        this.vns = vns;
    }

    public ArrayList<Point3> getVNS() {
        return this.vns;
    }

    public void setVTS(ArrayList<Point2> vts) {
        this.vts = vts;
    }

    public ArrayList<Point2> getVTS() {
        return this.vts;
    }

    public void constructTriangles() {
        this.t.clear();
        for (int[][] fData : this.f) {
            ArrayList<Point3> points = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                points.add(this.vs.get(fData[i][0] - 1));
            }
            this.t.add(new Triangle(points.get(0), points.get(1), points.get(2)));
        }
    }

    public ArrayList<Triangle> getTriangles() {
        return this.t;
    }

    /**
     * Deprecated
     */
    public ArrayList<Pixel> getPixels() {
        ArrayList<Pixel> pixels = new ArrayList<>();
        for (Triangle tri : this.t) {
            pixels.addAll(tri.getPixels());
        }
        return pixels;
    }

    public void scaleModel(int scale) {
        ArrayList<Point3> scaledVs = new ArrayList<>();

        for (Point3 v : this.vs) {
            scaledVs.add(v.getScale(scale));
        }

        this.vs = scaledVs;
        constructTriangles();
    }

    public void shiftX(int x) {
        for (Point3 v : this.vs) {
            v.setX(v.getX() + x);
        }
        constructTriangles();
    }

    public void shiftY(int y) {
        for (Point3 v : this.vs) {
            v.setY(v.getY() + y);
        }
        constructTriangles();
    }

    public Collision getIntersect(Point3 origin, Point3 direction) {
        float closest = Float.MAX_VALUE;
        Collision best = null;

        // find the closest intersecction amongst all triangles
        for (Triangle tri: this.t) {
            Collision intersection = tri.getCollision(origin, direction);
            if (intersection != null
                && best == null
                || intersection.getTime() < closest
                ) {
                
                    best = intersection;
                    closest = intersection.getTime();
            }
        }

        // return best intersection or null if no intersection exists
        if (best != null) {
            return best;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(); 
        for (int i = 0; i < t.size(); i++) {
            builder.append(String.format("Triangle #%d: %s\n", i, t.get(i).toString()));
        }
        return builder.toString(); 
    }
}
