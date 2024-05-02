package ScenePOJOs.Shaders;

import java.awt.Color;

public class AmbientShader extends Shader {
    
    public AmbientShader(Color color) {
        super(color);
    }

    @Override
    public Color illuminateModel() {
        return this.color;
    }
}
