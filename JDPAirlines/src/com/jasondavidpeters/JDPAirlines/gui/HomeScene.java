package com.jasondavidpeters.JDPAirlines.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HomeScene extends Scene {
    
    private ImageView image;
    private TextField searchBox;
    private TextField roundTripSearchBox;
    private Font font;
    private VBox navigationBar;

    public HomeScene(Parent layout, double width, double height) {
	super(layout, width, height);
	//window - stage
	//contents - scene

	font = new Font(null, 0.05*width);
	ToggleGroup radioBtns = new ToggleGroup();
	
	RadioButton oneWay = new RadioButton("One Way");
	RadioButton roundTrip = new RadioButton("Round Trip");
	
	/*
	 * TODO Once you click round trip radio button
	 * create textfield below previous textfield for return flights
	 */
	
	oneWay.setToggleGroup(radioBtns);
	roundTrip.setToggleGroup(radioBtns);
	
	Image img = new Image("/logo.png");
	
	searchBox = new TextField("Search...");
	roundTripSearchBox = new TextField("Round Trip...");
	
	image = new ImageView(img);
	image.setPreserveRatio(true);
	Button searchButton = new Button("O");
	Button invisBtn = new Button("O");
	
	invisBtn.setVisible(false);
	
	
	navigationBar = new VBox();
	VBox centerGroup = new VBox(5); // Vertical grouping
	HBox searchBar = new HBox(); // Horizontal grouping of search bar and search button
	HBox roundTripBar = new HBox();
	HBox flightOptions = new HBox(5);
	
	Hyperlink homeLink = new Hyperlink("Home");
	homeLink.setFont(font);
	
	Hyperlink myAccount = new Hyperlink("My Account");
	myAccount.setFont(font);
	
	Hyperlink myFlights = new Hyperlink("My Flights");
	myFlights.setFont(font);
	
	/*
	 * TODO: minimize the gap between the navigation bar
	 * and logo
	 */
	
	
	navigationBar.getChildren().add(homeLink);
	navigationBar.getChildren().add(new Separator());
	navigationBar.getChildren().add(myAccount);
	navigationBar.getChildren().add(new Separator());
	navigationBar.getChildren().add(myFlights);
	navigationBar.getChildren().add(new Separator());

	searchBox.setPrefWidth(width);
	roundTripSearchBox.setPrefWidth(width);
	
	searchBar.getChildren().add(searchBox); // Add [----] the box to the bar
	searchBar.getChildren().add(searchButton); //Add the search button to the bar
	roundTripBar.getChildren().add(roundTripSearchBox);
	roundTripBar.getChildren().add(invisBtn);
	
	flightOptions.getChildren().add(oneWay);
	flightOptions.getChildren().add(roundTrip);
	centerGroup.getChildren().add(image); // Add the logo to the logo bar group
	
	centerGroup.getChildren().add(flightOptions);
	centerGroup.getChildren().add(searchBar); // Add the search bar group to the logo bar group
	
	searchBar.setAlignment(Pos.CENTER);
	roundTripBar.setAlignment(Pos.CENTER);
	
	centerGroup.setAlignment(Pos.CENTER);
	flightOptions.setAlignment(Pos.CENTER);
	
	BorderPane.setAlignment(centerGroup, Pos.CENTER);
	((BorderPane) layout).setCenter(centerGroup);
	((BorderPane)layout).setLeft(navigationBar); // Put the navigation bar to the left of the application
	
	centerGroup.setMaxWidth(width);
	centerGroup.setPadding(new Insets(0, 0, 0, -width/4)); // top, right, bottom, left
	
//	navigationBar.setBorder(new Border(new BorderStroke(Color.BLACK, 
//	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//	centerGroup.setBorder(new Border(new BorderStroke(Color.BLACK, 
//	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	
	roundTrip.selectedProperty().addListener(e -> {
	    if (roundTrip.isSelected()) {
		centerGroup.getChildren().add(roundTripBar);
	    }
	    else if (!roundTrip.isSelected()) {
		centerGroup.getChildren().remove(roundTripBar);
	    }
	    
	});
	
	
    }
    
    public ImageView getImage() {
	return this.image;
    }
    public TextField getSearchBox() {
	return this.searchBox;
    }
    public TextField getRoundTripsBox() {
	return this.roundTripSearchBox;
    }
    public Font getFont() {
	return this.font;
    }
    public void setFont(Font font) {
	this.font=font;
    }
    public VBox getNavbar() { 
	return this.navigationBar;
    }

}
