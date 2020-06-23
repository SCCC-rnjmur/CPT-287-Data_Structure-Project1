package project1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

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
	public static void menu(int cmd, Scanner scnr, ArrayList<Movie> coming, DoubleLinkedList<Movie> showing) throws Exception{
		String userDate;
		switch(cmd) {
		case 1:
			ListIterator comingIter = coming.listIterator();
			while(comingIter.hasNext()) {
				System.out.println(comingIter.next());
			}
		// displays movies  <=== displays all movies and their info
			//Iterator<Movie>
			//TODO add display movies method
			System.out.println("Displaying all movies Movies");
		break;
		
		case 2:
			addMovie(scnr);
			
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
	public static void loadMovieFile(ArrayList<Movie> showing, DoubleLinkedList<Movie> coming) throws IOException {
		int coming_index = 0;
		int showing_index = 0;
		
		// Create file stream to get input file
		FileInputStream inputFile = new FileInputStream("Movies.txt");
		// Create scanner to get data from file
		Scanner inputScanner = new Scanner(inputFile);	
		
		while (inputScanner.hasNext()) {
			String[] movieFromFile = inputScanner.nextLine().split(",");
			
			Movie addMovie = new Movie();
			addMovie.setMovieTitle(movieFromFile[0]);
			try {
				addMovie.setReleaseDate(new SimpleDateFormat("MM/dd/yyyy").parse(movieFromFile[1]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addMovie.setDescription(movieFromFile[2]);
			try {
				addMovie.setReceivedDate(new SimpleDateFormat("MM/dd/yyyy").parse(movieFromFile[3]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (movieFromFile[4].equalsIgnoreCase(status.RELEASED.toString())) {
				showing.add(addMovie);
			} else {
				coming.addLast(addMovie);
			}
		}
		
		inputScanner.close();
		inputFile.close();
		
	}
	
	
	public static void addMovie(Scanner scnr) throws ParseException{
		String movieTitle, description;
		Date releaseDate, receivedDate;
		String recDate, dateRel = null;	
		
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
	}

	
	public static void writeMovieFile(ArrayList<Movie> showing, DoubleLinkedList<Movie> coming) throws IOException {
		// Create output file
		FileOutputStream outputFile = new FileOutputStream("Movie.txt");
		// Create print writer to output data to output file
		PrintWriter writeOutput = new PrintWriter(outputFile);
		
		Iterator show_it = showing.iterator();
		ListIterator com_it = coming.iterator();
		
		while (show_it.hasNext()) {
			writeOutput.print(show_it.next().toString() + "released");
		}
		while (com_it.hasNext()) {
			writeOutput.print(com_it.next().toString() + "received");
		}
		
		writeOutput.close();
		outputFile.close();
		
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scnr = new Scanner(System.in);
		int userSelected;
		
		ArrayList<Movie> showing = new ArrayList<Movie>();
		DoubleLinkedList<Movie> coming = new DoubleLinkedList<Movie>();
		
		loadMovieFile(showing, coming);
		
		System.out.println("Welcome to MovieMaster8000!");
		System.out.println("What would you like to do? -press 0 to view menu.");
		userSelected = scnr.nextInt();
		
		
		
		
		while (userSelected > -1 && userSelected < 8) {
			if(userSelected == 0) {
				MovieMenu.printMenu();
				userSelected = scnr.nextInt();
			}
			menu(userSelected, scnr, showing, coming);
		}
	}
}

