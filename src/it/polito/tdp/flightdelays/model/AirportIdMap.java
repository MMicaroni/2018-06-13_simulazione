package it.polito.tdp.flightdelays.model;

import java.util.HashMap;

public class AirportIdMap extends HashMap<Integer,Airport>{

public Airport get(Airport airport) {
		
		Airport old = super.get(airport.getId());
		if(old==null) {
			
			super.put(airport.getId(), airport);
			return airport;
		}
		
		return old;
		
	}
}

