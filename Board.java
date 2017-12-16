import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel{

    public Population currPop;
    public Unit[] rappr;
    public Wall wall;

    public static int frameCount;
    public static int deads;

    public static JFrame frame;

    public Board(){

        currPop = new Population();
        rappr = new Unit[currPop.getPopulation().length];

        for(int i=0;i<rappr.length;i++){
            rappr[i]=new Unit(currPop.getPopulation()[i]);
        }

        //System.out.println("fin qui arrivo");
        wall = new Wall(200, 500, 600, 200);

        frameCount=0;
        deads=0;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        if(frameCount == 0){
            currPop = evolve();
        }

        for(int i=0;i<rappr.length;i++){
            rappr[i].display(g);
            if(rappr[i].isAlive() && !rappr[i].reached){
                double tempDist = rappr[i].dist(Config.END_X, Config.END_Y);
                if(tempDist < rappr[i].closestDistance){
                    rappr[i].closestDistance = tempDist;
                }

                rappr[i].move(frameCount);
                rappr[i].setDistance(tempDist);
                rappr[i].update(wall);
                rappr[i].fitness();

                if(tempDist < 20){
                    rappr[i].reached=true;
                    rappr[i].timeReached = frameCount;
                }
            }
        }

        wall.display(g);

        frameCount = (frameCount+1)%Config.NUM_FRAMES;

        System.out.println(frameCount);
        repaint();
        //validate();
    }

    public Population evolve(){

        Population newPop = new Population();
        Chrome fittest = currPop.getFittest();

        newPop.population[0] = fittest;
        for(int i=0;i<newPop.population.length;i++){
            newPop.population[i] = GeneticAlgorithm.crossover(fittest, currPop.population[i], null, GeneticAlgorithm.UNIFORM_CROSSOVER, currPop.population[i].crossoverRate);
        }

        for(int i=0;i<newPop.population.length;i++){
            GeneticAlgorithm.mutate(newPop.population[i], newPop.population[i].mutationRate, GeneticAlgorithm.UNIFORM_MUTATION);
        }

        for(int i=0;i<rappr.length;i++){
            rappr[i] = new Unit(newPop.population[i]);
        }

        return newPop;
    }

    public static void main(String args[]){

        frame = new JFrame("Learning fish");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        frame.setLocation(0, 0);

        frame.add(new Board());

        frame.setVisible(true);
    }


}
