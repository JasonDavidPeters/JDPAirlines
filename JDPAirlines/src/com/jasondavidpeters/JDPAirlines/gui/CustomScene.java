package com.jasondavidpeters.JDPAirlines.gui;

import com.jasondavidpeters.JDPAirlines.Main;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CustomScene extends Scene {

    private VBox navigationBar;

    private Hyperlink myAccount;
    private Hyperlink homeLink;

    private Font font;

    private Parent layout;

    public CustomScene(Main main, Parent layout, double width, double height) {
	super(layout, width, height);
	this.layout=layout;
	
	navigationBar = new VBox();

	font = new Font(null, 50 );

	homeLink = new Hyperlink("Home");
	homeLink.setFont(font);

	myAccount = new Hyperlink("My Account");
	myAccount.setFont(font);

	Hyperlink myFlights = new Hyperlink("My Flights");
	myFlights.setFont(font);

	navigationBar.getChildren().add(homeLink);
	navigationBar.getChildren().add(new Separator());
	navigationBar.getChildren().add(myAccount);
	navigationBar.getChildren().add(new Separator());
	navigationBar.getChildren().add(myFlights);
	navigationBar.getChildren().add(new Separator());

	((BorderPane) layout).setLeft(navigationBar);
	
	
    }

    public VBox getNavbar() {
	return navigationBar;
    }

    public Font getFont() {
	return font;
    }
    public void setFont(Font font) {
	this.font = font;
    }

    public Hyperlink getMyAccountLink() {
	return myAccount;
    }
    public Hyperlink getHomeLink() {
	return homeLink;
    }
    public Parent getLayout() {
	return this.layout;
    }

}
