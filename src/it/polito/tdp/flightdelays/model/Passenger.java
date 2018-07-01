package it.polito.tdp.flightdelays.model;

public class Passenger {

	private int id ;
	private int voli ;
	private double delay ;
	public Passenger(int id, int voli, double delay) {
		super();
		this.id = id;
		this.voli = voli;
		this.delay = delay;
	}
	public int getId() {
		return id;
	}
	public int getVoli() {
		return voli;
	}
	public double getDelay() {
		return delay;
	}
	public void addVolo() {
		voli++ ;
	}
	public void addDelay(double ritardo) {
		delay+=ritardo;
	}
	public String toString() {
		return id+" ritardo: "+delay+" voli: "+voli ;
	}
}
