import java.util.*;
import java.math.*;
import java.awt.*;

public class Unit{

    public Chrome chrome;
    public int x, y, r, g, b;
    public double finalDistance, closestDistance, angle;
    public boolean alive, reached;

    public int timeStart, timeDead, timeReached;

    public Unit(Chrome chrome){
        this(Config.START_X, Config.START_Y, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
        this.chrome = chrome;
    }

    public Unit(int x, int y){
        this(x, y, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));

        this.chrome = new Chrome();
    }

    public Unit(int x, int y, int r, int g, int b){
        this.x = x;
        this.y = y;

        this.r=r;
        this.g=g;
        this.b=b;

        this.alive=true;
        this.reached=false;

        this.timeStart = Board.frameCount%Config.NUM_FRAMES;
        this.closestDistance = Config.SCREEN_WIDTH*Config.SCREEN_HEIGHT;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Chrome getChrome(){
        return this.chrome;
    }

    public double getAngle(){
        return this.angle;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public void fitness(){

        double fit = 1*(Config.NUM_GENES-finalDistance)+0.5*(Config.NUM_GENES-closestDistance)+0.3*(Config.NUM_FRAMES-timeDead)+1*(Config.NUM_FRAMES-timeReached);

        this.chrome.setFitness(fit);
    }

    public void setState(boolean b){
        this.alive=b;
    }

    public void setChrome(Chrome c){
        this.chrome=c;
    }

    public void reset(){
        this.x = Config.START_X;
        this.y = Config.START_Y;

        this.angle = 0;

        this.alive=true;
        this.reached=false;

        this.timeStart = Board.frameCount;
        this.finalDistance = 0;
        this.closestDistance = Config.SCREEN_WIDTH*Config.SCREEN_HEIGHT;
    }

    public void addAngle(double a){
        this.angle+=a;
    }

    public double dist(int x, int y){
        return Math.sqrt(Math.pow(this.x-x, 2) + Math.pow(this.y-y, 2));
    }

    public void setDistance(double d){
        finalDistance = d;
    }

    public void move(int fCount){

        addAngle(chrome.genes[fCount]);

        this.x += Config.MOVE_AMOUNT*Math.cos(angle);
        this.y += Config.MOVE_AMOUNT*Math.sin(angle);
    }

    public void update(Wall wall){
        if(this.x < 0 || this.x > Config.SCREEN_WIDTH || this.y < 0 || this.y > Config.SCREEN_HEIGHT){

            Board.deads++;
            this.finalDistance = dist(Config.END_X, Config.END_Y);
            this.timeDead = Board.frameCount%Config.NUM_FRAMES;
            setState(false);
        }

        if(wall.intersects(this.x, this.y)){
            Board.deads++;
            this.finalDistance = dist(Config.END_X, Config.END_Y);
            this.timeDead = Board.frameCount%Config.NUM_FRAMES;
            setState(false);
        }
    }

    public void display(Graphics gg){

        gg.setColor(new Color(r, g, b));
        gg.fillRect(x, y, 5, 5);
    }
}
