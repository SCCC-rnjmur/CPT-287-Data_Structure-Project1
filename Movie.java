package project1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Movie {
	//Data fields
	private String movieTitle;
	private Date releaseDate;
	private String dateRel;
	private String description;
	private Date receivedDate;
	private String recDate;
	private String movieStatus;
	
	//scanner
	Scanner scnr = new Scanner (System.in);
	
	//Constructors
	//Default constructor
	public Movie() {};

	
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
	public void addMovie() throws Exception {
		System.out.println("Enter movie title:");
		movieTitle = scnr.next();
		System.out.println("Enter release date:");
		System.out.println("Format: MM/dd/yyyy");
		releaseDate = new SimpleDateFormat("MM/dd/yyyy").parse(dateRel);
		System.out.println("Enter description:");
		description = scnr.next();
		System.out.println("Enter recieved date");
		System.out.println("Format: MM/dd/yyyy");
		recDate = scnr.next();
		receivedDate = new SimpleDateFormat("MM/dd/yyyy").parse(recDate);
		
		
		
		//Check if release date is before or equal to received date
		if (releaseDate.compareTo(receivedDate) == 0) {
			System.out.print("Invalid release date.");
		}
		else if (releaseDate.compareTo(receivedDate) < 0) {
			System.out.print("Invalid release date.");
		}
		
	}
	
}
