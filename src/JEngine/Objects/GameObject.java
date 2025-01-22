package JEngine.Objects;

import JEngine.Events.EventHandler;
import JEngine.Math.Transform;

import java.awt.*;

public abstract class GameObject implements IGameObject{
    public abstract void init();
    public abstract void update(double DeltaTime);
    public abstract void setEventHandler(EventHandler eventHandler);
    public abstract Transform getTransform();
    public abstract Shape getShape();
    public abstract Color getColor();
}
