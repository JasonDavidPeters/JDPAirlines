package com.jasondavidpeters.JDPAirlines.gui;

import com.jasondavidpeters.JDPAirlines.Main;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScene extends CustomScene {

    private ImageView image;
    private TextField searchBox;
    private TextField roundTripSearchBox;
    private VBox centerGroup;

    public HomeScene(Main main, Parent layout, double width, double height) {
	super(main, layout, width, height);
	// window - stage
	// contents - scene

	BorderPane bp = (BorderPane) layout;
	ToggleGroup radioBtns = new ToggleGroup();

	RadioButton oneWay = new RadioButton("One Way");
	RadioButton roundTrip = new RadioButton("Round Trip");

	oneWay.setToggleGroup(radioBtns);
	roundTrip.setToggleGroup(radioBtns);

	Image img = new Image("/logo.png");
//
	searchBox = new TextField();
	searchBox.setPromptText("Search..."); // greyed out promp text
	roundTripSearchBox = new TextField("");

	Button searchButton = new Button("O");
	Button invisBtn = new Button("O");
//
	invisBtn.setVisible(false);

	centerGroup = new VBox(); // Vertical grouping

	HBox searchBar = new HBox(); // Horizontal grouping of search bar and search button
	HBox roundTripBar = new HBox();
	HBox flightOptions = new HBox(5);
	image = new ImageView(img);
	image.setPreserveRatio(true);
	/*
	 * TODO: minimize the gap between the navigation bar and logo
	 */
//
//	searchBox.setPrefWidth(width);
//	roundTripSearchBox.setPrefWidth(width);
	searchBar.getChildren().addAll(searchBox, searchButton); // Add the search button to the bar
	
	roundTripBar.getChildren().addAll(roundTripSearchBox, invisBtn);

	flightOptions.getChildren().addAll(oneWay, roundTrip);

	// centerGroup.getChildren().add(image); // Add the logo to the logo bar group

	roundTripBar.setAlignment(Pos.CENTER);
//	
	roundTripBar.setVisible(false);
	centerGroup.getChildren().addAll(image,flightOptions, searchBar,roundTripBar);
	
	roundTrip.selectedProperty().addListener(e -> {
	    if (roundTrip.isSelected()) {
		roundTripBar.setVisible(true);
	    } else if (!roundTrip.isSelected()) {
		roundTripBar.setVisible(false);
	    }

	});
	searchBar.setAlignment(Pos.CENTER);
	flightOptions.setAlignment(Pos.CENTER);
	centerGroup.setAlignment(Pos.CENTER);
//	flightOptions.setAlignment(Pos.CENTER);
//	

	// centerGroup.setPrefSize(main.getScreenWidth(), main.getScreenHeight());
	// BorderPane.setAlignment(centerGroup, Pos.CENTER);
	// centerGroup.setPadding(new Insets(100,100,100,100));

	// bp.setMaxSize(main.getScreenWidth()/2, main.getScreenHeight()/2);

	// BorderPane.setAlignment(centerGroup, Pos.CENTER);
	// bp.setPadding(new Insets(50,50,50,50));
	bp.setCenter(centerGroup);
	
	// centerGroup.setAlignment(Pos.CENTER);

	// Put the navigation bar to the left of the application

//	centerGroup.setMaxWidth(width);
//	centerGroup.setPadding(new Insets(0, 0, 0, -width / 4)); // top, right, bottom, left

//	getNavbar().setBorder(new Border(
//		new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//	centerGroup.setBorder(new Border(
//		new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(30))));
//	bp.setBorder(new Border(
//		new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(30))))

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

    public VBox getCenterGroup() {
	return centerGroup;
    }
}
