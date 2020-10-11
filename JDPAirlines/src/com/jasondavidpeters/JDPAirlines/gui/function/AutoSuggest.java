package com.jasondavidpeters.JDPAirlines.gui.function;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

public class AutoSuggest extends ListView {

    private ArrayList<String> suggestions;
    private String text;
    private double width,height;

    public AutoSuggest(ArrayList<String> suggestions, String text, int suggestionsAmt) {
	this.text=text;
	this.suggestions=suggestions;
	setItems(FXCollections.observableList(findMatch(text, suggestions, suggestionsAmt)));
    }

    public AutoSuggest(ArrayList<String> suggestions,double width, double height,double yOffset) {
	this.suggestions = suggestions;
	this.width=width;
	this.height=height;
	setMaxSize(width,height);
	setTranslateY(yOffset);
    }

    /*
     * Method that finds a match between a string and strings in an arraylist
     */
    public ArrayList<String> findMatch(String string1, ArrayList<String> string2, final int MAX_SUGGESTIONS) {
	ArrayList<String> words = new ArrayList<String>();
	for (int i = 0; i < string2.size(); i++) {
	    // loop through words
	    if (string2.get(i).toLowerCase().contains(string1.toLowerCase())) {
		if (words.size() < MAX_SUGGESTIONS)
		    words.add(string2.get(i));
//		System.out.println(string1 + " " + string2.get(i));
	    }
	}
	return words;
    }

    public void fill() {

    }

}
