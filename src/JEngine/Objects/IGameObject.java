package JEngine.Objects;

import JEngine.Events.EventHandler;
import Math.Transform;

import java.awt.*;

public interface IGameObject {
    void init();
    void update(double DeltaTime);
    void setEventHandler(EventHandler eventHandler);

    Transform getTransform();
    Shape getShape();
    Color getColor();
}
