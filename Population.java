public class Population{

    private Chrome[] population;

    public Population(){
        this(Config.NUM_CHROMES);
    }

    public Population(int size){
        population = new Chrome[size];
    }

    public Chrome[] getPopulation(){
        return population;
    }
}
