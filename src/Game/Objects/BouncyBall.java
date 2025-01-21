package Game.Objects;

import JEngine.Events.EventHandler;
import JEngine.Objects.IGameObject;
import JEngine.Objects.Transform;
import JEngine.Objects.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;

public class BouncyBall implements IGameObject {

    private EventHandler eventHandler;

    private Arc2D arc;
    private Color fillColor;
    private Transform transform;
    private Vector acceleration;
    private Vector velocity;
    private Vector direction;
    private double radius;

    private boolean isGrounded = false;
    private double gravity = 10.82f;
    private double friction = 50;
    private double maxSpeed = 100.f;

    public BouncyBall(Transform transform, double radius){
        this.transform = transform;
        acceleration = new Vector();
        velocity = new Vector();
        direction = new Vector();
        this.radius = radius;
    }

    public BouncyBall(double x, double y, double radius){
        this.transform = new Transform(x, y);
        acceleration = new Vector();
        velocity = new Vector();
        this.radius = radius;
    }

    @Override
    public void init() {

        // Arc
        Vector position = transform.position();
        fillColor = Color.RED;
        arc = new Arc2D.Double(Arc2D.PIE);
        arc.setAngleStart(0);
        arc.setAngleExtent(360);
        arc.setFrame(position.getX(), position.getY(), 100, 100);
    }

    @Override
    public void update(double DeltaTime) {
        isGrounded = transform.position().getY() >= 499.99f;
        if(!isGrounded) friction = 25;
        else friction = 50;
        if(true){
            if(eventHandler.IsKeyDown(KeyEvent.VK_D)){
                velocity.setX(velocity.getX() + acceleration.getX());
            }
            if(eventHandler.IsKeyDown(KeyEvent.VK_A)){
                velocity.setX(velocity.getX() - acceleration.getX());
            }
            if(eventHandler.IsKeyDown(KeyEvent.VK_SPACE) && isGrounded){
                velocity.setY(acceleration.getY());
            }
        }

        if(velocity.getY() > 0){
            //speedY -= friction;
            //if(speedY < 0) speedY = 0;
        }
        if(!isGrounded){
            velocity.setY(velocity.getY() - gravity);
            //if(speedY > 0) speedY = 0;
        }else{
            if(Math.abs(velocity.getY()) <= 10.f && isGrounded) velocity.setY(0);
            else if(velocity.getY() < 0) velocity.setY(velocity.getY() * -0.8f);
        }
        if(velocity.getX() > 0){
            velocity.setX(velocity.getX() - friction);
            if(velocity.getX() < 0) velocity.setX(0);
        }else if(velocity.getX() < 0){
            velocity.setX(velocity.getX() + friction);
            if(velocity.getX() > 0) velocity.setX(0);
        }

        transform.position().setX(transform.position().getX() + velocity.getX() * DeltaTime);
        transform.position().setY(transform.position().getY() - velocity.getY() * DeltaTime);
        arc.setFrame(transform.position().getX(), transform.position().getY(), 100, 100);
    }

    @Override
    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    public Vector getVelocity(){
        return velocity;
    }

    public Vector getAcceleration(){
        return velocity;
    }

    public void setVelocity(Vector velocity){
        this.velocity = velocity;
    }

    public void setAcceleration(Vector acceleration){
        this.acceleration = acceleration;
    }

    @Override
    public Shape getShape() {
        return arc;
    }

    @Override
    public Color getColor() {
        return fillColor;
    }
}
