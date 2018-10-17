package Model;

import utilities.UtilityMethods;

public class Particle implements Comparable<Particle>, Cloneable{
	
	private byte[] position;
	private int fitness;
	private int personalBest;
	private int velocity;

	public Particle(int size) {
        this.fitness = 0;
        position = new byte[size];
        int chance;
        for(int i = 0; i < size; i++) {
            chance = UtilityMethods.generateRandomBoundedInt(0, 100);
            if(chance <= 50) position[i] = 1;
            else position[i] = 0;
        }
    }

	public Particle(byte[] position) {
		this.position = position;
	}
	
	public int getSize() {
		return position.length;
	}

    public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getPersonalBest() {
        return personalBest;
    }

    public void setPersonalBest(int personalBest) {
        this.personalBest = personalBest;
    }



    public byte[] getPositionArray() {
		return position;
	}

	public String positionToString() {
		String ret = "";
		for (byte gene : position)
			ret += gene;
		return ret + "\n";
	}

	public void setPosition(byte[] position) {
		this.position = position;
	}

	@Override
	public int compareTo(Particle c) {
		if(this.fitness > c.getFitness()) return 1;
		if(this.fitness == c.getFitness()) return 0;
		if(this.fitness < c.getFitness()) return -1;
		else return -2;
	}
	
	@Override
	public Particle clone() {
		try {
			return (Particle) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

}
