package it.polito.tdp.flightdelays.model;

public class Airport {

	private int id;
	private String iata ;
	private String name;
	private String city;
	private String state;
	private String country;
	private double latitude;
	private double longitude;
	private double timezone_offset ;
	
	
	public Airport(int id,String iata, String name, String city, String state, String country, double latitude,
			double longitude, double timezone_offset) {
		this.id = id;
		this.iata = iata ;
		this.name = name;
		this.city = city;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timezone_offset = timezone_offset ;
	}

	public Airport(Airport a) {
		this.id = a.id;
		this.iata = a.iata ;
		this.name = a.name;
		this.city = a.city;
		this.state = a.state;
		this.country = a.country;
		this.latitude = a.latitude;
		this.longitude = a.longitude;
		this.timezone_offset = a.timezone_offset ;
	}

	public int getId() {
		return id;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getTimezone_offset() {
		return timezone_offset;
	}

	@Override
	public String toString() {
	return name ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
