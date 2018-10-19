package utilities;

public class Constants {
    public static final int BEST_SOLUTION = 306348;

    public static final int POPULATION_SIZE = 100;
    public static final int KNAPSACK_CAPACITY = 19000;
    public static final int MIN_GENERATIONS = 100;


    public static final int STAGNATION_LIMIT = 15;
    public static final String SWARM_MODEL = "lbest";
    public static boolean FORCE_EXPLORE = false;

    public static final int NEIGBOURHOOD_RADIUS = 5;

    public static final boolean OUTPUT_TEST_DATA = true;
    public static final String DATA_FILE_NAME = "TestCases\\testCase1500.txt";
    public static final String TEST_FILE_NAME = "TestResults\\"
            +"model-" + SWARM_MODEL + "_pop" + POPULATION_SIZE + "_iter" + MIN_GENERATIONS
            + "_stagLimit-" + STAGNATION_LIMIT + ".csv";

}
