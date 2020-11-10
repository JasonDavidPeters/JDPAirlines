package com.jasondavidpeters.JDPAirlines.dbms;

public class Flight {

    private String airportCode;
    private String airportName;
    private String state;
    private String city;
    private double latitude;
    private double longitude;

    public Flight(String airportCode, String airportName, String state, String city, double latitude, double longitude) {
	this.airportCode = airportCode;
	this.airportName = airportName;
	this.state = state;
	this.city = city;
	this.latitude = latitude;
	this.longitude = longitude;
    }

    public String toString() {
	return "" + airportName + ", " + city + ", " + state + " (" + airportCode + ")";
    }
    
    public double getLat() {
	return latitude;
    }
    public double getLong() {
	return longitude;
    }
    public String getAirportCode() {
	return this.airportCode;
    }

}
