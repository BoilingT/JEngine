package Game.Objects;

import JEngine.Events.EventHandler;
import JEngine.Objects.GameObject;
import JEngine.global.JEngine;
import Math.Transform;
import Math.Vector;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Ground extends GameObject {

    private Rectangle2D rect;

    private Transform transform;
    private Color fillColor;

    public Ground(Vector position, Color color){
        this.transform = new Transform(position);
        fillColor = color;
    }

    public void init() {
        rect = new Rectangle2D.Double();
        rect.setFrame(transform.position().getX(), transform.position().getY(), transform.scale().getX(), transform.scale().getY());
    }

    public void update(double DeltaTime) {
        transform.position().setY(JEngine.window.getSize().height-100);
        transform.scale().set(JEngine.window.getSize().width, 100, 0);
        rect.setFrame(transform.position().getX(), transform.position().getY(), transform.scale().getX(), transform.scale().getY());
    }

    public void setEventHandler(EventHandler eventHandler) {

    }

    public Transform getTransform() {
        return transform;
    }

    public Shape getShape() {
        return rect;
    }

    public Color getColor() {
        return fillColor;
    }
}
