package utilities;

import Model.Item;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class UtilityMethods {

    public static int generateRandomBoundedInt(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public static double generateU(){
        return (generateRandomBoundedInt(0, 99) * 1.0)/100;
    }

    public static ArrayList<Item> readDataFile(String filename) {
        FileReader file;
        ArrayList<Item> items = new ArrayList<>();
        Scanner input;
        try {
            file = new FileReader(filename);
            input = new Scanner(file);
            while(input.hasNextLine()) {
                items.add(new Item(input.nextInt(), input.nextInt()));
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }
}
