import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

public class MovieMenu {
	private enum status{
		RECEIVED, RELEASED
	}
	
	public String cmd;
	
	public static void printMenu() {
		System.out.println("What would you like to do?  Choose a number from the menu below.");
		System.out.println("0....Display Menu");  // <--- DONE
		System.out.println("1....Display All Movies"); // <--- DONE
		System.out.println("2....Add a new movie"); // <--- DONE
		System.out.println("3....Edit Movie Details"); 
		System.out.println("4....Change Start Showing Date");
		System.out.println("5....Count Movies Released Before a Date");
		System.out.println("6....Save Changes"); // <--- DONE
		System.out.println("7....Exit\n"); // <--- DONE
	}
	public static String menu(int cmd, Scanner scnr, ArrayList<Movie> showing, DoubleLinkedList<Movie> coming) throws Exception{
		String userDate;
		switch(cmd) {
		case 1:
			System.out.println("SHOWING NOW");
			ListIterator showingIter = showing.listIterator();
			while(showingIter.hasNext()) {
				System.out.println(showingIter.next().toString());
			}
			System.out.println("COMING SOON - To a theater near YOU!");
			ListIterator comingIter = coming.iterator();
			while(comingIter.hasNext()) {
				System.out.println(comingIter.next().toString());
			}
		// displays movies  <=== displays all movies and their info
			
			//TODO add display movies method
			System.out.println("Displaying all movies.");
		break;
		
		case 2:
			addMovie(showing, coming);
		break;
		
		case 3:
			// edit movie description
			// list all movies
			comingIter = coming.iterator();
			int comingCount = 0;
			System.out.println("What movie would you like to edit?");
			while(comingIter.hasNext()) {
				comingCount ++;
				System.out.printf("%d...." + comingIter.next().toString());
			}
			comingIter = coming.iterator();
			Scanner inputScanner = new Scanner(System.in);
			int userChoice = inputScanner.nextInt();
			
			if (userChoice > comingCount) {
				System.out.println("That was an invalid option.\n");
				break;
			}
			
			for (int x = 1; x <= userChoice; x++) {
				comingIter.next();
			}
			System.out.println("Enter new description below.\nIf no changes are needed, leave blank.");
			String newDescription = inputScanner.nextLine();
			System.out.println("Enter new release date below.\nIf no changes are needed, leave blank.");
			Date newReleaseDate = new SimpleDateFormat("MM/dd/yyyy").parse(inputScanner.nextLine());
			
			if (newDescription.compareTo("") != 0) {
				// add set code for description
			} 
			if (newReleaseDate.toString().compareTo("") != 0) {
				// add set code for date
			}
			
			//TODO add set code for release date
			
		break;
		
		case 4:
		// start showing movies in the theater <=== all movies after x date go from coming to showing
			System.out.println("What is the new showing date?");
			//Movie.changeToShowing(newShowingDate)
			userDate = scnr.next();
			Date newShowingDate = new SimpleDateFormat("MM/dd/yyyy").parse(userDate);
			//TODO add code passing new date.  switch from coming soon list to now showing list.
			System.out.println("Movies released after X date: Movie 1... Movie 2");
		break;
		
		case 5:
		// number of movies before a date  <=== counts movies based on release date
			System.out.println("n movies released before x");
		break;
		
		case 6:
		// save  <=== writes to file
			writeMovieFile(showing, coming);
			System.out.println("Your changes have been saved.");
		break;
		
		case 7:
		// exit  <=== closes program
			System.out.println("Good-bye!");
			return "quit";
	}
	return "";	
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
			//System.out.println(status.RECEIVED.toString());
			//System.out.println(movieFromFile[4].trim());
			if (movieFromFile[4].trim().equalsIgnoreCase(status.RELEASED.toString())) {
				
				showing.add(addMovie);
			} else {
				coming.addLast(addMovie);
			}
		}
		
		inputScanner.close();
		inputFile.close();
		
	}
	
	public static void addMovie(ArrayList<Movie> showing, DoubleLinkedList<Movie> coming) throws ParseException{
		Scanner inputScanner = new Scanner(System.in);
		String movieTitle = "", description = "";
		Date releaseDate, receivedDate;
		String recDate, dateRel = null;	
		int category;
		
		
		System.out.println("Enter movie title:");
		
		
		movieTitle = inputScanner.nextLine();
		
		
		System.out.println("Enter release date in Format MM/dd/yyyy:");
		dateRel = inputScanner.nextLine();
		
		
		releaseDate = new SimpleDateFormat("MM/dd/yyyy").parse(dateRel);
		
		System.out.println("Enter description:");
		
		description =  inputScanner.nextLine();
		
		System.out.println("Enter recieved date");
		System.out.println("Format: MM/dd/yyyy");
		
		recDate = inputScanner.nextLine();
		
		receivedDate = new SimpleDateFormat("MM/dd/yyyy").parse(recDate);
		
		System.out.println("What category is the movie?\n1....Showing\n2....Coming");
		category = inputScanner.nextInt();
		if (category == 1) {
			showing.add(new Movie(movieTitle, releaseDate, description, receivedDate));
		} else {
			coming.addFirst(new Movie(movieTitle, releaseDate, description, receivedDate));
		}
		
		
		
	}
	public static void writeMovieFile(ArrayList<Movie> showing, DoubleLinkedList<Movie> coming) throws IOException {
		// Create output file
		FileOutputStream outputFile = new FileOutputStream("Movie.txt");
		// Create print writer to output data to output file
		PrintWriter writeOutput = new PrintWriter(outputFile);
		
		java.util.Iterator<Movie> show_it = showing.iterator();
		ListIterator com_it = coming.iterator();
		
		while (show_it.hasNext()) {
			writeOutput.print(show_it.next().toString() + "released\n");
		}
		while (com_it.hasNext()) {
			writeOutput.print(com_it.next().toString() + "received\n");
		}
		
		writeOutput.close();
		outputFile.close();
		
	}
	public static void main(String[] args) throws Exception {
		Scanner inputScanner = new Scanner(System.in);
		String userQuit = "";
		int userSelected;
		
		ArrayList<Movie> showing = new ArrayList<Movie>();
		DoubleLinkedList<Movie> coming = new DoubleLinkedList<Movie>();
		
		loadMovieFile(showing, coming);
		
		System.out.println("Welcome to MovieMaster8000!");
		System.out.println("What would you like to do? -press 0 to view menu.");
		userSelected = inputScanner.nextInt();
		
		while (userSelected > -1 && userSelected < 8 && userQuit.compareTo("quit") < 0) {
			if(userSelected == 0) {
				MovieMenu.printMenu();
				userSelected = inputScanner.nextInt();
			} else {
				System.out.println("What else would you like to do?");
				userSelected = inputScanner.nextInt();
			}
			userQuit = menu(userSelected, inputScanner, showing, coming);
		}
		inputScanner.close();
	}
}
