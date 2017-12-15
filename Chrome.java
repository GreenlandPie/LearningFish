import java.util.*;
import java.math.*;

public class Chrome{

    private double[] genes;
    private double mutationRate, crossoverRate;
    private int x, y, r, g, b;
    private float finalDistance, closestDistance, angle;
    private boolean alive, reached;

    public Chrome(int x, int y){
        this(x, y, 0.4, 0.005, new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
    }

    public Chrome(int x, int y, double crossoverRate, double mutationRate, int r, int g, int b){
        this.x=x;
        this.y=y;
        this.crossoverRate=crossoverRate;
        this.mutationRate=mutationRate;
        this.r=r;
        this.g=g;
        this.b=b;

        genes = new double[Config.NUM_CHROMOSOMES];
    }

    public void generateRandomGenes(){
        for(int i=0;i<genes.length;i++){
            this.genes[i]=(new Random().nextDouble()*Config.OFFSET*2)-Config.OFFSET;
        }
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public float getAngle(){
        return this.angle;
    }

    public boolean isAlive(){
        return this.alive();
    }

    public void setState(boolean b){
        alive=b;
    }

    public void addAngle(float a){
        this.angle+=a;
    }

    public void move(int fCount){

        addAngle(genes[fCount]);

        this.x += Config.MOVE_AMOUNT*Math.cos(angle);
        this.y += COnfig.MOVE_AMOUNT*Math.sin(angle);
    }

    public void update(){

        if(this.x < 0 || this.x > Config.SCREEN_WIDTH || this.y < 0 || this.y > Config.SCREEN_HEIGHT){
            setState(false);
        }
    }

    public void display(Graphics g){

        g.setColor(new Color(r, g, b));
        g.fillRect(x, y, x+5, y+5);
    }

}

