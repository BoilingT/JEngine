package Game;

import Game.Objects.*;
import JEngine.Objects.IGameObject;
import JEngine.Objects.Vector;
import JEngine.global.JEngine;

import java.awt.*;

public class Game extends JEngine {

    private Ground ground;
    private BouncyBall ball;

    public Game(){
        super();
        System.out.println("Game Initialized");
        SetFixedDeltaTime(500);
    }

    @Override
    public void Init(){
        ground = new Ground(new Vector(0,600,0), Color.GRAY);
        ground.getTransform().scale().set(700, 100, 0);
        ground.init();
        canvas.draw_queue.add(ground);

        ball = new BouncyBall(350-50, 0, 50);
        ball.setAcceleration(new Vector(100, 1000, 0));
        ball.setEventHandler(eventHandler);
        ball.init();
        canvas.draw_queue.add(ball);
    }

    double time = 0;
    @Override
    public void Update(double DeltaTime){
        time += DeltaTime;
        for(IGameObject obj : canvas.draw_queue){
            obj.update(DeltaTime);
        }
        if(time > Math.TAU) time = 0;
    }

    @Override
    public void FixedUpdate(double FixedDeltaTime){
        System.out.println("X: " + ball.getTransform().position().getX() + ", Y: " + ball.getTransform().position().getY() + ", speedY: " + ball.getVelocity().getY());
        //System.out.println("FPS: " + GetFramesPerSecond());
    }
}