package RenderingComponents;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.swing.Timer;

import ModelPOJOs.Collision;
import ModelPOJOs.Edge;
import ModelPOJOs.Model;
import ModelPOJOs.Point3;
import ModelPOJOs.Triangle;
import ScenePOJOs.Scene;

import java.util.Map;

import javax.swing.JFrame;

public class Frame extends JFrame{
    
private Panel panel;

    private Scene scene;
    private boolean animate;
    private int tick;

    public Frame(boolean animate) {
        setTitle("Lee's Rasterizer");
        setSize(500,500);
        setLocationRelativeTo(null); // null tells this method to center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.tick = 0;
        this.scene = null;
        this.animate = animate;

        panel = new Panel();
        add(panel);
    }

    // public void render(Scene scene) {
    //     this.scene = scene;
    //     center();
    //     Timer timer = new Timer(16, e -> {
    //         ArrayList<Pixel> buffer = prepareBuffer();
    //         panel.setBuffer(buffer);
    //         panel.repaint();
    //         this.tick++;
    //     });
    //     timer.start();
    // }

    public void render(Scene scene) {
        this.scene = scene;
        // center();

        ArrayList<Pixel> buffer = prepareBuffer();
        panel.setBuffer(buffer);
        panel.repaint();
    }

    private ArrayList<Pixel> prepareBuffer() {
        ArrayList<Pixel> buffer = new ArrayList<>();

        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                System.out.printf("#####################################\ngetPixel(%d,%d)\n#####################################\n\n",x,y);
                buffer.add(getPixel(x,y));
                if (x == 1) return buffer;
            }
            System.out.println("y = " + y);
        }
        return buffer;
    }

    private Pixel getPixel(int x, int y) {
        int startX = x - getWidth() / 2;
        int startY = y - getHeight() / 2;
        Point3 origin = this.scene.getCamera().getOrigin(startX, startY);
        Point3 direciton = this.scene.getCamera().getDirection(startX / (getWidth() / 2), startY / (getHeight() / 2));

        Collision closestCollision = this.scene.getRayTracedModel().getModel().getIntersect(origin, direciton);
        if (closestCollision == null) {
            return new Pixel(x,y,255,255,255);
        }
        else {
            Color color = this.scene.getRayTracedModel().getShader().illuminateModel();
            return new Pixel(x, y, color);
        }
    }

    private void center() {
        int shiftX = Math.round(this.getWidth() / 2);
        int shiftY = Math.round(this.getHeight() / 2);
        this.scene.getRayTracedModel().getModel().shiftX(shiftX);
        this.scene.getRayTracedModel().getModel().shiftY(shiftY);
    }

    private float[] rotate(float x, float y, float angle) {
        float _bw = this.getWidth() / 2;
        float _y = y;
        float _x = x - _bw;
        float _r = (float)Math.sqrt(Math.pow(_x,2) + Math.pow(_y,2));
        float _tan = (float)Math.atan2(_y,_x);
        float _angle = _tan + angle;
        float _x2 = (float)Math.cos(_angle) * _r;
        float _y2 = (float)Math.sin(_angle) * _r;
        return new float[]{_x2 + _bw, _y2};
    }

    public void printColorCounts(ArrayList<Pixel> buffer) {
        Map<String, Integer> colorCounts = new HashMap<>();

        for (Pixel pixel : buffer) {
            // Create a string representation of the color combination
            String color = pixel.getR() + "," + pixel.getG() + "," + pixel.getB();
            colorCounts.put(color, colorCounts.getOrDefault(color, 0) + 1);
        }

        // Print each color and its count
        for (Map.Entry<String, Integer> entry : colorCounts.entrySet()) {
            System.out.println("Color: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }
}
