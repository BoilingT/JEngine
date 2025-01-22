package Game.Objects;

import JEngine.Events.EventHandler;
import JEngine.Objects.GameObject;
import Math.Transform;
import Math.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Arc2D;

public class BouncyBall extends GameObject {

    private EventHandler eventHandler;

    private Arc2D arc;
    private Color fillColor;
    private Transform transform;
    private Vector acceleration;
    private Vector velocity;
    private Vector direction;
    private double radius;

    private boolean isGrounded = false;
    private double gravity = 9.82f;
    private double friction = 0.0f;
    private double maxSpeed = 100.f;
    private double groundLevel = 0;

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
        isGrounded = transform.position().getY() >= groundLevel-0.001f;
        if(!isGrounded) friction = 0;
        else friction = 4;
        if(true){
            if(eventHandler.IsKeyDown(KeyEvent.VK_D)){
                velocity.setX(velocity.getX() + acceleration.getX() * DeltaTime);
            }
            if(eventHandler.IsKeyDown(KeyEvent.VK_A)){
                velocity.setX(velocity.getX() - acceleration.getX() * DeltaTime);
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
            velocity.setY(velocity.getY() - gravity * DeltaTime);
            //if(speedY > 0) speedY = 0;
        }else{
            if(Math.abs(velocity.getY()) <= 0.f && isGrounded) velocity.setY(0);
            else if(velocity.getY() < 0) velocity.setY(velocity.getY() * -0.8f);
        }
        if(velocity.getX() > 0){
            velocity.setX(velocity.getX() - friction * DeltaTime);
            if(velocity.getX() < 0) velocity.setX(0);
        }else if(velocity.getX() < 0){
            velocity.setX(velocity.getX() + friction * DeltaTime);
            if(velocity.getX() > 0) velocity.setX(0);
        }

        if(transform.position().getY() > groundLevel+1) transform.position().setY(groundLevel);
        transform.position().setX(transform.position().getX() + velocity.getX() * DeltaTime * 90);
        transform.position().setY(transform.position().getY() - velocity.getY() * DeltaTime * 90);
        arc.setFrame(transform.position().getX(), transform.position().getY(), 100, 100);
    }

    public void setGroundLevel(double level){
        this.groundLevel = level;
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
