package RenderingComponents;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.swing.Timer;

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
    private boolean perspective;
    private boolean color;
    private int tick;

    public Frame(boolean animate, boolean perspective, boolean color) {
        setTitle("Lee's Rasterizer");
        setSize(500,500);
        setLocationRelativeTo(null); // null tells this method to center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.tick = 0;
        this.scene = null;
        this.animate = animate;
        this.perspective = perspective;
        this.color = color;

        panel = new Panel();
        add(panel);
    }

    public void render(Scene scene) {
        this.scene = scene;
        center();
        Timer timer = new Timer(16, e -> {
            ArrayList<Pixel> buffer = prepareBuffer();
            panel.setBuffer(buffer);
            panel.repaint();
            this.tick++;
        });
        timer.start();
    }

    private ArrayList<Pixel> prepareBuffer() {
        ArrayList<Pixel> buffer = new ArrayList<>();

        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                buffer.add(getPixel(x,y));
            }
        }

        return buffer;
    }

    private Pixel getPixel(int x, int y) {
        int startX = x - getWidth() / 2;
        int startY = y - getHeight() / 2;
        Point3 origin = this.
    }

    // public void render(Model model) {
    //     this.model = model;
    //     center();
    //     Timer timer = new Timer(16, e -> {
    //         ArrayList<Pixel> buffer = prepareBuffer();
    //         panel.setBuffer(buffer);
    //         panel.repaint();
    //         this.tick++;
    //     });
    //     timer.start();
    // }

    // private ArrayList<Pixel> prepareBuffer() {
    //         ArrayList<Pixel> buffer = new ArrayList<>();

    //         for (Triangle t : this.model.getTriangles()) {
    //             ArrayList<Pixel> tBuffer = new ArrayList<>();
    //             Point3 one = t.getOne().clone();
    //             Point3 two = t.getTwo().clone();
    //             Point3 three = t.getThree().clone();

    //             if (animate) {
    //                 float angle = (float)tick / 20;

    //                 float[] out = rotate(one.getX(), one.getZ(), angle);
    //                 one.setX(out[0]);
    //                 one.setZ(out[1]);
          
    //                 out = rotate(two.getX(), two.getZ(), angle);
    //                 two.setX(out[0]);
    //                 two.setZ(out[1]);
          
    //                 out = rotate(three.getX(), three.getZ(), angle);
    //                 three.setX(out[0]);
    //                 three.setZ(out[1]);
    //             }

    //             float cameraFromZ = (this.getWidth() / 2) + (this.getWidth() / 8) + 5;
    //             float cameraToZ = (this.getWidth() / 8) + 5;
    //             float cameraLengthZ = cameraFromZ - cameraToZ;

    //             ArrayList<Point3> points = new ArrayList<>();
    //             points.add(one);
    //             points.add(two);
    //             points.add(three);

    //             if (perspective) {
    //                 float _bw = this.getWidth() / 2;
    //                 for (Point3 p : points) {
    //                     float distanceZ = cameraFromZ - p.getZ();

    //                     float offsetX = p.getX() - _bw;
    //                     float ratioX = offsetX / distanceZ;
    //                     float newValueX = ratioX * cameraLengthZ;
    //                     p.setX(newValueX + _bw);

    //                     float offsetY = p.getY() - _bw;
    //                     float ratioY = offsetY / distanceZ;
    //                     float newValueY = ratioY * cameraLengthZ;
    //                     p.setY(newValueY + _bw);
    //                 }
    //             }

    //             Point3 twoOne = two.getSubtract(one);
    //             Point3 threeOne = three.getSubtract(one);
    //             Point3 cross = twoOne.getCross(threeOne);
    //             Point3 normal = cross.getNormalized();

    //             tBuffer.addAll(new Edge(one,two).getPixels());
    //             tBuffer.addAll(new Edge(two,three).getPixels());
    //             tBuffer.addAll(new Edge(three,one).getPixels());

    //             Point3 toSun = new Point3(1,0,1).getNormalized();
    //             float diffuse = normal.getDot(toSun);
    //             diffuse = Math.max(0,diffuse);

    //             float r = 255;
    //             float g = 255;
    //             float b = 255;

    //             r *= diffuse;
    //             g *= diffuse;
    //             b *= diffuse;

    //             int ambient = 50;
    //             r += ambient;
    //             g += ambient;
    //             b += ambient;

    //             r = Math.max(0, Math.min(255, r));
    //             g = Math.max(0, Math.min(255, g));
    //             b = Math.max(0, Math.min(255, b));

    //             if (color) {
    //                 int minY = tBuffer.stream()
    //                                     .min(Comparator.comparing(Pixel::getY))
    //                                     .orElseThrow(NoSuchElementException::new)
    //                                     .getY();
    //                 int maxY = tBuffer.stream()
    //                                     .max(Comparator.comparing(Pixel::getY))
    //                                     .orElseThrow(NoSuchElementException::new)
    //                                     .getY();

    //                 for (int y = minY; y <= maxY; y++) {
    //                     final int finalY = y;
    //                     ArrayList<Pixel> _buffer = tBuffer.stream()
    //                                                         .filter(pixel -> pixel.getY() == finalY)
    //                                                         .collect(Collectors.toCollection(ArrayList::new));

    //                     int minX = _buffer.stream()
    //                                         .min(Comparator.comparing(Pixel::getX))
    //                                         .orElseThrow(NoSuchElementException::new)
    //                                         .getX();
    //                     int maxX = _buffer.stream()
    //                                         .max(Comparator.comparing(Pixel::getX))
    //                                         .orElseThrow(NoSuchElementException::new)
    //                                         .getX();     
                                            
    //                     for (int x = minX; x <= maxX; x++) {
    //                         tBuffer.add(new Pixel(x,y,Math.round(r),Math.round(g),Math.round(b)));
    //                     }
    //                 }
    //             }
    //             //end add color
    //             buffer.addAll(tBuffer);
    //         }

    //         return buffer;
    // }

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
