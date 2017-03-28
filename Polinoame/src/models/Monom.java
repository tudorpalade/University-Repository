package models;

public class Monom implements Comparable<Object>{
	private double coeficient;
	private int grad;
	
	public double getCoeficient(){
		return this.coeficient;
	}
	public int getGrad(){
		return this.grad;
	}
	
	public Monom(final double coeficient, final int grad){
		this.coeficient = coeficient;
		this.grad = grad;
	}
	

	@Override
	public int compareTo(Object monom) {
		if (this.grad > ((Monom)monom).grad)
			return -1;
		else 
			return 1;
	}
}
