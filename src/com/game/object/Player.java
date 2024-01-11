package com.game.object;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.Handler;
import com.game.object.util.ObjectId;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

public class Player extends GameObject{
    private static final float WIDTH = 16;
    private static final float HEIGHT = 32;
    private Handler handler;
    private Texture tex;
    private BufferedImage[] spriteL, spriteS;
    private boolean jumped=false;
    int health;
    private double spriteIndex=0;

    public Player(float x, float y, int scale, Handler handler){
        super(x,y, ObjectId.Player,WIDTH, HEIGHT, scale);
        this.handler = handler;
        tex = Game.getTexture();
        spriteL=tex.getMario_l();
        spriteS = tex.getMario_s();
        health =2;
    }

    @Override
    public void tick() {
        setX(getVelX()+getX());
        setY(getVelY()+getY());
        applyGravity();
        collision();
        if(getVelX()==0){
            setSpriteIndex(0);
        }
        else setSpriteIndex(((getSpriteIndex())+0.1>3.9)?(1):((getSpriteIndex())+0.1));
    }

    @Override
    public void render(Graphics g) {
        if(health==1){
            g.drawImage(spriteS[(int) floor(spriteIndex)], (((int) getWidth()>0)?((int) getX()):((int)(getX()+getAbsWidth()))), (int) getY(), abs((int) getWidth()), abs((int) getHeight()/2),null);
        }
        else if(health ==2){
            g.drawImage(spriteL[(int) floor(spriteIndex)], (((int) getWidth()>0)?((int) getX()):((int)(getX()+getAbsWidth()))), (int) getY(), (int) getWidth(), (int) getHeight(), null);
        }
        //showBounds(g);
    }

    private void collision(){
        for(int i=0;i<handler.getGameObjs().size();i++){
            GameObject temp = handler.getGameObjs().get(i);

            if((temp.getId()==ObjectId.Block)||(temp.getId()==ObjectId.Pipe)){

                if(getBounds().intersects(temp.getBounds())){
                    setY(temp.getY()-getAbsHeight());
                    setVelY(0);
                    jumped=false;
                }
                if(getBoundsTop().intersects(temp.getBounds())){
                    setY(temp.getY()+temp.getAbsHeight());
                    setVelY(0);
                }
                if(getBoundsRight().intersects(temp.getBounds())){
                    setX(temp.getX()-getAbsWidth());
                }
                if(getBoundsLeft().intersects(temp.getBounds())){
                    setX(temp.getX()+temp.getAbsWidth());
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()+getAbsWidth()/2-getAbsWidth()/4),
                (int) (getY()+getAbsHeight()/2),
                (int) getAbsWidth()/2,
                (int) getAbsHeight()/2);
    }

    public Rectangle getBoundsTop(){

        return new Rectangle((int) (getX()+getAbsWidth()/2-getAbsWidth()/4),
                (int) (getY()),
                (int) getAbsWidth()/2,
                (int) getAbsHeight()/2);
    }

    public Rectangle getBoundsRight(){

        return new Rectangle((int) (getX()+getAbsWidth()-5),
                (int) (getY()+5),
                5,
                (int) getAbsHeight()-10);


    }

    public Rectangle getBoundsLeft(){
        return new Rectangle((int) (getX()),
                (int) (getY()+5),
                5,
                (int) getAbsHeight()-10);

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

    public void setSpriteIndex(double spriteIndex){
        this.spriteIndex = spriteIndex;
    }

    public double getSpriteIndex(){
        return spriteIndex;
    }
}