package utilities;

public class UtilityMethods {
    public static int generateRandomBoundedInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
