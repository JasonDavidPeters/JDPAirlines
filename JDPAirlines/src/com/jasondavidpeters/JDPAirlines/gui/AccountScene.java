package com.jasondavidpeters.JDPAirlines.gui;

import com.jasondavidpeters.JDPAirlines.Main;

import javafx.scene.Parent;

public class AccountScene extends CustomScene {
    
    public AccountScene(Main main,Parent layout, double width, double height) {
	super(main, layout, width, height);
	
	getMyAccountLink().setOnAction(e -> {
	    main.getStage().setScene(this);
	});
	getHomeLink().setOnAction(e -> {
	    main.resizeNodes(main.getHomeScene());
	    main.getStage().setScene(main.getHomeScene());
	});
    }

}
