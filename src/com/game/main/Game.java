package com.game.main;

import com.game.gfx.Windows;
import com.game.object.util.Handler;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game  extends Canvas implements Runnable{
    private static final int MILLIS_PER_SEC=1000;
    private static final int NANOS_PER_SEC=1000000000;
    private static final double NUM_TICKS=60.0;
    private static final String NAME = "Mario";
    private static final int WINDOW_WIDTH = 960;
    private static final int WINDOW_HEIGHT = 720;

    private boolean running;

    private Thread thread;
    private Handler handler;

    public Game() {
        initalize();
    }
    public static void main(String[] args) {
        new Game();
    }

    private void initalize(){
        handler = new Handler();
        new Windows(WINDOW_WIDTH, WINDOW_HEIGHT,NAME, this);

        start();
    }

    private synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @java.lang.Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = NUM_TICKS;
        double ns = NANOS_PER_SEC/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;

            while(delta>=1){
                tick();
                updates++;
                delta--;
            }

            if(running){
                render();
                frames++;
            }

            if (System.currentTimeMillis() - timer > MILLIS_PER_SEC){
                timer += MILLIS_PER_SEC;
                System.out.println("FPS: " + frames + " TPS: "+updates);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
    }

    private void render(){
        BufferStrategy buf = this.getBufferStrategy();
        if(buf == null){
            this.createBufferStrategy(3);
            return;
        }
        System.gc();

        //draw graphics
        Graphics g = buf.getDrawGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);

        handler.render(g);
        //clean for next frame
        g.dispose();
        buf.show();
    }

    public static int getWindowHeight(){
        return WINDOW_HEIGHT;
    }

    public static  int getWindowWidth(){
        return WINDOW_WIDTH;
    }
}
