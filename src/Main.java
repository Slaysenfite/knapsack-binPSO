import Model.BPSOHelper;
import Model.Particle;
import Model.Item;
import Model.Swarm;
import utilities.Constants;
import utilities.UtilityMethods;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static utilities.Constants.*;

public class Main {

    private int globalBest = 0;
    private ArrayList<Integer> neighbourhoodBests;

    public static void main(String[] args) {

        System.out.println("Starting Evolutionary Process");
        long start = System.currentTimeMillis();

        DecimalFormat df = new DecimalFormat("#.##");

        //Init Swarm
        ArrayList<Item> items = UtilityMethods.readDataFile(Constants.DATA_FILE_NAME);
        Swarm swarm = new Swarm(items, Constants.POPULATION_SIZE);
        //Update fitness
        for(Particle i : swarm.getPopulation()){
            i.setFitness(BPSOHelper.greedyRepairFitness(i.getPositionArray(), items));
        }

        
        System.out.println("Total weight of items: " + swarm.totalItemWeight());
        System.out.println("Total value of items: " + swarm.totalItemValue());
        while(!stoppingCondition){
            //Update bests
            for (Particle i : swarm.getPopulation()) {
                System.out.println(i.getFitness());
            }
            //Update velocity and position
            for (Particle i : swarm.getPopulation()) {
                System.out.println(i.positionToString());
            }

            /*for each particle i = 1, . . . , ns do
            //set the personal best position
                if f(xi) < f(yi) then
                    yi = xi;
            end
            //set the neighborhood best position
            if f(yi) < f(ˆyi) then
                    ˆy = yi;
            end
                    end
            for each particle i = 1, . . . , ns do
                update the velocity using equation (16.6);
            update the position using equation (16.1);
            end*/
            stoppingCondition = true;
        }

        long end = System.currentTimeMillis();
        System.out.println("Program execution time: " + 1.0 *(end - start)/1000 + "s");

    }
}
