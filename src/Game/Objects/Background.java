package Game.Objects;

import JEngine.Events.EventHandler;
import JEngine.Objects.GameObject;
import JEngine.global.JEngine;
import Math.Transform;

import java.awt.*;

public class Background extends GameObject {
    private Rectangle rect;
    private Transform transform;
    private Color fillColor;

    @Override
    public void init() {
        fillColor = new Color(45, 47, 61);
        transform = new Transform();
        rect = new Rectangle();
    }

    @Override
    public void update(double DeltaTime) {
        transform.scale().set(JEngine.window.getSize().width, JEngine.window.getSize().height, 0);
        rect.setRect(transform.position().getX(), transform.position().getY(), transform.scale().getX(), transform.scale().getY());
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
