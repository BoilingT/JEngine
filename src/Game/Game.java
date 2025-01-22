package Game;

import Game.Objects.*;
import JEngine.Objects.IGameObject;
import Math.Vector;
import JEngine.global.JEngine;

import java.awt.*;

public class Game extends JEngine {

    private Ground ground;
    private BouncyBall ball;
    private Rocket rocket;

    public Game(){
        super();
        System.out.println("Game Initialized");
        SetFixedDeltaTime(500);
    }

    public void Init(){
        ground = new Ground(new Vector(0,600,0), Color.GRAY);
        ground.getTransform().scale().set(700, 100, 0);
        ground.init();
        getCanvas().draw_queue.add(ground);

        rocket = new Rocket(new Vector(0, 100, 0));
        rocket.getTransform().scale().set(100, 100, 0);
        rocket.setEventHandler(eventHandler);
        rocket.init();
        getCanvas().draw_queue.add(rocket);

        /*ball = new BouncyBall(350-50, 0, 50);
        ball.setAcceleration(new Vector(20.f, 10, 0));
        ball.setEventHandler(eventHandler);
        ball.init();
        getCanvas().draw_queue.add(ball);*/
    }

    double time = 0;
    public void Update(double DeltaTime){
        time += DeltaTime;
        rocket.setGroundLevel(getCanvas().getSize().height-200);
        rocket.setMouse(getMousex(), getMousey());
        //ball.setGroundLevel(getCanvas().getSize().height-200);
        ground.getTransform().position().set(0, getCanvas().getSize().height-100, 0);
        ground.getTransform().scale().set(getCanvas().getSize().width, 100, 0);
        for(IGameObject obj : getCanvas().draw_queue){
            obj.update(DeltaTime);
        }
        if(time > Math.TAU) time = 0;
    }

    public void FixedUpdate(double FixedDeltaTime){
        System.out.println("X: " + rocket.getTransform().position().getX() + ", Y: " + rocket.getTransform().position().getY() + ", speedY: " + rocket.getVelocity().getY());
        System.out.println("FPS: " + GetFramesPerSecond());
    }
}