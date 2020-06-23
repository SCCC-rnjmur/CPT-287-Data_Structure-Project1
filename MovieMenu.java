import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import project1.Menu.status;

public class MovieMenu {
	private enum status{
		RECEIVED, RELEASED
	}
	
	public String cmd;
	
	public static void printMenu() {
		System.out.println("What would you like to do?  Choose a number from the menu below.");
		System.out.println("0....Display Menu");
		System.out.println("1....Display All Movies");
		System.out.println("2....Add a new movie");
		System.out.println("3....Edit Movie Details");
		System.out.println("4....Change Start Showing Date");
		System.out.println("5....Count Movies Released Before a Date");
		System.out.println("6....Save Changes");
		System.out.println("7....Exit");
		//System.out.println("8....");
		
	}
	public static void menu(int cmd, Scanner scnr) throws Exception{
		// String title, description, releaseDate;
		String userDate;
		switch(cmd) {
		case 1:
		// displays movies  <=== displays all movies and their info
			//Iterator<Movie>
			//TODO add display movies method
			System.out.println("Displaying all movies Movies");
		break;
		
		case 2:
	/*
	// add movies  <=== adds movies - prompts input per info needed
		// Request Title
			System.out.println("What is the title of the movie you would like to add?");
			title = scnr.nextLine();
		// Request Release Date
			System.out.printf("What is the release date of %s?/nPlease provide date in mm.dd.yyyy format.", title);
		// >> set released / received based on current date
			releaseDate = scnr.nextLine();
		
		// Request Description
			System.out.printf("What is a description of %s?/n", title);
			description = scnr.nextLine();
		// TODO add code to add the movie
		
			System.out.printf("Added %s(%s) releasing on: %s.", title, description, releaseDate);
	*/
			Movie.addMovie();
			
		break;
		
		case 3:
		// edit release dates  <=== ???
			
			System.out.println("Editing release dates");
		break;
		
		case 4:
		// edit movie description  <===  ???
			// list all movies, with numbers 
			System.out.println("Editing movie description");
		break;
		
		case 5:
		// start showing movies in the theater <=== all movies after x date go from coming to showing
			System.out.println("What is the new showing date?");
			//Movie.changeToShowing(newShowingDate)
			userDate = scnr.next();
			Date newShowingDate = new SimpleDateFormat("MM/dd/yyyy").parse(userDate);
			//TODO add code passing new date.  switch from coming soon list to now showing list.
			System.out.println("Movies released after X date: Movie 1... Movie 2");
		break;
		
		case 6:
		// number of movies before a date  <=== counts movies based on release date
			System.out.println("n movies released before x");
		break;
		
		case 7:
		// save  <=== writes to file
			
			//TODO call print to file method
			System.out.println("Your changes have been saved.");
		break;
		
		case 8:
		// exit  <=== closes program
		
		break;
	}
}
	public static void loadMovieFile(ArrayList<Movie> showing, DoubleLinkedList<Movie> coming) {
		int coming_index = 0;
		int showing_index = 0;
		
		// Create file stream to get input file
		FileInputStream inputFile = new FileInputStream("Movies.txt");
		// Create scanner to get data from file
		Scanner inputScanner = new Scanner(inputFile);
				
		// Create output file
		FileOutputStream outputFile = new FileOutputStream("log.txt");
		// Create print writer to output data to output file
		PrintWriter writeOutput = new PrintWriter(outputFile);		
		
		while (inputScanner.hasNext()) {
			Movie addMovie = new Movie();
			addMovie.setMovieTitle(inputScanner.next());
			addMovie.setMovieTitle(inputScanner.next());
			try {
				addMovie.setReleaseDate(new SimpleDateFormat("MM/dd/yyyy").parse(inputScanner.next()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addMovie.setDescription(inputScanner.next());
			try {
				addMovie.setReceivedDate(new SimpleDateFormat("MM/dd/yyyy").parse(inputScanner.next()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (inputScanner.next().equalsIgnoreCase(status.RELEASED.toString())) {
				showing.add(addMovie);
			} else {
				coming.addLast(addMovie);
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scnr = new Scanner(System.in);
		int userSelected;
		
		ArrayList<Movie> showing = new ArrayList<Movie>();
		DoubleLinkedList<Movie> coming = new DoubleLinkedList<Movie>();
		
		System.out.println("Welcome to MovieMaster8000!");
		System.out.println("What would you like to do? -press 0 to view menu.");
		userSelected = scnr.nextInt();
		
		loadMovieFile(showing, coming);
		
		
		while (userSelected > -1 && userSelected < 8) {
			if(userSelected == 0) {
				MovieMenu.printMenu();
				userSelected = scnr.nextInt();
			}
			menu(userSelected, scnr);
		}
	}
}
