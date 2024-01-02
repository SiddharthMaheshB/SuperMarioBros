package com.game.object.util;

import com.game.object.GameObject;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Handler {
    private List<GameObject> gameObjs;

    public Handler(){
        gameObjs = new LinkedList<GameObject>();
    }

    public void tick() {
        for (GameObject obj : gameObjs){
            obj.tick();
        }
    }

    public void render(Graphics g){
        for(GameObject obj : gameObjs){
            obj.render(g);
        }
    }

    public void addObj(GameObject obj){
        gameObjs.add(obj);
    }

    public void removeObj(GameObject obj){
        gameObjs.remove(obj);
    }

    public List<GameObject> getGameObjs(){
        return gameObjs;
    }
}