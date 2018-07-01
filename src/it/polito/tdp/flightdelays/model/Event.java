package it.polito.tdp.flightdelays.model;

import java.time.LocalDateTime;

public class Event implements Comparable<Event>{

	private int id_passeggero ;
	private Flight flight ;
	private LocalDateTime arrivalDate ;
	
	public Event(int id, Flight flight, LocalDateTime arrivalDate) {
		this.id_passeggero = id ;
		this.flight = flight;
		this.arrivalDate = arrivalDate;
	}
	public Flight getFlight() {
		return flight;
	}
	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}
	
	public int getId_passeggero() {
		return id_passeggero;
	}
	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.arrivalDate.compareTo(o.arrivalDate);
	}
	
	
	
}
