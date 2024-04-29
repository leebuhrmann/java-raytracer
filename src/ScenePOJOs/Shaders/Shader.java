package ScenePOJOs.Shaders;

import java.awt.Color;

public abstract class Shader {
    Color color;
    
    public Shader(int r, int g, int b) {
        this.color = new Color(r,g,b);
    }

    public abstract Color illuminateModel();
}
