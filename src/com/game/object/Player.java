package com.game.object;

import com.game.object.util.Handler;
import com.game.object.util.ObjectId;
import org.w3c.dom.css.Rect;

import java.awt.*;

public class Player extends GameObject{
    private static final float WIDTH = 16;
    private static final float HEIGHT = 32;
    private Handler handler;

    private boolean jumped=false;

    public Player(float x, float y, int scale, Handler handler){
        super(x,y, ObjectId.Player,WIDTH, HEIGHT, scale);
        this.handler = handler;
    }

    @Override
    public void tick() {
        setX(getVelX()+getX());
        setY(getVelY()+getY());
        //applyGravity();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int) getX(), (int) getY(), (int) WIDTH, (int) HEIGHT);
        showBounds(g);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()+getWidth()/2-getWidth()/4),
                (int) (getY()+getHeight()/2),
                (int) getWidth()/2,
                (int) getHeight()/2);
    }

    public Rectangle getBoundsTop(){

        return new Rectangle((int) (getX()+getWidth()/2-getWidth()/4),
                (int) (getY()),
                (int) getWidth()/2,
                (int) getHeight()/2);
    }

    public Rectangle getBoundsRight(){

        return new Rectangle((int) (getX()+getWidth()-5),
                (int) (getY()+5),
                5,
                (int) getHeight()-10);


    }

    public Rectangle getBoundsLeft(){
        return new Rectangle((int) (getX()),
                (int) (getY()+5),
                5,
                (int) getHeight()-10);

    }

    private void showBounds(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.RED);
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
    }

    public boolean hasJumped(){
        return jumped;
    }

    public void setJumped(boolean hasJumped){
        jumped = hasJumped;
    }
}
