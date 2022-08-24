package sait.mms.contracts;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Interface for connecting the database to the program
 * COntains the signatures of the Connect, get(query), update, 
 * and disconnect methods
 * @author Mahdiyeh
 * @version April 17, 2022
 */
public interface DatabaseDriver {
	
	/**
	 * Connects the Java program to the relative database
	 * @throws SQLException when the program could not connect to the database
	 */
	void connect() throws SQLException;
	
	/**
	 * Disconnects the program from the relational database
	 * @throws SQLException when the program cannot disconnect from the database
	 */
	void disconnect() throws SQLException;
	
	/**
	 * Gets the query and passes it to the database
	 * @param query query that is set to execute
	 * @return result sets from the database
	 * @throws SQLException if there was an error processing the request
	 */
	ResultSet get(String query) throws SQLException;
	
	/**
	 * 
	 * @param query query that is set to update the database
	 * @return the number of rows that were effected
	 * @throws SQLException if there was an error
	 */
	int update(String query) throws SQLException;
}
