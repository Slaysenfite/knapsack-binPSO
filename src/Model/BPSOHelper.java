package Model;

import utilities.Constants;

import java.util.ArrayList;

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

    public static double sigmoidFunction(double x){
        return 1/(1 + Math.exp(-x));
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

    public static int indexOfMinProfit(byte[] individual, ArrayList<Item> items) {
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

    public static int individualWeight(byte[] individual, ArrayList<Item> items) {
        int weight = 0;
        for(int c = 0; c < items.size(); c++) {
            if(individual[c] == 1) {
                weight += items.get(c).getWeight();
            }
        }
        return weight;
    }

    public static int getGlobalBest(ArrayList<Integer> neighBests){
        int max = neighBests.get(0);
        for(int i = 1; i < neighBests.size(); i++){
            if(max < neighBests.get(i))
                max = neighBests.get(i);
        }
        return max;
    }

    public static int updateVelocity(Particle ind){

        return 0;
    }

    public static void updatePosition(Particle ind){

    }

}
