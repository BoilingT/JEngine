package Game;

import Game.Objects.*;
import JEngine.Objects.GameObject;
import JEngine.Objects.IGameObject;
import Math.Vector;
import JEngine.global.JEngine;

import java.awt.*;
import java.util.ArrayList;

public class Game extends JEngine {

    private final ArrayList<IGameObject> gameObjects;

    public Game(){
        super();
        System.out.println("Game Initialized");
        SetFixedDeltaTime(1000);

        gameObjects = new ArrayList<>();
    }

    public void Init(){
        Ground ground = new Ground(new Vector(0,600,0), Color.GRAY);
        ground.getTransform().scale().set(700, 100, 0);

        Rocket rocket = new Rocket(new Vector(0, 100, 0));
        rocket.getTransform().scale().set(100, 100, 0);

        BouncyBall ball = new BouncyBall(350-50, 0, 50);
        ball.setAcceleration(new Vector(20.f, 10, 0));

        gameObjects.add(ground);
        gameObjects.add(rocket);
        //gameObjects.add(ball);

        for(IGameObject obj : gameObjects){
            obj.init();
            obj.setEventHandler(eventHandler);
            getCanvas().draw_queue.add(obj);
        }
    }

    double time = 0;
    public void Update(double DeltaTime){
        time += DeltaTime;

        for(IGameObject obj : getCanvas().draw_queue){
            obj.update(DeltaTime);
        }
        if(time > Math.TAU) time = 0;
    }

    public void FixedUpdate(double FixedDeltaTime){
        //System.out.println("X: " + rocket.getTransform().position().getX() + ", Y: " + rocket.getTransform().position().getY() + ", speedY: " + rocket.getVelocity().getY());
        System.out.println("FPS: " + GetFramesPerSecond());
    }
}