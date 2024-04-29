package ScenePOJOs.Lights;

import java.awt.Color;

import ModelPOJOs.Point3;

public class Sunlight extends Light{
    
    public Sunlight(int r, int g, int b, Point3 direction) {
        super(r,g,b,direction);
    }

    @Override
    public Color illumination(Point3 origin) {
        return this.color;
    }
}
