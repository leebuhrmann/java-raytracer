package ModelPOJOs;

import ScenePOJOs.Shaders.Shader;

public class RayTracedModel {
    private Model model;
    private Shader shader;

    public RayTracedModel(Model model, Shader shader) {
        this.model = model;
        this.shader = shader;
    }

    public Model getModel() {
        return this.model;
    }

    public Shader getShader() {
        return this.shader;
    }
}
