package it.polito.tdp.flightdelays.model;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Simulatore {

	//Parametri iniziali
	private int K_passeggeri ;
	private int V_voli ;
	private Airline airline ;
	private Model model ;
	
	public Simulatore(Model model, int K, int V, Airline airline) {
		
		K_passeggeri = K ;
		V_voli = V ;
		this.model = model ;
		this.airline = airline ;
		

	}

	//Modello del mondo 
	Map<Integer,Passenger> delay = new TreeMap<>() ;
	
	//Coda degli eventi
	PriorityQueue<Event> queue = new PriorityQueue<>() ;
	
	//Inizializzazione 
	public void init() {
		
		//Genero i passegeri / MAPPA (ID_PASSEGGERO,RITARDO ACCUMULATO)
		for(int i=1 ; i<=K_passeggeri; i++ ) {
			delay.put(i, new Passenger(i, 0, 0.0)) ;
			
			List<Airport> airports = model.getAirportsFromAirline(airline) ;
			
			int rand = (int)(Math.random()*(airports.size())) ;
			Airport iniziale = new Airport(airports.get(rand)) ;
			System.out.println("Aereoporto iniziale "+iniziale+" Passeggero ID: "+i);
			Flight first = model.getFirstFlight2015Airport(iniziale,airline) ;
			System.out.println(first);
		
			if(first!=null)
				queue.add(new Event(i, first, first.getArrivalDate())) ;
			
		}
	}
	
	public void run() {
		Event e;
		while((e = queue.poll()) != null) {
			processEvent(e);
		}
	}

	private void processEvent(Event e) {
		
		Airport actual = model.getAirpMap().get(e.getFlight().getDestinationAirport().getId()) ;
		double ritardo = e.getFlight().getArrivalDelay() ;
		Passenger p = delay.get(e.getId_passeggero()) ;
		p.addVolo();
		p.addDelay(ritardo);
		Flight prossimo = null;
		if(actual!=null && p.getVoli()<V_voli) {
		 prossimo = model.getNextFlightAirport(actual,airline,e.getArrivalDate()) ;
		 queue.add(new Event(e.getId_passeggero(), prossimo, prossimo.getArrivalDate())) ;
		}
		
		
	}

	public Map<Integer, Passenger> getDelay() {
		return delay;
	}


}
