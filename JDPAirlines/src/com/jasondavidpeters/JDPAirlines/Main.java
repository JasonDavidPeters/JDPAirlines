package com.jasondavidpeters.JDPAirlines;

import com.jasondavidpeters.JDPAirlines.dbms.Connector;
import com.jasondavidpeters.JDPAirlines.gui.AccountScene;
import com.jasondavidpeters.JDPAirlines.gui.CustomScene;
import com.jasondavidpeters.JDPAirlines.gui.FlightScene;
import com.jasondavidpeters.JDPAirlines.gui.HomeScene;
import com.jasondavidpeters.JDPAirlines.gui.RegisterScene;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private double screenWidth;
    private double screenHeight;
    private static double MAX_HEIGHT = 1200;
    private static double MIN_HEIGHT = 600;
    private static double MAX_WIDTH = 1800; // get users max resolution width
    private static double MIN_WIDTH = 800;
    public static final String APPLICATION_NAME = "JDP Airlines";
    private double posX, posY;
    private AccountScene accountScene;
    private HomeScene homeScene;
    private RegisterScene registerScene;
    private FlightScene flightScene;
    private Stage stage;
    private Connector connector;

    public static void main(String[] args) {
	launch(args);
    }

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws Exception {
	this.stage = stage;

	stage.setWidth(MIN_WIDTH);
	stage.setHeight(MIN_HEIGHT);
	screenWidth = stage.getWidth();
	screenHeight = stage.getHeight();
	stage.setTitle(APPLICATION_NAME);
	connector = new Connector();
	connector.connect();

	homeScene = new HomeScene(this, new BorderPane(), screenWidth, screenHeight);
	accountScene = new AccountScene(this, new BorderPane(), screenWidth, screenHeight);
	registerScene = new RegisterScene(this, new BorderPane(), screenWidth, screenHeight);
	flightScene = new FlightScene(this, new BorderPane(), screenWidth, screenHeight);
	
	resizeNodes(homeScene);
	resizeNodes(accountScene);
	resizeNodes(flightScene);

	stage.setScene(homeScene);

	stage.setResizable(false);
//	stage.minWidthProperty().bind(stage.getScene().heightProperty().);
//	stage.minHeightProperty().bind(stage.getScene().widthProperty());
	stage.sizeToScene();

	stage.widthProperty().addListener(e -> {
	    // System.out.println("width being changed");
	    if (stage.getWidth() >= MAX_WIDTH) {
		screenWidth = MAX_WIDTH;
		stage.setWidth(screenWidth);
		resizeNodes(stage.getScene());
		return;
	    }
	    if (stage.getWidth() <= MIN_WIDTH) {
		screenWidth = MIN_WIDTH;
		// stage.centerOnScreen();
		stage.setWidth(screenWidth);
		resizeNodes(stage.getScene());
		return;
	    }
	    screenWidth = stage.getWidth();
	    System.out.println(screenWidth);
	    stage.setWidth(stage.widthProperty().get());
	    resizeNodes(stage.getScene());

	});
	stage.heightProperty().addListener(e -> {
	    System.out.println("height being changed");
	    if (stage.getHeight() >= MAX_HEIGHT) {
		screenHeight = MAX_HEIGHT;
		stage.setHeight(screenHeight);
		resizeNodes(stage.getScene());
		return;
	    }
	    if (stage.getHeight() <= MIN_HEIGHT) {
		screenHeight = MIN_HEIGHT;
		// stage.centerOnScreen();
		stage.setHeight(screenHeight);
		resizeNodes(stage.getScene());
		return;
	    }
	    screenHeight = stage.getHeight();
	    resizeNodes(stage.getScene());
	});
	stage.show();
    }

    public void resizeNodes(Scene scene) {
	if (scene instanceof HomeScene) {
	    ((HomeScene) scene).getImage().setFitWidth((screenWidth / 2));
	    ((HomeScene) scene).getImage().setFitHeight((screenHeight / 2));
	    ((HomeScene) scene).getSearchBox().setPrefWidth((screenWidth / 2));
	    ((HomeScene) scene).getRoundTripsBox().setPrefWidth((screenWidth / 2));
	}
	if (scene instanceof AccountScene) {
	    ((AccountScene) scene).getUsernameBox().setPrefWidth((screenWidth / 2));
	    ((AccountScene) scene).getPasswordBox().setPrefWidth((screenWidth / 2));
	}
	if (scene instanceof RegisterScene) {
	}
	if (scene instanceof CustomScene) {

	    ((CustomScene) scene).setFont(new Font(((CustomScene) scene).getFont().getName(), 0.03 * screenWidth));
	    ObservableList<Node> f = ((CustomScene) scene).getNavbar().getChildren();
	    for (Node n : f) {
		if (n instanceof Hyperlink) {
		    Hyperlink newLink = (Hyperlink) n;
		    newLink.setFont(((CustomScene) scene).getFont());
		    n = newLink;
		}
	    }
	}

    }

    public AccountScene getAccountScene() {
	return accountScene;
    }
    public RegisterScene getRegisterScene() {
	return registerScene;
    }

    public HomeScene getHomeScene() {
	return homeScene;
    }

    public Stage getStage() {
	return this.stage;
    }
    
    public CustomScene getFlightScene() {
	return flightScene;
    }

    public void setScreenWidth(double screenWidth) {
	this.screenWidth = screenWidth;
    }

    public void setScreenHeight(double screenHeight) {
	this.screenHeight = screenHeight;
    }

    public double getScreenWidth() {
	return screenWidth;
    }

    public double getScreenHeight() {
	return screenHeight;
    }
    public Connector getConnector() {
	return connector;
    }
}
