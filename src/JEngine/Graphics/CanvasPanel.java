package JEngine.Graphics;

import JEngine.Objects.IGameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CanvasPanel extends JPanel {

    private int mouse_x = 0;
    private int mouse_y = 0;
    private Graphics graphics;

    public ArrayList<IGameObject> draw_queue = new ArrayList<IGameObject>();

    public CanvasPanel(int width, int height){
        System.out.println("Canvas Initialized");
    }

    public int getMouse_y() {
        return mouse_y;
    }

    public void setMouse_y(int mouse_y) {
        this.mouse_y = mouse_y;
    }

    public int getMouse_x() {
        return mouse_x;
    }

    public void setMouse_x(int mouse_x) {
        this.mouse_x = mouse_x;
    }

    public void setMouse(int mouse_x, int mouse_y){
        this.mouse_x = mouse_x;
        this.mouse_y = mouse_y;
    }

    public void Draw(){
        repaint();
    }

    public void Clear(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        graphics = g;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //g2d.setColor(Color.BLACK);
        //g2d.fillRect(0, 0, getSize().width, getSize().height);

        for (IGameObject obj : draw_queue){
            g2d.setColor(obj.getColor());
            g2d.fill(obj.getShape());
        }
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }
}
