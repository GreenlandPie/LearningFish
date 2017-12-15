import java.util.*;

public class GeneticAlgorithm{

    public static final int ONE_POINT_CROSSOVER = 1;
    public static final int TWO_POINT_CROSSOVER = 2;
    public static final int UNIFORM_CROSSOVER = 3;
    public static final int THREE_PARENT_CROSSOVER = 4;

    public static final int BOUNDARY_MUTATION = 10;
    public static final int UNIFORM_MUTATION = 11;
    public static final int FLIP_MUTATION = 12;

    public static Chrome crossover(Chrome parent1, Chrome parent2, Chrome parent3, int mode, double crossoverRate){

        Chrome child = new Chrome();

        try{
            switch(mode){
                case ONE_POINT_CROSSOVER:
                    int index = new Random().nextInt(Config.NUM_GENES);

                    for(int i=0;i<Config.NUM_GENES;i++){
                        if(i<index) child.setGene(i, parent1.getGene(i));
                        else child.setGene(i, parent2.getGene(i));
                    }
                    break;
                case TWO_POINT_CROSSOVER:
                    int index1 = new Random().nextInt(Config.NUM_GENES);
                    int index2 = new Random().nextInt(Config.NUM_GENES - index1) + index1;

                    for(int i=0;i<Config.NUM_GENES;i++){
                        if(i<index1) child.setGene(i, parent1.getGene(i));
                        if(i<index2) child.setGene(i, parent2.getGene(i));
                        if(i>=index2) child.setGene(i, parent1.getGene(i));
                    }
                    break;
                case UNIFORM_CROSSOVER:
                    if(crossoverRate > 1.0) crossoverRate = 1.0;
                    if(crossoverRate < 0.0) crossoverRate = 0.0;

                    for(int i=0;i<Config.NUM_GENES;i++){
                        double cRate = new Random().nextDouble();

                        if(cRate < crossoverRate) child.setGene(i, parent1.getGene(i));
                        else child.setGene(i, parent2.getGene(i));
                    }
                    break;
                case THREE_PARENT_CROSSOVER:
                    try{
                        for(int i=0;i<Config.NUM_GENES;i++){
                            if(parent1.getGene(i) == parent2.getGene(i)) child.setGene(i, parent1.getGene(i));
                            else child.setGene(i, parent3.getGene(i));
                        }
                    }
                    catch(NullPointerException e){
                        System.err.println("Caught NullPointerException: "+e.getMessage());
                    }
                default:
                    System.out.println("No crossover was made. Child returned has non-initialized genes");
                    break;
            }
        }
        catch(NullPointerException e){
            System.err.println("Caught NullPointerException: "+e.getMessage());
        }

        return child;
    }

    /**
    * Mutate genes of the selected chromosome, based on the selected mode and themutation rate
    *
    * @param    child   chromosome to mutate
    * @param    mutationRate the percentage of the genes to be mutated
    * @param    mode    can be either UNIFORM_MUTATION: mutates [@mutationRate]% of the genes or BOUNDARY_MUTATION: every gene is the mutated randomly to upper or lower bound
    */
    public static void mutate(Chrome child, double mutationRate, int mode){

        try{
            switch(mode){
                case UNIFORM_MUTATION:
                    for(int i=0;i<mutationRate*Config.NUM_GENES;i++){

                        int index = new Random().nextInt(Config.NUM_GENES);
                        double value = (new Random().nextDouble()*Config.OFFSET*2)-Config.OFFSET;

                        child.setGene(index, value);
                    }
                    break;
                case BOUNDARY_MUTATION:
                    for(int i=0;i<Config.NUM_GENES;i++){

                        double bound = new Random().nextInt(2) == 0 ? -Config.OFFSET : Config.OFFSET;

                        child.setGene(i, bound);
                    }
                    break;
                case FLIP_MUTATION:
                    for(int i=0;i<Config.NUM_GENES;i++){
                        child.setGene(i, -child.getGene(i));
                    }
                    break;
            }
        }
        catch(NullPointerException e){
            System.err.println("Caught NullPointerException: " + e.getMessage());
        }
    }
}
