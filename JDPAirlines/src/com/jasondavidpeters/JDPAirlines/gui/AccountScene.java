package com.jasondavidpeters.JDPAirlines.gui;

import com.jasondavidpeters.JDPAirlines.Main;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AccountScene extends CustomScene {

    private TextField usernameField, passwordField;

    public AccountScene(Main main, Parent layout, double width, double height) {
	super(main, layout, width, height);
//	((Region) layout).setBorder(new Border(
//		new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(30))));
	BorderPane bp = (BorderPane) layout;
	VBox centerGroup = new VBox(5);
	HBox usernameBox = new HBox(5);
	HBox passwordBox = new HBox(5);
	
	Label title = new Label("Login Form");
	title.setFont(new Font(null, 50));

	Label userLabel = new Label("Username");
	Label passLabel = new Label("Password");

	usernameField = new TextField();
	passwordField = new PasswordField();
	
	Hyperlink loginBtn = new Hyperlink("Login");
	Hyperlink registerBtn = new Hyperlink("Register");
	
	HBox links = new HBox(5);
	
	links.getChildren().addAll(loginBtn,registerBtn);

	usernameField.setPromptText("Username");
	passwordField.setPromptText("Password");
	
	usernameBox.setPrefWidth(width);
	passwordBox.setPrefWidth(width);
	
	usernameBox.getChildren().addAll(userLabel,usernameField);
	passwordBox.getChildren().addAll(passLabel,passwordField);
	centerGroup.getChildren().addAll(title,usernameBox,passwordBox,links);
	
	links.setAlignment(Pos.CENTER);
	usernameBox.setAlignment(Pos.CENTER);
	passwordBox.setAlignment(Pos.CENTER);
	centerGroup.setAlignment(Pos.CENTER);
	
	bp.setCenter(centerGroup);

	getMyAccountLink().setOnAction(e -> {
	    main.getStage().setScene(main.getAccountScene());

	});
	getHomeLink().setOnAction(e -> {
	    main.resizeNodes(main.getHomeScene());
	    main.getStage().setScene(main.getHomeScene());
	    System.out.println(main.getHomeScene().getCenterGroup().getWidth());
//	    System.out.println(main.getHomeScene().getCenterGroup().getParent());
//	    System.out.println( main.getHomeScene().getCenterGroup().getWidth());
	});
    }

    public TextField getUsernameBox() {
	return usernameField;
    }

    public TextField getPasswordBox() {
	return passwordField;
    }

}
