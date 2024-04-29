package ModelPOJOs;

import RenderingComponents.Pixel;
import java.util.ArrayList;

public class Edge {
    
    private Point3 one;
    private Point3 two;

    public Edge(Point3 one, Point3 two) {
        this.one = one;
        this.two = two;
    }

    // Bresenham's line algorithm: Transcribed from Wikipedia
    public ArrayList<Pixel> getPixels() {
        return plotLine(
            Math.round(one.getX()),
            Math.round(one.getY()),
            Math.round(two.getX()),
            Math.round(two.getY())
        );
    }

    private ArrayList<Pixel> plotLine(int x0, int y0, int x1, int y1) {
        ArrayList<Pixel> pixels = new ArrayList<>();
        
        if (Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
            if (x0 > x1) {
                pixels = plotLineLow(x1, y1, x0, y0);
            }
            else {
                pixels = plotLineLow(x0, y0, x1, y1);
            }
        }
        else {
            if (y0 > y1) {
                pixels = plotLineHigh(x1, y1, x0, y0);
            }
            else {
                pixels = plotLineHigh(x0, y0, x1, y1);
            }
        }

        return pixels;
    }

    private ArrayList<Pixel> plotLineLow(int x0, int y0, int x1, int y1) {
        ArrayList<Pixel> pixels = new ArrayList<>();
        
        int dx = x1 - x0;
        int dy = y1 - y0;
        int yi = 1;
        if (dy < 0) {
            yi = -1;
            dy = -dy;
        }
        int D = 2*dy - dx;
        int y = y0;

        for (int x  = x0; x <= x1; x++) {
            pixels.add(new Pixel(x,y,0,0,0));
            if (D > 0) {
                y = y + yi;
                D = D + (2 * (dy - dx));
            }
            else {
                D = D + 2*dy;
            }
        }

        return pixels;
    }

    private ArrayList<Pixel> plotLineHigh(int x0, int y0, int x1, int y1) {
        ArrayList<Pixel> pixels = new ArrayList<>();
        
        int dx = x1 - x0;
        int dy = y1 - y0;
        int xi = 1;
        if (dx < 0) {
            xi = -1;
            dx = -dx;
        }
        int D = 2*dx - dy;
        int x = x0;

        for (int y = y0; y <= y1; y++) {
            pixels.add(new Pixel(x,y,0,0,0));
            if (D > 0) {
                x = x + xi;
                D = D + (2 * (dx - dy));
            }
            else {
                D = D + 2*dx;
            }
        }

        return pixels;
    }
}
