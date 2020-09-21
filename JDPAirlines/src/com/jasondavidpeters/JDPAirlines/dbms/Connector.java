package com.jasondavidpeters.JDPAirlines.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    public static void connect() {
	Connection conn = null;

	try {
	    String url = "jdbc:sqlite:res/db/JDPAirlinesDB.db";
	    conn = DriverManager.getConnection(url);
	    System.out.println("Connected to database");
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
		conn.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

}
