package com.game.object;

import com.game.object.util.Handler;
import com.game.object.util.ObjectId;

import java.awt.*;

public class Player extends GameObject{
    private static final float WIDTH = 16;
    private static final float HEIGHT = 32;

    private Handler handler;

    public Player(float x, float y, int scale, Handler handler){
        super(x,y, ObjectId.Player,WIDTH, HEIGHT, scale);
        this.handler = handler;
    }

    @Override
    public void tick() {
        setX(getVelX()+getX());
        setY(getVelY()+getY());
        applyGravity();
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    public Rectangle getBoundsTop(){
        return null;
    }

    public Rectangle getBoundsRight(){
        return null;
    }

    public Rectangle getBoundsLeft(){

    }

    private void showBounds(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.RED);
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
    }
}
