package ScenePOJOs;

import ModelPOJOs.RayTracedModel;
import ScenePOJOs.Lights.Light;

public class Scene {
    private RayTracedModel rayTracedModel; // TODO: turn into a list of models
    private Camera camera;
    private Light lights; // TODO: turn into a list of lights

    public Scene(RayTracedModel rayTracedModel, Camera camera, Light lights) {
        this.rayTracedModel = rayTracedModel;
        this.camera = camera;
        this.lights = lights;
    }

    public RayTracedModel getRayTracedModel() {
        return this.rayTracedModel;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public Light getLights() {
        return this.lights;
    }
}
