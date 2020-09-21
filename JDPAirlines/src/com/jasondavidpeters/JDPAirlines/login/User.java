package com.jasondavidpeters.JDPAirlines.login;

public class User {
    
    private String username, password, email;
    private int phoneNumber;
    private double latitude, longitude;
    
    public User(String username, String password, String email, int phoneNumber, double latitude, double longitude) {
	this.username=username;
	this.password=password;
	this.email=email;
	this.phoneNumber=phoneNumber;
	this.latitude=latitude;
	this.longitude=longitude;
    }

}
