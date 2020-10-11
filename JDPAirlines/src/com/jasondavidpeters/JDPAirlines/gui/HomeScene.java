package com.jasondavidpeters.JDPAirlines.gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jasondavidpeters.JDPAirlines.Main;
import com.jasondavidpeters.JDPAirlines.gui.function.AutoSuggest;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HomeScene extends CustomScene {

    private ImageView image;
    private TextField searchBox;
    private TextField roundTripSearchBox;
    private VBox centerGroup;

    private StackPane roundTripBoxPane, searchBoxPane;

    private final int MAX_SUGGESTIONS = 2;

    private boolean loaded;

    private ArrayList<String> suggestions;

    private AutoSuggest autoSuggest;

    public HomeScene(Main main, Parent layout, double width, double height) {
	super(main, layout, width, height);
	suggestions = new ArrayList<String>();
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
	searchBoxPane = new StackPane();
	roundTripBoxPane = new StackPane();
	searchBox = new TextField();
	autoSuggest = new AutoSuggest(suggestions, width, 75, 50);
	searchBoxPane.getChildren().addAll(searchBox, autoSuggest);
	autoSuggest.setOpacity(.7);
	autoSuggest.setFocusTraversable(false);
	autoSuggest.setVisible(false);

	searchBox.setPromptText("Search..."); // greyed out promp text
	searchBox.focusedProperty().addListener((ov, oldV, newV) -> {
	    if (!oldV) { // focused
		for (Object o : searchBoxPane.getChildren()) {
		    if (o instanceof AutoSuggest) {
			((AutoSuggest) o).setOnMouseClicked(e -> {
			    // String text = e.
			    if (e.getTarget() instanceof Text) {
				searchBox.setText(((Text) e.getTarget()).getText());
				((AutoSuggest) o).setVisible(false);
			    }
			});
			return;
		    }
		}
	    }
	});

	searchBox.setOnKeyReleased(e -> {
	    if (searchBox.getText().isBlank()) {
		autoSuggest.setVisible(false);
	    }
	    for (int i = 0; i < suggestions.size(); i++) {
		autoSuggest.setItems(FXCollections
			.observableList(autoSuggest.findMatch(searchBox.getText(), suggestions, MAX_SUGGESTIONS)));

	    }
	    autoSuggest.setVisible(true);
	    /*
	     * findMatch(searchBox.getText()); Match letters from here to suggestions
	     * arraylist set combobox visible with suggestions that match
	     */
	});

	roundTripSearchBox = new TextField("");
	roundTripSearchBox.focusedProperty().addListener((ov, oldV, newV) -> {
	    if (!oldV) { // focused
		for (Object o : roundTripBoxPane.getChildren()) {
		    if (o instanceof AutoSuggest) {
			((AutoSuggest) o).setOnMouseClicked(e -> {
			    // String text = e.
			    if (e.getTarget() instanceof Text) {
				roundTripSearchBox.setText(((Text) e.getTarget()).getText());
				((AutoSuggest) o).setVisible(false);
			    }
			});
			return;
		    }
		}
		if (roundTripSearchBox.getText().isBlank()) {
		    for (Object o : roundTripBoxPane.getChildren()) {
			if (o instanceof AutoSuggest) {
			    ((AutoSuggest) o).setVisible(false);
			}
		    }
		}
	    }
	});
	roundTripSearchBox.setOnKeyReleased(e -> {
	    if (roundTripSearchBox.getText().isBlank()) {
		for (Object o : roundTripBoxPane.getChildren()) {
		    if (o instanceof AutoSuggest) {
			((AutoSuggest) o).setVisible(false);
		    }
		}
	    } else {
		for (Object o : roundTripBoxPane.getChildren()) {
		    if (o instanceof AutoSuggest) {
			((AutoSuggest) o).setVisible(true);
		    }
		}
	    }

	    for (Object o : roundTripBoxPane.getChildren()) {
		if (o instanceof AutoSuggest) {
		    ((AutoSuggest) o).setItems(FXCollections.observableList(
			    ((AutoSuggest) o).findMatch(roundTripSearchBox.getText(), suggestions, MAX_SUGGESTIONS)));

		}

	    }
	    /*
	     * findMatch(searchBox.getText()); Match letters from here to suggestions
	     * arraylist set combobox visible with suggestions that match
	     */
	});

	/*
	 * suggested searches
	 */

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
	searchBar.getChildren().addAll(searchBoxPane, searchButton); // Add the search button to the bar

	roundTripBoxPane.getChildren().add(roundTripSearchBox);

	roundTripBar.getChildren().addAll(roundTripBoxPane, invisBtn);

	flightOptions.getChildren().addAll(oneWay, roundTrip);

	// centerGroup.getChildren().add(image); // Add the logo to the logo bar group

	roundTripBar.setAlignment(Pos.CENTER);
//	
	roundTripBar.setVisible(false);
	centerGroup.getChildren().addAll(image, flightOptions, searchBar, roundTripBar);

	roundTrip.selectedProperty().addListener(e -> {
	    if (roundTrip.isSelected()) {
		roundTripBar.setVisible(true);
		roundTripBoxPane.getChildren().add(new AutoSuggest(suggestions, width, 75, 50));
		for (Object o : roundTripBoxPane.getChildren()) {
		    if (o instanceof AutoSuggest) {
			((AutoSuggest) o).setVisible(false);
			return;
		    }
		}
	    } else if (!roundTrip.isSelected()) {
		for (Object o : roundTripBoxPane.getChildren()) {
		    if (o instanceof AutoSuggest) {
			roundTripBoxPane.getChildren().remove(o);
			roundTripBar.setVisible(false);
			return;
		    }
		}
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

	loadData(main.getConnector().getConnection());

    }

    private void loadData(Connection c) {
	if (!loaded) {
	    /*
	     * Load data from database once.
	     */

	    String airportCode;
	    String airportName;
	    String state;
	    String city;

	    Statement st = null;

	    String query = "SELECT Code, City, State, Airport_Name FROM Flights";

	    try {
		st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
		    airportCode = rs.getString("Code");
		    airportName = rs.getString("Airport_Name");
		    state = rs.getString("State");
		    city = rs.getString("City");
		    suggestions.add(airportName + ", " + city + ", " + state + " (" + airportCode + ")");

		}
		c.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
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
