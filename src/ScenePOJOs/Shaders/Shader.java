package ScenePOJOs.Shaders;

import java.awt.Color;

public abstract class Shader {
    Color color;
    
    public Shader(Color color) {
        this.color = color;
    }
    public abstract Color illuminateModel();
}
