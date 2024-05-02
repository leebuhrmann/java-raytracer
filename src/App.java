import java.awt.Color;

import ModelPOJOs.Model;
import ModelPOJOs.Point3;
import ModelPOJOs.RayTracedModel;
import RenderingComponents.Frame;
import ScenePOJOs.Camera;
import ScenePOJOs.Scene;
import ScenePOJOs.Lights.Sunlight;
import ScenePOJOs.Shaders.AmbientShader;
import ScenePOJOs.Shaders.DiffuseShader;

public class App {
    public static void main(String[] args) throws Exception {
        // Create window
        Frame frame = new Frame(false);
        frame.setVisible(true);
        // end create window

        // Import blender obj model
        OBJParser modelParser = new OBJParser();
        // Model model = modelParser.loadModel("src/WavefrontFiles/cube.obj");
        // Model model = modelParser.loadModel("src/WavefrontFiles/untitled.obj");
        Model model = modelParser.loadModel("src/WavefrontFiles/star.obj");
        // end import.

        // Prepare Scene
        model.scaleModel(100);
        AmbientShader ambientShader = new AmbientShader(new Color(0,50,0));
        // DiffuseShader diffuseShader = new DiffuseShader(new Color(0,50,0), new Point3(0,0,1));
        RayTracedModel rayTracedModel = new RayTracedModel(model, ambientShader);
        Camera camera = new Camera(false, new Point3(0,0,100), new Point3(0,0,-1), new Point3(0,1,0), (float)Math.PI / 4);
        Sunlight sunlight = new Sunlight(255, 255, 255, new Point3(0,-1,0));
        Scene scene = new Scene(rayTracedModel, camera, sunlight);
        // end prepare scene

        // Render
        frame.render(scene);
    }
}
