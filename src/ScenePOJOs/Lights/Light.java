package ScenePOJOs.Lights;

import java.awt.Color;

import ModelPOJOs.Point3;

public abstract class Light {
    Color color;
    Point3 direction;

    public Light(int r, int g, int b, Point3 direction) {
        this.color = new Color(r,g,b);
        this.direction = direction;
    }

    public abstract Color illumination(Point3 origin);
}
