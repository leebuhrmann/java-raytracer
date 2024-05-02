package RenderingComponents;

import java.awt.Color;

public class Pixel {

    private int x;
    private int y;
    private int r;
    private int g;
    private int b;

    public Pixel(int x, int y, int r, int g, int b) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
    }
    
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return this.r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return this.g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return this.b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Color getColor() {
        return new Color(this.r,this.g,this.b);
    }

    public String toString() {
        return String.format("(%d,%d),(%d,%d,%d)", this.x, this.y, this.r, this.g, this.b);
    }
}
