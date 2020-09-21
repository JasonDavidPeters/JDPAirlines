package com.jasondavidpeters.JDPAirlines.gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javafx.scene.paint.Color;
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
	Label errorMessage = new Label();
	errorMessage.setTextFill(Color.RED);
	errorMessage.setVisible(false);

	title.setFont(new Font(null, 50));
	errorMessage.setFont(new Font(null, 12));

	Label userLabel = new Label("Username");
	Label passLabel = new Label("Password");

	usernameField = new TextField();
	passwordField = new PasswordField();

	Hyperlink loginBtn = new Hyperlink("Login");
	Hyperlink registerBtn = new Hyperlink("Register");

	loginBtn.setOnAction(e -> {

	    /*
	     * Validation check do both fields contain information? do both fields meet
	     * criteria (username length, special characters etc...) username must be 3-16
	     * characters, password must be 5-20 characters and contain 1 capital letter, 1
	     * special character does this username match our records? if not then send
	     * error message
	     */
	    Pattern usernameValidation = Pattern.compile("^[A-Za-z]{3,16}$");
	    Pattern passwordValidation = Pattern.compile("([A-Za-z]{5,20})([!@#$%^&*()re+])");
	    String username = usernameField.getText();
	    String password = passwordField.getText();
	    Matcher userMatcher = usernameValidation.matcher(username);

	    if (userMatcher.find()) {

		errorMessage.setVisible(false);
	    } else {
		errorMessage.setText("Username must be 3-16 characters long and contain no special characters.");
		errorMessage.setVisible(true);

	    }
	    Matcher passmatcher = passwordValidation.matcher(password);
	    if (passmatcher.find()) {

		errorMessage.setVisible(false);
	    } else {
		errorMessage.setText("Password must be 5-16 characters long and contain at least 1 special character.");
		errorMessage.setVisible(true);

	    }
//	    if (usernameField.getText().length() < 3) {
//		errorMessage.setText("Username must be longer than 2 characters!");
//		errorMessage.setVisible(true);
//	    } else if (usernameField.getText().length() > 16) {
//		errorMessage.setText("Username cannot be longer than 16 characters");
//		errorMessage.setVisible(true);
//	    } else if (usernameField.getText().contains(s) ) { 
//		
//	    }

	});

	registerBtn.setOnAction(e -> {

	});

	HBox links = new HBox(5);

	links.getChildren().addAll(loginBtn, registerBtn);

	usernameField.setPromptText("Username");
	passwordField.setPromptText("Password");

	usernameBox.setPrefWidth(width);
	passwordBox.setPrefWidth(width);

	usernameBox.getChildren().addAll(userLabel, usernameField);
	passwordBox.getChildren().addAll(passLabel, passwordField);
	centerGroup.getChildren().addAll(title, errorMessage, usernameBox, passwordBox, links);

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
