package project1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Movie {
	//Data fields
	private String movieTitle;
	private Date releaseDate;
	private String description;
	private Date receivedDate;

	
	//Arraylist and scanner
	ArrayList<String> Movie = new ArrayList<String>();
	Scanner scnr = new Scanner (System.in);
	
	//Constructors
	//Default constructor
	public Movie() {}
	
	public Movie(String movieTitle, Date releaseDate, String description, Date receivedDate) {
		this.movieTitle = movieTitle;
		this.releaseDate = releaseDate;
		this.description = description;
		this.receivedDate = receivedDate;
	}
	
	//Setters and getters
	public String getMovieTitle() {return this.movieTitle;}
	public void setMovieTitle(String movieTitle) {this.movieTitle = movieTitle;}
	public Date getReleaseDate() {return this.releaseDate;}
	public void setReleaseDate(Date releaseDate) {this.releaseDate = releaseDate;}
	public String getDescription() {return this.description;}
	public void setDescription(String description) {this.description = description;}
	public Date getReceivedDate() {return this.receivedDate;}
	public void setReceivedDate(Date receivedDate) {this.receivedDate = receivedDate;}
	
	//Methods
	@Override
	public String toString() {
		DateFormat print_dates = new SimpleDateFormat("MM/dd/yyyy");
		return this.movieTitle + ", " + print_dates.format(this.releaseDate) + ", " +
				this.description + ", " + print_dates.format(this.receivedDate) + ", ";
	}
}
