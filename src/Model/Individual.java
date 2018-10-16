package Model;

import utilities.UtilityMethods;

public class Individual implements Comparable<Individual>, Cloneable{
	
	private byte[] chromosome;
	private int fitness;
	private boolean selected;

	public Individual(int size) {
        this.fitness = 0;
        this.selected = false;
        chromosome = new byte[size];
        int chance;
        for(int i = 0; i < size; i++) {
            chance = UtilityMethods.generateRandomBoundedInt(0, 100);
            if(chance <= 33) chromosome[i] = 1;
            else chromosome[i] = 0;
        }
    }

	public Individual(byte[] chromosome) {
		this.chromosome = chromosome;
		this.selected = false;
	}
	
	public int getSize() {
		return chromosome.length;
	}

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public byte[] getChromosomeArray() {
		return chromosome;
	}

	public String chromosomeToString() {
		String ret = "";
		for (byte gene : chromosome)
			ret += gene;
		return ret + "\n";
	}

	public void setChromosome(byte[] chromosome) {
		this.chromosome = chromosome;
	}

	@Override
	public int compareTo(Individual c) {
		if(this.fitness > c.getFitness()) return 1;
		if(this.fitness == c.getFitness()) return 0;
		if(this.fitness < c.getFitness()) return -1;
		else return -2;
	}
	
	@Override
	public Individual clone() {
		try {
			return (Individual) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

}
