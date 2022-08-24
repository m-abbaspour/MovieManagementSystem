/**
 * 
 */
package sait.mms.problemdomain;

/**
 * The Movie class for the table movies in the database
 * 
 * @author Mahdiyeh
 * @version April 17, 2022
 *
 */
public class Movie {

	String title;
	int duration;
	int year;

	/**
	 * Default no-arg constructor or the Movie class
	 */
	public Movie() {
		super();
	}

	/**
	 * Constructor with all of the attributes of the Movie class
	 * 
	 * @param title    title of the Movie
	 * @param duration duration of the movie
	 * @param year     year that the movie was released
	 */
	public Movie(String title, int duration, int year) {
		super();
		this.title = title;
		this.duration = duration;
		this.year = year;
	}

	/**
	 * gets the title of the movie
	 * 
	 * @return title of the movie
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the title of the movie to what the user entered
	 * 
	 * @param title of the movie to be set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the Movie's duration.
	 * 
	 * @return Movie's duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of the movie
	 * 
	 * @param duration duration of the movie
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Gets the Movie's release year.
	 * 
	 * @return Movie's release year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year of the release of the movie
	 * 
	 * @param year year that the movie was released in
	 */
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", duration=" + duration + ", year=" + year + "]";
	}

}
