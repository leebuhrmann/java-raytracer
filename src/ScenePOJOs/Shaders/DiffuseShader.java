package ScenePOJOs.Shaders;

import java.awt.Color;

import ModelPOJOs.Point3;
import ModelPOJOs.RayTracedModel;

public class DiffuseShader extends Shader {

    private Point3 lightDirection;

    public DiffuseShader(Color color, Point3 lightDirection) {
        super(color);
        this.lightDirection = lightDirection;
    }

    public Color illuminateModel(Point3 rayFrom, Point3 normal, RayTracedModel rayTracedModel) {
        float coefficient = normal.getDot(this.lightDirection);
        if (coefficient < 0) {
            coefficient = 0;
        }
        
        return new Color(this.color.getRed() * coefficient, this.color.getGreen() * coefficient, this.color.getBlue() * coefficient);
    }

    @Override
    public Color illuminateModel() {
        throw new UnsupportedOperationException("Unimplemented method 'illuminateModel'");
    }
    
}
