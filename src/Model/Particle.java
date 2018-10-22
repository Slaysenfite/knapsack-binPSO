package Model;

import utilities.UtilityMethods;

public class Particle implements Comparable<Particle>, Cloneable{
	
	private byte[] position;
	private int fitness;
	private byte[] personalBest;
    private byte[] nbBest;
	private int totalVelocity;
	private double[] velocity;

	public Particle(int size) {
        this.fitness = 0;
        this.personalBest = new byte[size];
        this.nbBest = new byte[size];
        this.totalVelocity = 0;
        velocity = new double[size];
        position = new byte[size];
        int chance;
        for(int i = 0; i < size; i++) {

            chance = UtilityMethods.generateRandomBoundedInt(0, 100);
            if(chance <= 50) {
                position[i] = 0;
                velocity[i] = 0;
            }
            else {
                position[i] = 1;
                velocity[i] = 1;
            }
        }
        this.personalBest = this.position;
        this.nbBest = this.position;
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

    public int getTotalVelocity() {
        return totalVelocity;
    }

    public void setTotalVelocity(int totalVelocity) {
        this.totalVelocity = totalVelocity;
    }


    public byte[] getPersonalBest() {
        return personalBest;
    }

    public void setPersonalBest(byte[] personalBest) {
        this.personalBest = personalBest;
    }

    public byte[] getNbBest() {
        return nbBest;
    }

    public void setNbBest(byte[] nbBest) {
        this.nbBest = nbBest;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public void setVelocityAt(int index, double velElement) {
	    this.velocity[index] = velElement;
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

	public void setPositionArray(byte[] position) {
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
