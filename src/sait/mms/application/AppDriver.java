package sait.mms.application;

import java.sql.SQLException;

import sait.mms.managers.MovieManagementSystem;

/**
 * The driver of the MovieManagementSystem project
 * @author Mahdiyeh
 * @version April 17, 2022
 *
 */
public class AppDriver {

	public static void main(String[] args) throws SQLException {
		
		MovieManagementSystem mms = new MovieManagementSystem();
		mms.displayMenu();
				
	}
}
