package Game.Objects;

import JEngine.Events.EventHandler;
import JEngine.Objects.GameObject;
import JEngine.Objects.Transform;
import JEngine.Objects.Vector;

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

    @Override
    public void init() {
        rect = new Rectangle2D.Double();
        rect.setFrame(transform.position().getX(), transform.position().getY(), transform.scale().getX(), transform.scale().getY());
    }

    @Override
    public void update(double DeltaTime) {
        rect.setFrame(transform.position().getX(), transform.position().getY(), transform.scale().getX(), transform.scale().getY());
    }

    @Override
    public void setEventHandler(EventHandler eventHandler) {

    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public Shape getShape() {
        return rect;
    }

    @Override
    public Color getColor() {
        return fillColor;
    }
}
