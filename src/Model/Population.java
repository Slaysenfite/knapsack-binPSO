package Model;

import java.util.ArrayList;

public class Population {
	
	private ArrayList<Item> items;
	private ArrayList<Individual> population;
	
	public Population(ArrayList<Item> items, int populationSize) {
		this.items = items;
		population = new ArrayList<>();
		for(int i = 0; i < populationSize; i++) {
			population.add(new Individual(items.size()));
		}
	}

    public Population(ArrayList<Item> items, ArrayList<Individual> population) {
        this.items = items;
        this.population = population;
    }

	public void setPopulation(ArrayList<Individual> population) {
		this.population = population;
	}

    public ArrayList<Individual> getPopulation() {
		return population;
	}

    public int totalItemWeight(){
	    int weight = 0;
	    for(Item item : items)
	        weight += item.getWeight();
	    return weight;
    }

    public int totalItemValue(){
        int value = 0;
        for(Item item : items)
            value  += item.getValue();
        return value ;
    }

}
