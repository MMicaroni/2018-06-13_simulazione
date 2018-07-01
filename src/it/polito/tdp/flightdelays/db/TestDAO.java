package it.polito.tdp.flightdelays.db;

import it.polito.tdp.flightdelays.model.AirlineIdMap;
import it.polito.tdp.flightdelays.model.AirportIdMap;

public class TestDAO {

	public static void main(String[] args) {

		FlightDelaysDAO dao = new FlightDelaysDAO() ;
		AirportIdMap airportIdMap  = new AirportIdMap() ;
		AirlineIdMap airlineIdMap = new AirlineIdMap() ;
		System.out.println(dao.loadAllAirlines(airlineIdMap));
//		System.out.println(dao.loadAllAirports());
//		System.out.println(dao.loadAllFlights());
	}

}
