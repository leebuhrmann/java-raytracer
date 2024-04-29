package ScenePOJOs.Shaders;

import java.awt.Color;

public class AmbientShader extends Shader {
    
    public AmbientShader(int r, int g, int b) {
        super(r,g,b);
    }

    @Override
    public Color illuminateModel() {
        return this.color;
    }
}
