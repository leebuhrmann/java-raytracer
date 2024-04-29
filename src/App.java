import ModelPOJOs.Model;
import RenderingComponents.Frame;

public class App {
    public static void main(String[] args) throws Exception {
        // Create window
        Frame frame = new Frame(false, true, true);
        frame.setVisible(true);
        // end create window

        // Import blender obj model
        OBJParser modelParser = new OBJParser();
        // Model model = modelParser.loadModel("src/WavefrontFiles/cube.obj");
        // Model model = modelParser.loadModel("src/WavefrontFiles/untitled.obj");
        Model model = modelParser.loadModel("src/WavefrontFiles/star.obj");
        model.scaleModel(100);
        // end import.

        // Render
        frame.render(model);
    }
}
