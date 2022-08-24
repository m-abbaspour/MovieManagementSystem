package sait.mms.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import sait.mms.contracts.DatabaseDriver;
import sait.mms.drivers.MariaDBDriver;
import sait.mms.problemdomain.Movie;

/**
 * The Management System for the Movie class
 * @author Mahdiyeh
 * @version April 17, 2022
 *
 */
public class MovieManagementSystem {

	private DatabaseDriver db;
	private Scanner keyboardScanner;

	/**
	 * Default constructor for the MovieManagementSystem class
	 * @throws SQLException
	 */
	public MovieManagementSystem() throws SQLException {
		this.db = new MariaDBDriver();
		this.db.connect();
		this.keyboardScanner = new Scanner(System.in);
	}

	/**
	 * Displays the menu
	 * @throws SQLException
	 */
	public void displayMenu() throws SQLException {
		while (true) {
			System.out.print("\nJim's Movie Manager\r\n" + "1	Add New Movie\r\n"
					+ "2	Print movies released in year\r\n" + "3	Print random list of movies\r\n"
					+ "4	Delete a movie\r\n" + "5	Exit\r\n" + "\r\n" + "Enter an option: ");
			String option = "";
			while(option.equals(""))
				option = keyboardScanner.nextLine(); // Changed from nextLine(
			switch (option) {
			case "1":
				addMovie();
				break;
			case "2":
				printMoviesInYear();
				break;
			case "3":
				printRandomMovies();
				break;
			case "4":
				deleteMovie();
				break;
			case "5":
				System.out.println("Goodbye!");
				db.disconnect();
				keyboardScanner.close();
				return;
			default:
				System.out.println("Unexpected value: " + option);
			}
		}
	}

	/**
	 * Deletes a movie based on the movie's id
	 */
	private void deleteMovie() {
		String id;
		try {
		do{
			System.out.print("\nEnter the movie ID thata you want to delete: ");
			id = keyboardScanner.next();			
		} while(!(id.length()>0 && id.matches("\\d+")));
		String query = "DELETE FROM movies WHERE id = " + id + ";";
		int rows = db.update(query);
		if (rows>0)
		   System.out.println("Movie " + id + " is deleted.");
		else
			   System.out.println("Movie with id:" + id + " not found.");
		} catch (SQLException e) {
			System.out.println("Could not delete the movie: ");
		}
		catch (Exception e) {
		       System.out.println("Wrong Input");
		}
	}

	/**
	 * Prints a random list (the number of movies is specified by the user) of movies
	 */
	private void printRandomMovies() {
		String num;
		int totalDuration = 0;
		try {
		do{
			System.out.print("\nEnter number of movies: ");
			num = keyboardScanner.next();			
		} while(!(num.length()>0 && num.matches("\\d+")));
		String query = "SELECT duration AS \"Duration\", YEAR AS \"Year\", title AS \"Title\" "
				+ " FROM movies "
				+ " ORDER BY RAND() "
				+ " LIMIT " + num + ";";
		ResultSet rs = db.get(query);
		System.out.println("\nMovie List\nDuration          Year     Title");

		while(rs.next()) {
			Movie m = new Movie(rs.getString(3), rs.getInt(1), rs.getInt(2));
			totalDuration += m.getDuration();
			System.out.printf("%3d               %4d     %s\n", m.getDuration(), m.getYear(), m.getTitle());
		}
		System.out.println("\nTotal duration: " + totalDuration + " minutes\n");
		} catch (Exception e) {
			System.out.println("Wrong input");
		}
	}

	/**
	 * Prints the movies that are specified
	 */
	private void printMoviesInYear() {
		String year;
		int totalDuration = 0;
		try {
		do{
			System.out.print("Enter in year: ");
			year = keyboardScanner.next();
			
		} while(!(year.length()==4 && year.compareTo("1900")>=0 && year.compareTo("2022")<=0));
		
		
		String query = "SELECT duration AS \"Duration\", YEAR AS \"Year\", title AS \"Title\" \r\n"
				+ "FROM movies\r\n"
				+ "WHERE YEAR = " + year +" ORDER BY title;";
		ResultSet rs = db.get(query);
		System.out.println("\nMovie List\nDuration          Year     Title");

		while(rs.next()) {
			Movie m = new Movie(rs.getString(3), rs.getInt(1), rs.getInt(2));
			totalDuration += m.getDuration();
			System.out.printf("%3d               %4d     %s\n", m.getDuration(), m.getYear(), m.getTitle());
		}
		System.out.println("\nTotal duration: " + totalDuration + " minutes\n");
		} catch (Exception e) {
			System.out.println("Wrong input");
		}
	}

	/**
	 * Adds a movie to the database
	 */
	private void addMovie() {
		String title = "";
		try {
			do {
				System.out.print("\nEnter movie title: ");
				title = keyboardScanner.nextLine(); 				
			} while(!(title.length()>0));
			String duration;
			do {
				System.out.print("Enter duration: ");
				duration = keyboardScanner.next(); 				
			} while(!(duration.length()>0 && duration.matches("\\d+")));

		String year;
		do{
			System.out.print("Enter in year: ");
			year = keyboardScanner.next();
			
		} while(!(year.length()==4 && year.compareTo("1900")>=0 && year.compareTo("2022")<=0));
		String query = "INSERT INTO movies (duration, title,year) VALUES(" + duration + ", '" + title + "', " + year+ ");";
		int rows = db.update(query);
		System.out.println(rows + " row(s) were inserted.");
		} catch (SQLException e) {
			System.out.println("Could not add the movie: " + title);
		}
		catch (Exception e) {
		       System.out.println("Wrong Input");
		}
	}

}
