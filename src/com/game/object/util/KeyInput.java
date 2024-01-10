package com.game.object.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private boolean[] keyDown = new boolean[4];
    private Handler handler;
    public KeyInput(Handler handler){
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }

        //jump
        if(key == KeyEvent.VK_SPACE || key==KeyEvent.VK_W){
            if(!handler.getPlayer().hasJumped()){
                handler.getPlayer().setVelY(-5);
                keyDown[0] = true;
                handler.getPlayer().setJumped(true);
            }

        }

        //left
        if(key == KeyEvent.VK_A || key==KeyEvent.VK_LEFT){
            handler.getPlayer().setVelX(-7);
            keyDown[1]=true;
        }

        //right
        if(key==KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            handler.getPlayer().setVelX(7);
            keyDown[2]=true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SPACE || key==KeyEvent.VK_W){
            keyDown[0]=false;
        }

        if(key == KeyEvent.VK_A || key==KeyEvent.VK_LEFT){
            keyDown[1]=false;
        }

        if(key==KeyEvent.VK_D || key == KeyEvent.VK_RIGHT){
            keyDown[1]=false;
        }

        if(!keyDown[1] && !keyDown[2]){
            handler.getPlayer().setVelX(0);
        }
    }

    private int toInt(boolean bool){
        return Boolean.compare(bool,false);
    }
}
 