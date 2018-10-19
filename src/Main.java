import Model.BPSOHelper;
import Model.Particle;
import Model.Item;
import Model.Swarm;
import utilities.Constants;
import utilities.TestUtility;
import utilities.UtilityMethods;

import java.io.IOException;
import java.util.ArrayList;

import static utilities.Constants.*;

public class Main {

    public static void main(String[] args) {

        int globalBest = 0;
        int prevBest;
        int stagnationCount = 0;
        int iterationBest;
        int iteration = 0;

        System.out.println("Initializing swarm");
        long start = System.currentTimeMillis();

        //Init Swarm
        ArrayList<Item> items = UtilityMethods.readDataFile(DATA_FILE_NAME);
        Swarm swarm = new Swarm(items, POPULATION_SIZE);

        System.out.println("Total weight of items: " + swarm.totalItemWeight());
        System.out.println("Total value of items: " + swarm.totalItemValue());

        while(iteration < MIN_GENERATIONS){

            prevBest = globalBest;

            for (Particle i : swarm.getPopulation()) {
                i.setFitness(BPSOHelper.greedyRepairFitness(i.getPositionArray(), items));
                if(i.getFitness() > BPSOHelper.greedyRepairFitness(i.getPersonalBest(), items)){
                    i.setPersonalBest(i.getPositionArray());
                }
                BPSOHelper.updateNeighbourhoodBest(i, swarm);
                if(Constants.SWARM_MODEL.equals("gbest")){
                    if(BPSOHelper.greedyRepairFitness(i.getPersonalBest(), items) > globalBest){
                        i.setNbBest(i.getPersonalBest());
                    }
                }
                if(Constants.SWARM_MODEL.equals("lbest")){
                    if(BPSOHelper.greedyRepairFitness(i.getPersonalBest(), items)
                            > BPSOHelper.greedyRepairFitness(i.getNbBest(), items)){
                        i.setNbBest(i.getPersonalBest());
                    }
                }

            }
            iterationBest = swarm.getPopulation().get(BPSOHelper.getBestIndex(swarm.getPopulation())).getFitness();
            //Update bests
            if(iterationBest > globalBest)
                globalBest = iterationBest;
            //Update velocity and position
            for (Particle i : swarm.getPopulation()) {
                BPSOHelper.updateVelocityAndPosition(i, iteration);
            }

            if(globalBest == prevBest){
                stagnationCount++;
                if(stagnationCount > STAGNATION_LIMIT){
                    if(!Constants.FORCE_EXPLORE) {
                        Constants.FORCE_EXPLORE = true;
                        System.out.println("Forced exploration activated");
                    }
                }
            }
            else {
                stagnationCount = 0;
                if(Constants.FORCE_EXPLORE){
                    Constants.FORCE_EXPLORE = false;
                    System.out.println("Forced exploration deactivated");
                }

            }

            TestUtility.generationGreatest.add(TestUtility.maxFitnessOfGeneration(swarm));
            TestUtility.generationMean.add(TestUtility.meanFitnessOfGeneration(swarm));
            TestUtility.generationNumber.add(iteration);

            System.out.println("Iteration: " + iteration + " Best Fitness: " + globalBest);

            iteration++;
        }

        if(Constants.OUTPUT_TEST_DATA){
            try {
                TestUtility.writeTestDataToFile(Constants.TEST_FILE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Program execution time: " + 1.0 *(end - start)/1000 + "s");
    }
}
