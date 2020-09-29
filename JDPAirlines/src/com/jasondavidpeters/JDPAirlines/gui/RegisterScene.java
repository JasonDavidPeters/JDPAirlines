package com.jasondavidpeters.JDPAirlines.gui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jasondavidpeters.JDPAirlines.Main;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RegisterScene extends CustomScene {

    public RegisterScene(Main main, Parent layout, double width, double height) {
	super(main, layout, width, height);

	BorderPane bp = (BorderPane) layout;
	VBox centerGroup = new VBox(10);
	/*
	 * First name Last name e-mail retype email phone number zip code username
	 * password retype password
	 */
	Label label_title = new Label("Registration");
	Label label_error = new Label();
	Label label_fName = new Label("First name");
	Label label_lName = new Label("Last name");
	Label label_email = new Label("E-mail");
	Label label_reEmail = new Label("Retype e-mail");
	Label label_phone = new Label("Phone");
	Label label_residence = new Label("Residence");
	Label label_zipcode = new Label("Zip code");
	Label label_username = new Label("Username");
	Label label_password = new Label("Password");

	TextField textField_firstName = new TextField();
	TextField textField_lastName = new TextField();
	TextField textField_email = new TextField();
	TextField textField_reEmail = new TextField();
	TextField textField_phone = new TextField();
	ComboBox<?> comboBox_residence = new ComboBox<Object>(); // need drop down menu somehow
	TextField textField_zipcode = new TextField();
	TextField textField_username = new TextField();
	PasswordField passwordField_password = new PasswordField();
	Hyperlink registerLink = new Hyperlink("Register");
	Hyperlink goBack = new Hyperlink("Go Back");

	HBox titleBox = new HBox();
	HBox errorBox = new HBox();
	HBox firstLine = new HBox(5);
	HBox secondLine = new HBox(5);
	HBox thirdLine = new HBox(5);
	HBox fourthLine = new HBox(5);
	HBox fifthLine = new HBox(5);
	HBox sixthLine = new HBox(5);

	titleBox.getChildren().add(label_title);
	errorBox.getChildren().add(label_error);

	firstLine.getChildren().addAll(label_fName, textField_firstName, label_lName, textField_lastName);

	secondLine.getChildren().addAll(label_email, textField_email, label_reEmail, textField_reEmail);

	thirdLine.getChildren().addAll(label_phone, textField_phone, label_residence, comboBox_residence);

	fourthLine.getChildren().addAll(label_zipcode, textField_zipcode);

	fifthLine.getChildren().addAll(label_username, textField_username, label_password, passwordField_password);

	sixthLine.getChildren().addAll(registerLink, goBack);

	centerGroup.getChildren().addAll(titleBox, errorBox, firstLine, secondLine, thirdLine, fourthLine, fifthLine,
		sixthLine);
	centerGroup.setAlignment(Pos.CENTER);
	bp.setCenter(centerGroup);

	label_title.setFont(new Font(null, 25));
	label_error.setFont(new Font(null, 12));
	label_error.setTextFill(Color.RED);

	label_error.setVisible(false);

	registerLink.setOnAction(e -> {
	    // validation
	    Pattern namePattern = Pattern.compile("[^A-Za-z]"); // match words and no digits
	    Matcher nameMatcher = namePattern.matcher(textField_firstName.getText());
	    if (nameMatcher.find() || textField_firstName.getText().isBlank()) {
		label_error.setText("First name must contain alphabetic characters only");
		label_error.setVisible(true);
		return;
	    }
	    nameMatcher = namePattern.matcher(textField_lastName.getText());
	    if (nameMatcher.find()|| textField_lastName.getText().isBlank()) {
		label_error.setText("Last name must contain alphabetic characters only");
		label_error.setVisible(true);
		return;
	    }
	    
	    Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	    Matcher emailMatcher = emailPattern.matcher(textField_email.getText());
	    if (!(emailMatcher.find())) {
		label_error.setText("Incorrect e-mail address format entered");
		label_error.setVisible(true);
		return;
	    }
	    if (!(textField_reEmail.getText().equals(textField_email.getText()))){
		label_error.setText("Email address do not match");
		label_error.setVisible(true);
		return;
	    }
	    
	    /*
	     * phone number regex
	     */
	    /*
	     * username and password
	     */
	    Pattern usernameValidation = Pattern.compile("^[A-Za-z]{3,16}$");
	    Pattern passwordValidation = Pattern.compile("([A-Za-z0-9]{5,16})([?.,;'!@#$%^&*()re+])");
	    String username = textField_username.getText();
	    String password = passwordField_password.getText();
	    Matcher userMatcher = usernameValidation.matcher(username);
	    Matcher passwordMatcher = passwordValidation.matcher(password);
	    if (!(userMatcher.find())) {
		label_error.setText("Username must be 3-16 characters long and contain no special characters.");
		label_error.setVisible(true);
		return;
	    }
	    if (!(passwordMatcher.find())) {
		label_error.setText("Password must be 5-16 characters long and contain at least 1 special character.");
		label_error.setVisible(true);
		return;
	    }
	    label_error.setVisible(false);

	});

    }
}
