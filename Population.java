public class Population{

    public Chrome[] population;

    public Population(){
        this(Config.NUM_CHROMES);
    }

    public Population(int size){
        population = new Chrome[size];

        for(int i=0;i<size;i++){
            population[i] = new Chrome();
        }
    }

    public Chrome[] getPopulation(){
        return population;
    }

    public Chrome getFittest(){
        Chrome fittest = population[0];

        for(int i=1;i<population.length;i++){
            if(population[i].getFitness() > fittest.getFitness()){
                fittest = population[i];
            }
        }

        return fittest;
    }
}
