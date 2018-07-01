package it.polito.tdp.flightdelays.model;

import java.util.HashMap;

public class AirlineIdMap extends HashMap<Integer,Airline> {

public Airline get(Airline airline) {
		
		Airline old = super.get(airline.getId());
		if(old==null) {
			
			super.put(airline.getId(), airline);
			return airline;
		}
		
		return old;
		
	}
}
