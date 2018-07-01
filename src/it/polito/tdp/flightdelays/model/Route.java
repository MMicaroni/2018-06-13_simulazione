package it.polito.tdp.flightdelays.model;

public class Route implements Comparable<Route>{

	private Airport origin ;
	private Airport destination ;
	private double AVERAGE_DELAY ;
	private double weight ;
	public Route(Airport origin, Airport destination, double aVERAGE_DELAY) {
		super();
		this.origin = origin;
		this.destination = destination;
		AVERAGE_DELAY = aVERAGE_DELAY;
	}
	public Airport getOrigin() {
		return origin;
	}
	public Airport getDestination() {
		return destination;
	}
	public double getAVERAGE_DELAY() {
		return AVERAGE_DELAY;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	@Override
	public int compareTo(Route o) {
		if(this.weight>o.weight)
			return -1 ;
		if(this.weight<o.weight)
			return 1 ;
		return 0;
	}
	
	
}
