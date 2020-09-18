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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HomeScene extends CustomScene {

    private ImageView image;
    private TextField searchBox;
    private TextField roundTripSearchBox;

    public HomeScene(Main main, Parent layout, double width, double height) {
	super(main, layout, width, height);
	// window - stage
	// contents - scene

	ToggleGroup radioBtns = new ToggleGroup();

	RadioButton oneWay = new RadioButton("One Way");
	RadioButton roundTrip = new RadioButton("Round Trip");

	oneWay.setToggleGroup(radioBtns);
	roundTrip.setToggleGroup(radioBtns);

	Image img = new Image("/logo.png");

	searchBox = new TextField();
	searchBox.setPromptText("Search..."); // greyed out promp text
	roundTripSearchBox = new TextField("");

	image = new ImageView(img);
	image.setPreserveRatio(true);
	Button searchButton = new Button("O");
	Button invisBtn = new Button("O");

	invisBtn.setVisible(false);

	getHomeLink().setOnAction(e -> {
	    main.getStage().setScene(this);
	});
	getMyAccountLink().setOnAction(e -> {
	    main.setScreenWidth(main.getStage().getWidth());
	    main.setScreenHeight(main.getStage().getHeight());
	    main.resizeNodes(main.getAccountScene());
	    main.getStage().setScene(main.getAccountScene());
	});

	VBox centerGroup = new VBox(5); // Vertical grouping
	HBox searchBar = new HBox(); // Horizontal grouping of search bar and search button
	HBox roundTripBar = new HBox();
	HBox flightOptions = new HBox(5);

	/*
	 * TODO: minimize the gap between the navigation bar and logo
	 */

	searchBox.setPrefWidth(width);
	roundTripSearchBox.setPrefWidth(width);

	searchBar.getChildren().add(searchBox); // Add [----] the box to the bar
	searchBar.getChildren().add(searchButton); // Add the search button to the bar
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

	//BorderPane.setAlignment(centerGroup, Pos.CENTER);
	((BorderPane) layout).setCenter(centerGroup);
	// Put the navigation bar to the left of the application

//	centerGroup.setMaxWidth(width);
//	centerGroup.setPadding(new Insets(0, 0, 0, -width / 4)); // top, right, bottom, left

	getNavbar().setBorder(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	centerGroup.setBorder(new Border(new BorderStroke(Color.RED, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

	roundTrip.selectedProperty().addListener(e -> {
	    if (roundTrip.isSelected()) {
		centerGroup.getChildren().add(roundTripBar);
	    } else if (!roundTrip.isSelected()) {
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

}
