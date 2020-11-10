package com.jasondavidpeters.JDPAirlines.gui.function;

import java.util.ArrayList;

import com.jasondavidpeters.JDPAirlines.dbms.Flight;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

public class AutoSuggest extends ListView {

    private ArrayList<Flight> suggestions;
    private String text;
    private double width,height;

    public AutoSuggest(ArrayList<Flight> suggestions, String text, int suggestionsAmt) {
	this.text=text;
	this.suggestions=suggestions;
	setItems(FXCollections.observableList(findMatch(text, suggestions, suggestionsAmt)));
    }

    public AutoSuggest(ArrayList<Flight> suggestions,double width, double height,double yOffset) {
	this.suggestions = suggestions;
	this.width=width;
	this.height=height;
	setMaxSize(width,height);
	setTranslateY(yOffset);
    }

    /*
     * Method that finds a match between a string and strings in an arraylist
     */
    public ArrayList<String> findMatch(String string1, ArrayList<Flight> flightList, final int MAX_SUGGESTIONS) {
	ArrayList<String> words = new ArrayList<String>();
	for (int i = 0; i < flightList.size(); i++) {
	    // loop through words
	    if (flightList.get(i).toString().toLowerCase().contains(string1.toLowerCase())) {
		if (words.size() < MAX_SUGGESTIONS)
		    words.add(flightList.get(i).toString());
//		System.out.println(string1 + " " + string2.get(i));
	    }
	}
	return words;
    }

    public void fill() {

    }

}
