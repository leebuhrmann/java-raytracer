package RenderingComponents;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Panel extends JPanel {
 
    private ArrayList<Pixel> buffer;

    public Panel() {
        this.buffer = new ArrayList<Pixel>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBuffer(g);
    }

    private void drawBuffer(Graphics g) {
        for (Pixel p : buffer) {
            drawPixel(g, p);
        }
    }

    private void drawPixel(Graphics g, Pixel pixel) {
        int x = pixel.getX();
        int y = pixel.getY();
        Color color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
        g.setColor(color);
        g.fillRect(x, y, 1,1);
    }

    public void setBuffer(ArrayList<Pixel> buffer) {
        this.buffer = buffer;
    }

    public ArrayList<Pixel> getBuffer() {
        return this.buffer;
    }
    
}
