package Model;

import java.util.ArrayList;

public class Swarm {
	
	private ArrayList<Item> items;
	private ArrayList<Particle> population;
	
	public Swarm(ArrayList<Item> items, int populationSize) {
		this.items = items;
		population = new ArrayList<>();
		for(int i = 0; i < populationSize; i++) {
			population.add(new Particle(items.size()));
		}
	}

    public Swarm(ArrayList<Item> items, ArrayList<Particle> population) {
        this.items = items;
        this.population = population;
    }

	public void setPopulation(ArrayList<Particle> population) {
		this.population = population;
	}

    public ArrayList<Particle> getPopulation() {
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
