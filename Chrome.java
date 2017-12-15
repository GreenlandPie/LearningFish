import java.util.*;
import java.math.*;

public class Chrome{

    public double[] genes;
    public double mutationRate, crossoverRate;
    public double fitness;

    public Chrome(){
        this(0.4, 0.005);
    }

    public Chrome(double crossoverRate, double mutationRate){
        this.crossoverRate=crossoverRate;
        this.mutationRate=mutationRate;

        genes = new double[Config.NUM_GENES];
        generateRandomGenes();
    }

    public void generateRandomGenes(){
        for(int i=0;i<genes.length;i++){
            setGene(i, (new Random().nextDouble()*Config.OFFSET*2)-Config.OFFSET);
        }
    }

    public double getGene(int index){
        return genes[index];
    }

    public double getFitness(){
        return fitness;
    }

    public void setGene(int index, double value){
        this.genes[index] = value;
    }

    public void setFitness(double fitness){
        this.fitness = fitness;
    }

}
