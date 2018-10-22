package Model;

import utilities.Constants;
import utilities.UtilityMethods;

import java.util.ArrayList;

import static utilities.Constants.LAMBDA;
import static utilities.UtilityMethods.generateU;

public class BPSOHelper {

    private static int DISTANCE_ERROR = -1;

    public static int hammingDistance(byte[] c1, byte[] c2){
        int hDist = 0;
        if(c1.length == c2.length){
            for(int i = 0 ; i < c1.length; i++){
                if(c1[i] != c2[i])
                    hDist++;
            }
            return hDist;
        } else return DISTANCE_ERROR;
    }

    private static double sigmoidFunction(double x, double lambda){
        return 1/(1 + Math.exp(-x*lambda));
    }

    private static double tanHNormalization(double x) {
        return 0.5*Math.tanh(x) + 0.5;
    }

    private static double sineNormalization(double x) {
        return 0.5*Math.sin(x);
    }

    public static int greedyRepairFitness(byte[] individual, ArrayList<Item> items) {

        while (individualWeight(individual, items) > Constants.KNAPSACK_CAPACITY){
            individual[indexOfMinProfit(individual, items)] = 0;
        }
        return individualValue(individual, items);
    }

    public static int individualValue(byte[] individual, ArrayList<Item> items) {
        int value = 0;
        for(int c = 0; c < items.size(); c++) {
            if(individual[c] == 1) {
                value += items.get(c).getValue();
            }
        }
        return value;
    }

    private static int indexOfMinProfit(byte[] individual, ArrayList<Item> items) {
        int index = 0;
        double min = items.get(index).getGreedyRatio();
        for(int i = 1; i < individual.length; i++) {
            if(individual[i] == 1 && items.get(i).getGreedyRatio() < min) {
                index = i;
                min = items.get(i).getGreedyRatio();
            }
        }
        return index;
    }

    private static int individualWeight(byte[] individual, ArrayList<Item> items) {
        int weight = 0;
        for(int c = 0; c < items.size(); c++) {
            if(individual[c] == 1) {
                weight += items.get(c).getWeight();
            }
        }
        return weight;
    }

    public static int getBestIndex(ArrayList<Particle> particles){
        int max = particles.get(0).getFitness();
        int index = 0;
        for(int i = 1; i < particles.size(); i++){
            if(max <particles.get(i).getFitness()) {
                max = particles.get(i).getFitness();
                index = i;
            }
        }
        return index;
    }

    public static void updateNeighbourhoodBest(Particle individual, Swarm population){
        int index = population.getIndexOf(individual);
        ArrayList<Particle> neighbourhood = new ArrayList<>();
        for(int i = 0; i < Constants.NEIGBOURHOOD_RADIUS; i++){
            int i1 = (index + i) % population.getPopulation().size();
            int i2 = ((index - i) + population.getPopulation().size())% population.getPopulation().size();
            neighbourhood.add(population.getPopulation().get(i1));
            //neighbourhood.add(population.getPopulation().get((index - 1) % population.getPopulation().size()));
        }
    }

    public static void updateVelocityAndPosition(Particle individual, int iterNum){
        double rand;
        double rCoff;
        double social;
        double cognitive;
        double velCom;
        int size = individual.getPositionArray().length;
        for(int i = 0; i < size; i++){
            rCoff = (UtilityMethods.generateRandomBoundedInt(1, 100)*1.0)/100 ;
            if(iterNum < 10){
                cognitive = (individual.getPersonalBest()[i])*rCoff;
                social = (individual.getNbBest()[i])*rCoff;
            }
            else{
                cognitive = (individual.getPersonalBest()[i] - individual.getPositionArray()[i])*rCoff;
                social = (individual.getNbBest()[i] - individual.getPositionArray()[i])*rCoff;
            }
            if(Constants.FORCE_EXPLORE){
                cognitive = (individual.getPersonalBest()[i]);
                social = (individual.getNbBest()[i]);
            }
            if(Constants.FORCE_CONVERGE){
                cognitive = 0;
                social = (individual.getNbBest()[i]);
            }
            velCom = individual.getVelocity()[i] + cognitive + social;
            individual.setVelocityAt(i, velCom);
            rand = (UtilityMethods.generateRandomBoundedInt(1, 100)*1.0)/100 ;
            if(rand < Math.abs(sigmoidFunction(velCom, LAMBDA)))
                individual.getPositionArray()[i] = 1;
            else individual.getPositionArray()[i] = 0;
        }
    }


    public static void updateVelocityAndPositionXOR(Particle individual, int iterNum){
        double rand;
        double rCoff;
        double social;
        double cognitive;
        double velCom;
        int size = individual.getPositionArray().length;
        for(int i = 0; i < size; i++){
            rCoff = (UtilityMethods.generateRandomBoundedInt(1, 100)*1.0)/100;
            if(iterNum < 10){
                cognitive = (individual.getPersonalBest()[i])*generateU();
                social = (individual.getNbBest()[i])*generateU();
            }
            else{
                cognitive = (individual.getPersonalBest()[i]^individual.getPositionArray()[i])*generateU();
                social = (individual.getNbBest()[i]^individual.getPositionArray()[i])*generateU();
            }
            if(Constants.FORCE_EXPLORE){
                cognitive = (individual.getPersonalBest()[i]);
                social = (individual.getNbBest()[i]);
            }
            if(Constants.FORCE_CONVERGE){
                cognitive = 0;
                social = (individual.getNbBest()[i]);
            }
            velCom = individual.getVelocity()[i] + cognitive + social;
            individual.setVelocityAt(i, velCom);
            rand = (UtilityMethods.generateRandomBoundedInt(1, 100)*1.0)/100 ;
            if(rand < Math.abs(tanHNormalization(velCom)))
                individual.getPositionArray()[i] = 1;
            else individual.getPositionArray()[i] = 0;
        }
    }

    public static void updateVelocityAndPositionStandard(Particle individual, int iterNum){
        double rand;
        double social;
        double cognitive;
        double velCom;
        int size = individual.getPositionArray().length;
        for(int i = 0; i < size; i++){

            cognitive = (individual.getPersonalBest()[i]^individual.getPositionArray()[i])*generateU();
            social = (individual.getNbBest()[i]^individual.getPositionArray()[i])*generateU();
            velCom = individual.getVelocity()[i] + cognitive + social;
            individual.setVelocityAt(i, velCom);
            rand = (UtilityMethods.generateRandomBoundedInt(1, 100)*1.0)/100 ;
            if(rand < Math.abs(sigmoidFunction(velCom, LAMBDA)))
                individual.getPositionArray()[i] = 1;
            else individual.getPositionArray()[i] = 0;
        }
    }

}
