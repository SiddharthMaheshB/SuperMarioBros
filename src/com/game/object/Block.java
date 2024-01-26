package com.game.object;

import com.game.gfx.Texture;
import com.game.main.Game;
import com.game.object.util.ObjectId;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject{
    private Texture tex = Game.getTexture();
    private int index;
    private BufferedImage[] sprite;
    public Block(int x, int y, int width, int height,int index, int scale){
        super(x,y, ObjectId.Block, width, height, scale);
        this.index = index;
        sprite = tex.getTile_1();
    }
    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getWidth(), (int) getHeight(),null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }
}

