package Game.Objects;

import JEngine.Events.EventHandler;
import JEngine.Objects.GameObject;
import JEngine.global.JEngine;
import Math.Transform;
import Math.Vector;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Rocket extends GameObject {
    EventHandler eventHandler;

    private static final double SI_METER = 50;
    private Rectangle2D rect;
    private Color fillColor;
    private Transform transform;
    private Vector acceleration;
    private Vector velocity;
    private Vector direction;
    Vector mouse;

    private boolean isGrounded = false;
    private double gravity = 9.82f;
    private double mass = 100; // kg
    // F = m * a
    // a = F / m
    private double friction = 50;
    private double maxSpeed = 100.f;
    private double groundLevel = 500;

    private double mouseX = 0;
    private double mouseY = 0;

    public Rocket(Vector position){
        fillColor = new Color(220, 223, 242);
        transform = new Transform(position);
        acceleration = new Vector(20.f, 10, 0);
        velocity = new Vector();
        direction = new Vector();
        mouse = new Vector(0);
    }

    public void init() {
        rect = new Rectangle2D.Double();
    }

    public void update(double DeltaTime) {
        groundLevel = JEngine.window.getSize().height-200;

        isGrounded = transform.position().getY() >= groundLevel - 0.001f;
        // v = a * t
        // p = v * t
        // p = a * t * t
        if(eventHandler.IsKeyDown(KeyEvent.VK_D)){
            velocity.setX(velocity.getX() + acceleration.getX() * DeltaTime);
        }
        if(eventHandler.IsKeyDown(KeyEvent.VK_A)){
            velocity.setX(velocity.getX() - acceleration.getX() * DeltaTime);
        }

        if(eventHandler.IsKeyDown(KeyEvent.VK_W)){
            double energy = 1;
            if(eventHandler.IsKeyDown(KeyEvent.VK_SPACE)) energy=30;
            acceleration.setY(acceleration.getY() + energy * DeltaTime);
            velocity.setY(velocity.getY() + acceleration.getY() * DeltaTime);
        }
        if(!isGrounded && (eventHandler.IsKeyDown(KeyEvent.VK_D) || eventHandler.IsKeyDown(KeyEvent.VK_A) || eventHandler.IsKeyDown(KeyEvent.VK_W))) {
            if (acceleration.getY() > 0) {
                acceleration.setY(acceleration.getY() - 10.00f * DeltaTime);
            } else {
                acceleration.setY(0);
            }
            //System.out.println(acceleration.getY());
        }

        //System.out.println(mouse.sub(transform.position()).length2D());
        /*direction = mouse.sub(transform.position());
        if(direction.length2D() > 0){

            velocity.setX(velocity.getX() + acceleration.getX() * direction.getX() / direction.length2D());
            velocity.setY(velocity.getY() - acceleration.getY() * direction.getY() / direction.length2D());
        }*/

        if(!isGrounded){
            velocity.setY(velocity.getY() - gravity * DeltaTime);
            //if(speedY > 0) speedY = 0;
        }else{
            if(Math.abs(velocity.getY()) <= 0.f && isGrounded) velocity.setY(0);
            else if(velocity.getY() < 0) {
                velocity.setY(0);
            }
        }

        if(transform.position().getY() > groundLevel+1) transform.position().setY(groundLevel);
        transform.position().setX(transform.position().getX() + velocity.getX() * DeltaTime * SI_METER);
        transform.position().setY(transform.position().getY() - velocity.getY() * DeltaTime * SI_METER);
        rect.setRect(transform.position().getX(), transform.position().getY(), transform.scale().getX(), transform.scale().getY());
    }

    public void setGroundLevel(double level){
        this.groundLevel = level;
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

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
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

    public double getMouseX() {
        return mouseX;
    }

    public void setMouse(double mouseX, double mouseY) {
        this.mouse.set(mouseX, mouseY, 0);
    }

    public double getMouseY() {
        return mouseY;
    }
}
