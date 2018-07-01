package it.polito.tdp.flightdelays.model;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.flightdelays.db.FlightDelaysDAO;

public class Model {

	
	private List<Airport> airports ;
	private AirportIdMap airpMap ;
	private FlightDelaysDAO flyDAO ;
	private List<Route> routes ;
	private SimpleDirectedWeightedGraph<Airport, DefaultWeightedEdge> graph ;
	private List<Airline> airlines ;
	
	//ATTRIBUTI DI PROVA(POTREBBERO ESSERE UTILI)
	private AirlineIdMap airlineMap ;
	
	public Model () {
		
		flyDAO = new FlightDelaysDAO() ;
		airlineMap = new AirlineIdMap() ;
		airpMap = new AirportIdMap() ;
		airlines = flyDAO.loadAllAirlines(airlineMap) ;
		airports = flyDAO.loadAllAirports(airpMap) ;
		
	}
	
	
	public void  createGraph(Airline air) {
		
		graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class) ;
		
		Graphs.addAllVertices(graph, airports) ;
		
		 routes = flyDAO.getAllRoutesForAirline(air,airpMap) ;
		
		for(Route r : routes ) {
			
			Airport sourceAirport = r.getOrigin();
			Airport destinationAirport = r.getDestination();
			
			double distance = this.calcolaDistanza(sourceAirport, destinationAirport) ;
			
			Graphs.addEdge(graph, sourceAirport, destinationAirport, r.getAVERAGE_DELAY()/distance) ;
			r.setWeight(r.getAVERAGE_DELAY()/distance);
		}
		System.out.println(graph.vertexSet().size()+" "+graph.edgeSet().size());
		
	}
	

	
	public double calcolaDistanza(Airport sourceAirport, Airport destinationAirport) {
		double distance = LatLngTool.distance(new LatLng(sourceAirport.getLatitude(), 
				sourceAirport.getLongitude()), new LatLng(destinationAirport.getLatitude(), 
						destinationAirport.getLongitude()), LengthUnit.KILOMETER);
		return distance ;
	}
	
	
	public List<Route> getTenWorstRoutes(){
		List<Route> worst = new ArrayList<>() ;
		Collections.sort(routes);
		
		for(int i=0; i<10; i++) {
			worst.add(routes.get(i)) ;
		}
		
		return worst ;
	}


	public List<Airport> getAirports() {
		return airports;
	}


	public List<Route> getRoutes() {
		return routes;
	}


	public List<Airline> getAirlines() {
		return airlines;
	}


	public Flight getFirstFlight2015Airport(Airport iniziale,Airline airline) {
		int year = 2015 ;
		return flyDAO.getFirstFlight2015Airport(airline,iniziale,year,airpMap);
	}


	public AirportIdMap getAirpMap() {
		return airpMap;
	}


	public Flight getNextFlightAirport(Airport actual, Airline airline, LocalDateTime arrivalDate) {
		
		return flyDAO.getNextFlightAirport(airline,actual,arrivalDate,airpMap);
	}


	public List<Airport> getAirportsFromAirline(Airline airline) {
		
		return flyDAO.getAirportsFromAirline(airline,airpMap);
	}
	
	
}
