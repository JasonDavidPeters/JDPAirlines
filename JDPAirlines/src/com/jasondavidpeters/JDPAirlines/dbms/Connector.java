package com.jasondavidpeters.JDPAirlines.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    
    private Connection conn;

    public void connect() {
	conn = null;

	try {
	    String url = "jdbc:sqlite:res/db/JDPAirlinesDB.db";
	    conn = DriverManager.getConnection(url);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    public Connection getConnection() {
	return conn;
    }

}
