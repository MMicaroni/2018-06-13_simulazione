package it.polito.tdp.flightdelays.model;

import java.time.LocalDateTime;

public class Flight {

	private int id;
	private int airlineId;
	//AL POSTO DELL'ID SI PUO' INSERIRE IL RIFERIMENTO ALL'OGGETTO
	private Airline airline ;
	//-----------------------
	private int flightNumber;
	private String tailnumber ;
	private Airport originAirport;
	private Airport destinationAirport;
	private LocalDateTime scheduledDepartureDate;
	private LocalDateTime arrivalDate;
	private int departureDelay;
	private int arrivalDelay;
	private double elapsed_time;
	private int distance;
	
	//COSTRUTTORE CON IL RIFERIMENTO ALLA LINEA AEREA AL POSTO DELL'ID
	public Flight(int id, Airline airline, int flightNumber,String tailnumber, Airport originAirport, Airport destinationAirport,
			LocalDateTime scheduledDepartureDate, LocalDateTime arrivalDate, int departureDelay, int arrivalDelay,
			double elapsed_time, int distance) {
		this.id = id;
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.tailnumber = tailnumber ;
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.scheduledDepartureDate = scheduledDepartureDate;
		this.arrivalDate = arrivalDate;
		this.departureDelay = departureDelay;
		this.arrivalDelay = arrivalDelay;
		this.elapsed_time = elapsed_time ;
		this.distance = distance;
	}
	
	public Flight(int id, int airlineId, int flightNumber,String tailnumber, Airport originAirport, Airport destinationAirport,
			LocalDateTime scheduledDepartureDate, LocalDateTime arrivalDate, int departureDelay, int arrivalDelay,
			double elapsed_time, int distance) {
		this.id = id;
		this.airlineId = airlineId;
		this.flightNumber = flightNumber;
		this.tailnumber = tailnumber ;
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.scheduledDepartureDate = scheduledDepartureDate;
		this.arrivalDate = arrivalDate;
		this.departureDelay = departureDelay;
		this.arrivalDelay = arrivalDelay;
		this.elapsed_time = elapsed_time ;
		this.distance = distance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAirlineId() {
		return airlineId;
	}

	public void setAirline(int airline) {
		this.airlineId = airline;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Airport getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(Airport originAirport) {
		this.originAirport = originAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public LocalDateTime getScheduledDepartureDate() {
		return scheduledDepartureDate;
	}

	public void setScheduledDepartureDate(LocalDateTime scheduledDepartureDate) {
		this.scheduledDepartureDate = scheduledDepartureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public int getDepartureDelay() {
		return departureDelay;
	}

	public void setDepartureDelay(int departureDelay) {
		this.departureDelay = departureDelay;
	}

	public int getArrivalDelay() {
		return arrivalDelay;
	}

	public void setArrivalDelay(int arrivalDelay) {
		this.arrivalDelay = arrivalDelay;
	}



	public double getElapsed_time() {
		return elapsed_time;
	}

	public void setElapsed_time(double elapsed_time) {
		this.elapsed_time = elapsed_time;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getTailnumber() {
		return tailnumber;
	}

	public void setTailnumber(String tailnumber) {
		this.tailnumber = tailnumber;
	}

	public Airline getAirline() {
		return airline;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Flight [id=");
		builder.append(id);
		builder.append(", airlineId=");
		builder.append(airlineId);
		builder.append(", flightNumber=");
		builder.append(flightNumber);
		builder.append(", originAirport=");
		builder.append(originAirport);
		builder.append(", destinationAirport=");
		builder.append(destinationAirport);
		builder.append(", scheduledDepartureDate=");
		builder.append(scheduledDepartureDate);
		builder.append(", arrivalDate=");
		builder.append(arrivalDate);
		builder.append(", departureDelay=");
		builder.append(departureDelay);
		builder.append(", arrivalDelay=");
		builder.append(arrivalDelay);
		builder.append(", elapsed_time=");
		builder.append(elapsed_time);
		builder.append(", distance=");
		builder.append(distance);
		builder.append("]");
		return builder.toString();
	}
}
