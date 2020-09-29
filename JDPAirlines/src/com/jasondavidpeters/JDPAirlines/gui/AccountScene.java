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
	    boolean userCorrect,passCorrect;
	    /*
	     * Validation check do both fields contain information? do both fields meet
	     * criteria (username length, special characters etc...) username must be 3-16
	     * characters, password must be 5-20 characters and contain 1 capital letter, 1
	     * special character does this username match our records? if not then send
	     * error message
	     */
	    Pattern usernameValidation = Pattern.compile("^[A-Za-z]{3,16}$");
	    Pattern passwordValidation = Pattern.compile("([A-Za-z0-9]{5,16})([?.,;'!@#$%^&*()re+])");
	    String username = usernameField.getText();
	    String password = passwordField.getText();
	    Matcher userMatcher = usernameValidation.matcher(username);

	    if (userMatcher.find()) {
		userCorrect=true;
		errorMessage.setVisible(false);
	    } else {
		userCorrect=false;
		errorMessage.setText("Username must be 3-16 characters long and contain no special characters.");
		errorMessage.setVisible(true);
		return;

	    }
	    Matcher passmatcher = passwordValidation.matcher(password);
	    if (passmatcher.find()) {
		passCorrect=true;
		errorMessage.setVisible(false);
	    } else {
		passCorrect=false;
		errorMessage.setText("Password must be 5-16 characters long and contain at least 1 special character.");
		errorMessage.setVisible(true);
		return;

	    }
	    if (userCorrect && passCorrect) { 
		// initiate login sequence
	    }

	});

	registerBtn.setOnAction(e -> {
	    // Load new scene for registration
	    /*
	     * First name
	     * Last name
	     * e-mail
	     * phone number
	     * zip code
	     * username
	     * password
	     */
	    main.resizeNodes(main.getRegisterScene());
	    main.getStage().setScene(main.getRegisterScene());
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

    }

    public TextField getUsernameBox() {
	return usernameField;
    }

    public TextField getPasswordBox() {
	return passwordField;
    }

}
