package it.polito.tdp.flightdelays.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.flightdelays.model.Airline;
import it.polito.tdp.flightdelays.model.AirlineIdMap;
import it.polito.tdp.flightdelays.model.Airport;
import it.polito.tdp.flightdelays.model.AirportIdMap;
import it.polito.tdp.flightdelays.model.Flight;
import it.polito.tdp.flightdelays.model.Route;

public class FlightDelaysDAO {

	public List<Airline> loadAllAirlines() {
		String sql = "SELECT ID, AIRLINE from airlines";
		List<Airline> result = new ArrayList<Airline>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Airline(rs.getInt("ID"), rs.getString("AIRLINE")));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Airline> loadAllAirlines(AirlineIdMap airlineIdMap) {
		String sql = "SELECT ID, AIRLINE from airlines";
		List<Airline> result = new ArrayList<Airline>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Airline airline = new Airline(rs.getInt("ID"), rs.getString("AIRLINE")) ;
				result.add(airlineIdMap.get(airline));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<Airport> loadAllAirports(AirportIdMap airpMap) {
		String sql = "SELECT ID, IATA_CODE, AIRPORT, CITY, STATE, COUNTRY, LATITUDE, LONGITUDE, TIMEZONE_OFFSET FROM airports";
		List<Airport> result = new ArrayList<Airport>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Airport airport = new Airport(rs.getInt("ID"), rs.getString("IATA_CODE"), rs.getString("AIRPORT"), rs.getString("CITY"),
						rs.getString("STATE"), rs.getString("COUNTRY"), rs.getDouble("LATITUDE"), rs.getDouble("LONGITUDE"),rs.getDouble("TIMEZONE_OFFSET"));
				result.add(airpMap.get(airport));
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Flight> loadAllFlights(AirportIdMap airpMap) {
		String sql = "SELECT ID, AIRLINE_ID, FLIGHT_NUMBER,TAIL_NUMBER, ORIGIN_AIRPORT_ID, DESTINATION_AIRPORT_ID, SCHEDULED_DEPARTURE_DATE, "
				+ "ARRIVAL_DATE, DEPARTURE_DELAY, ARRIVAL_DELAY, ELAPSED_TIME, DISTANCE FROM flights";
		List<Flight> result = new LinkedList<Flight>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Flight flight = new Flight(rs.getInt("ID"), rs.getInt("AIRLINE_ID"), rs.getInt("FLIGHT_NUMBER"), rs.getString("TAIL_NUMBER"),
						airpMap.get(rs.getInt("ORIGIN_AIRPORT_ID")), airpMap.get(rs.getInt("DESTINATION_AIRPORT_ID")),
						rs.getTimestamp("SCHEDULED_DEPARTURE_DATE").toLocalDateTime(),
						rs.getTimestamp("ARRIVAL_DATE").toLocalDateTime(), rs.getInt("DEPARTURE_DELAY"),
						rs.getInt("ARRIVAL_DELAY"), rs.getDouble("ELAPSED_TIME"), rs.getInt("DISTANCE"));
				result.add(flight);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Route> getAllRoutesForAirline(Airline air, AirportIdMap airpMap) {
		String sql = "SELECT f.ORIGIN_AIRPORT_ID, f.DESTINATION_AIRPORT_ID, AVG(ARRIVAL_DELAY) as avg " + 
				"FROM flights as f " + 
				"WHERE f.AIRLINE_ID = ? " + 
				"GROUP BY f.ORIGIN_AIRPORT_ID, f.DESTINATION_AIRPORT_ID";
		
		List<Route> result = new ArrayList<Route>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, air.getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Airport origin = airpMap.get(rs.getInt("f.ORIGIN_AIRPORT_ID")) ;
				Airport destination = airpMap.get(rs.getInt("f.DESTINATION_AIRPORT_ID")) ;
				
				if(origin != null && destination != null) {
					
					result.add(new Route(origin, destination, rs.getDouble("avg"))) ;
					
				}
				
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public Flight getFirstFlight2015Airport(Airline airl, Airport iniziale, int year,AirportIdMap airpMap) {
		String sql ="SELECT * " + 
				"FROM flights as f " + 
				"WHERE f.AIRLINE_ID = ? " + 
				"and f.ORIGIN_AIRPORT_ID= ? " + 
				"and YEAR(SCHEDULED_DEPARTURE_DATE) = ? " + 
				"ORDER BY SCHEDULED_DEPARTURE_DATE asc " + 
				"limit 1";
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			Flight flight = null ;
			st.setInt(1, airl.getId());
			st.setInt(2, iniziale.getId());
			st.setInt(3, year);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				 flight = new Flight(rs.getInt("ID"), rs.getInt("AIRLINE_ID"), rs.getInt("FLIGHT_NUMBER"), rs.getString("TAIL_NUMBER"),
						airpMap.get(rs.getInt("ORIGIN_AIRPORT_ID")), airpMap.get(rs.getInt("DESTINATION_AIRPORT_ID")),
						rs.getTimestamp("SCHEDULED_DEPARTURE_DATE").toLocalDateTime(),
						rs.getTimestamp("ARRIVAL_DATE").toLocalDateTime(), rs.getInt("DEPARTURE_DELAY"),
						rs.getInt("ARRIVAL_DELAY"), rs.getDouble("ELAPSED_TIME"), rs.getInt("DISTANCE"));
				 	
			}
			
			
			conn.close();
			return flight ;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

	}

	//PRENDI IL PROSSIMO VOLO DA QUELL'AEREOPOROTO
	public Flight getNextFlightAirport(Airline airline, Airport actual, LocalDateTime arrivalDate,AirportIdMap airpMap) {
		String sql = "SELECT * " + 
				"FROM flights as f " + 
				"WHERE f.AIRLINE_ID = ? " + 
				"and f.ORIGIN_AIRPORT_ID=? " + 
				"and YEAR(SCHEDULED_DEPARTURE_DATE) >= ? " + 
				"ORDER BY SCHEDULED_DEPARTURE_DATE asc " + 
				"limit 1";
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			Flight flight = null ;
			st.setInt(1, airline.getId());
			st.setInt(2, actual.getId());
			st.setTimestamp(3, Timestamp.valueOf(arrivalDate));
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				 flight = new Flight(rs.getInt("ID"), rs.getInt("AIRLINE_ID"), rs.getInt("FLIGHT_NUMBER"), rs.getString("TAIL_NUMBER"),
							airpMap.get(rs.getInt("ORIGIN_AIRPORT_ID")), airpMap.get(rs.getInt("DESTINATION_AIRPORT_ID")),
							rs.getTimestamp("SCHEDULED_DEPARTURE_DATE").toLocalDateTime(),
							rs.getTimestamp("ARRIVAL_DATE").toLocalDateTime(), rs.getInt("DEPARTURE_DELAY"),
							rs.getInt("ARRIVAL_DELAY"), rs.getDouble("ELAPSED_TIME"), rs.getInt("DISTANCE"));
				 	
			}
			
			
			conn.close();
			return flight ;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	//PRENDI GLI AEREOPORTI CHE HANNO ALMENO UN VOLO IN PARTENZA NEL 2015, CON LA COMPAGNIA AEREA SELEZIONATA
	public List<Airport> getAirportsFromAirline(Airline airline, AirportIdMap airpMap) {
		String sql = "SELECT f.ORIGIN_AIRPORT_ID, f.DESTINATION_AIRPORT_ID " + 
				"FROM  airlines as a, flights as f " + 
				"WHERE a.ID = f.AIRLINE_ID "
				+ "and a.ID = ? "
				+" and YEAR(f.SCHEDULED_DEPARTURE_DATE) = 2015 " + 
				"GROUP BY f.ORIGIN_AIRPORT_ID, f.DESTINATION_AIRPORT_ID";
		
		List<Airport> result = new ArrayList<Airport>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, airline.getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
			
			Airport source = airpMap.get(rs.getInt("f.ORIGIN_AIRPORT_ID")) ;
			
			
			
			if(!result.contains(source))
				result.add(source) ;

			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
