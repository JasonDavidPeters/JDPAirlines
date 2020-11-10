package com.jasondavidpeters.JDPAirlines.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import com.jasondavidpeters.JDPAirlines.Main;
import com.jasondavidpeters.JDPAirlines.dbms.Flight;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class FlightScene extends CustomScene {

    private HashMap<String, String> flights;
    private BorderPane bp;

    private double localLat;
    private double localLong;
    
    private Flight localAirport = null;

    private VBox centerGroup, originAirportGroup, flyingToGroup, flyingFromGroup;

    public FlightScene(Main main, Parent layout, double width, double height) {
	super(main, layout, width, height);
	flights = new HashMap<String, String>();

	bp = (BorderPane) layout;

	centerGroup = new VBox(5);
	originAirportGroup = new VBox(5);
	flyingToGroup = new VBox(5);
	flyingFromGroup = new VBox(5);

	centerGroup.getChildren().addAll(originAirportGroup, flyingToGroup, flyingFromGroup);

	bp.setCenter(centerGroup);
	

	URL whatismyip;
	String ip = null;
	try {
	    whatismyip = new URL("http://checkip.amazonaws.com");
	    BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));

	    ip = in.readLine(); // you get the IP as a String

	    InetAddress ipAddress = InetAddress.getByName(ip);
	    File database = new File("res/db/GeoLite2-City.mmdb");
	    DatabaseReader reader = new DatabaseReader.Builder(database).build();

	    try {
		CityResponse response = reader.city(ipAddress);
		Country country = response.getCountry();
		Location loc = response.getLocation();
		localLat = loc.getLatitude();
		localLong = loc.getLongitude();
	    } catch (GeoIp2Exception e) {

		e.printStackTrace();
	    }

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

//	System.out.println("ip:" + ip);

    }

    public void drawFlightInfo() {
	// get local airport
	Label originAirportTitleLabel = new Label("Flying From:");
	originAirportTitleLabel.setFont(new Font(20));
	Label originAirportLabel = new Label(localAirport.toString());

	originAirportGroup.getChildren().addAll(originAirportTitleLabel,originAirportLabel);
	
	Label flyingToTitleLabel = new Label("Flying To");
	flyingToTitleLabel.setFont(new Font(20));
	Label to = new Label();
	
	VBox flightInfo = new VBox(5);

	Label from = new Label();

	if (flights.size() > 1) { // Round Trip
	    to.setText(flights.get("to"));
	    from.setText(flights.get("from"));
	    flightInfo.getChildren().addAll(to, from);
	} else if (flights.size() <= 1) { // One-way
	    to.setText(flights.get("to"));
	    flightInfo.getChildren().add(to);
	}
	centerGroup.getChildren().add(flightInfo);
	
	
    }

    public void setFlights(String to) { // One-way
	flights.put("to", to);
    }

    public void setFlights(String from, String to) { // Round-trip
	flights.put("from", from);
	flights.put("to", to);

    }

    public void findOriginAirport(ArrayList<Flight> flights) {
	Flight closestAirport = null;
//	System.out.println("local lat:  "  + localLat + " localLong: " + localLong);
	for (Flight f : flights) {
	    if (closestAirport == null) {
		closestAirport = f;
		continue;
	    }
//	    System.out.println(f.getAirportCode() + "  " +Math.abs(f.getLat() - Math.abs(localLat)) + " " + Math.abs(closestAirport.getLat() - Math.abs(localLat)));
//	    System.out.println(f.getAirportCode() + "  " +Math.abs(f.getLong() - Math.abs(localLong)) + " " + Math.abs(closestAirport.getLong() - Math.abs(localLong)));
	    if (Math.abs(f.getLat() - Math.abs(localLat)) <= Math.abs(closestAirport.getLat() - Math.abs(localLat))
		    && Math.abs(f.getLong() - Math.abs(localLong)) <= Math.abs(closestAirport.getLong() - Math.abs(localLong))) {
		closestAirport = f;

	    }
	    // if longitude is not null
	    // locallong localla

	}
//	System.out.println(closestAirport);
	localAirport=closestAirport;
    }

}
