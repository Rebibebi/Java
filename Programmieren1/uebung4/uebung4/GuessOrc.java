/* ************************************************************************* *\
*                Programmierung 1 HS 2018 - Serie 4-1                         *
\* ************************************************************************* */

package uebung4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GuessOrc {

	// Store the orc chieftain position.
	private int mineShaftId;

	// Amount of attempts we get to stop the orcs.
	// Use 'MAX_ATTEMPTS'' instead of coding a number
	// directly into your program.
	public final int MAX_ATTEMPTS = 6;

	// Number of mine shafts from which orcs can arrive.
	public final int MINE_SHAFTS = 12;

	// Magic numbers to tell us when something was not set.
	public final int NO_MOVE_MADE = -1;
	public final int NO_HINT = -1;

	// Could not parse the given input.
	public final int PARSING_FAILED = -1;

	// SET DEBUG MODE.
	public final boolean DEBUG = false;

	// That's what the orc horde looks like.
	public final String ORCS =  "  - - - ORC--ARMY - - -  ";
	// If you use WINDOWS (or the above line just looks wrong) change it to this:
	// public final String ORCS =  "  - - - ORC--ARMY - - -  ";

	//Do not modify or access!
	private int[] history = new int[ MAX_ATTEMPTS ];
	private int[] historyHelpers = new int[ MAX_ATTEMPTS ];

	private final String[] HINTS = new String[] {
			"to the left",
			"to the right",
			"under dirt",
			"under stone",
			"very far away"
	};

	// DO NOT CHANGE THIS METHOD!
	public static void main ( String[] args ) {
		GuessOrc game = new GuessOrc();
		game.resetHistory();
		game.start();
	}

	/**
	 * Implement your hint calculation here.
	 *
	 * @param guessedMineShaftId the guessed mine shaft.
	 * @return the id for a hint in HINT.
	 */
	public void calculateHint(int guessedMineShaftId) {
		//guessedMineShaftId = UserInputValid();
		List<Integer> hintList = new ArrayList<>();
		if (mineShaftId < guessedMineShaftId)
			hintList.add(Integer.valueOf(1));
		if (mineShaftId > guessedMineShaftId)
			hintList.add(Integer.valueOf(2));
		if ((movesMade()%2 == 1) && (guessedMineShaftId%2 == 0))
			hintList.add(Integer.valueOf(3));
		if ((movesMade()%2 == 0) && (guessedMineShaftId%2 == 1))
			hintList.add(Integer.valueOf(4));
		if ((mineShaftId-guessedMineShaftId) <= -5 || (mineShaftId-guessedMineShaftId) >=5 )
			hintList.add(Integer.valueOf(5));
		System.out.println("Hintlist: " + hintList);
	}
	

// UserInputValid initiates and repeats getUserInput method as long as userInput is invalid.
// validitycheck by returnvalue from getUserInput. -1 means invalid.
	
	private int UserInputValid() {
		int userInput = -1;
			while (userInput == -1) {
				userInput = getUserInput();
			}
			return userInput;
	}

// getUserInput is initiated by UserInputValid, checks userInput if it is an int and parses if possible.
// returns -1 in case input was invalid.

	private int getUserInput() {
		int userGuessInt;
		System.out.println("Please enter your " + getMovesAsString() + " guess:");
		Scanner scan = new Scanner(System.in);
		boolean guessIsInt = scan.hasNextInt();
		
		if (guessIsInt == true) {
			userGuessInt = scan.nextInt();
			userGuessInt = userGuessInt - 1; // Corrects userinput to ID
			scan.nextLine();
		} else {
			
			char userGuessChar = (scan.nextLine()).charAt(0);
			userGuessInt = getColumnAsInt(userGuessChar);
		}
		
		if (userGuessInt < 0 || userGuessInt >= MINE_SHAFTS) {
				System.out.println("Please enter an integer between 1 and " + MINE_SHAFTS);
				return -1;
		}
		
		return userGuessInt;
	}
				
// getMovesAsString delivers a String to getUserInput depending on the number of moves made.
	
	private String getMovesAsString() {
			switch (movesMade()) {
			case 0:
				return "first";
			case 1:
				return "second";
			case 2:
				return "third";
			case 3:
				return "fourth";
			case 4:
				return "fifth";
			default:
				return "last";
			}
	}

	private void setOrcChieftain() {
		Random randomNumber = new Random();
		mineShaftId  = randomNumber.nextInt(12);
		System.out.println("Orc-Chieftain in Mineshaft " + mineShaftId);
	}
	/**
	 * Implement the game logic here.
	 */
	public void start() {
		System.out.println(generateOrcIntroScreen());
		System.out.println("\n");
		System.out.println(generateMapIntro());
		setOrcChieftain();
		//UserInputValid();
		calculateHint(UserInputValid());	
	}



	// --- Helper methods ---

	/**
	 * Resets the game's history.
	 * 
	 * DO NOT CHANGE THIS METHOD!
	 */
	private void resetHistory() {
		history = new int[ MAX_ATTEMPTS ];
		Arrays.fill( history, NO_MOVE_MADE );
		historyHelpers = new int[ MAX_ATTEMPTS ];
		Arrays.fill( historyHelpers, NO_HINT );
	}

	/**
	 * Calculates the number of moves.
	 * 
	 * @return the number of moves already made.
	 * 
	 * DO NOT CHANGE THIS METHOD!
	 */
	private int movesMade() {
		int currentMove = 0;
		while (currentMove < MAX_ATTEMPTS && history[currentMove] != NO_MOVE_MADE){
			currentMove++;
		}
		return currentMove;
	}

	/**
	 * Prints the map.
	 * If you hand it something else than NO_MOVE_MADE and
	 * NO_HINT it will also update the history.
	 *
	 * @param column a chosen column to flush
	 * @param helpTextId a chosen helptextID (from HINTS) or
	 *
	 * DO NOT CHANGE THIS METHOD!
	 */
	private void printMap( int column, int helpTextId ) {
		// Update history
		// This would not write anything even if we did an update
		if( column != NO_MOVE_MADE ) {
			history[movesMade()] = column;
		}
		if( helpTextId != NO_HINT ) {
			historyHelpers[movesMade() - 1] = helpTextId;
		}

		boolean hasWon = column == mineShaftId;
		int currentMove = movesMade();

		System.out.println();
		if( movesMade() > 0 )
			System.out.println( "You flush mine shaft " + getMineShaftAsChar(column)  );
		if( column == mineShaftId ){ // Did we get the orcs?
			System.out.println();
			System.out.println( "You have stopped the orc chieftain!"  );
			System.out.println();
		} else {
				System.out.println();
				System.out.println();
		}
		// Build and print the header.
		String header = "|";
		for(int col = 0; col < MINE_SHAFTS; col++){
			header += String.valueOf( getMineShaftAsChar( col ) ) + "|";
		}
		System.out.println( header + "\n" + getLine() );

		// Build the map
		int row = MAX_ATTEMPTS;
		while( row > 0 ){
			printRow( column, MAX_ATTEMPTS - row, currentMove);
			row -= 1;
		}
		System.out.println( getLine() );


		if ( !hasWon && movesMade() > 0 && movesMade() < MAX_ATTEMPTS){
			System.out.println(
					"Your Seismometer tells you that the orcs are "
							+ HINTS[ helpTextId ] );
		} else if ( movesMade() == MAX_ATTEMPTS ){
			System.out.println( ORCS ); // They got you.
		}

		// Print history:
		System.out.println( getLine() );
		if( movesMade() > 1 ) {
			System.out.println("History: ");
		}
		for( int historyEntry = 0; historyEntry < MAX_ATTEMPTS; historyEntry++ ) {
			int hintId = historyHelpers[ historyEntry ];
			if( hintId < HINTS.length && hintId >= 0 ) {
				System.out.println( ( historyEntry + 1 ) + ".: " + HINTS[ hintId ] );
			}
		}
	}

	/**
	 * A line we can use to structure the map.
	 *
	 * @ return a String made of "-"
	 */
	private String getLine() {
		// Add a line of (more or less) correct length.
		char[] line = new char[ 2*MINE_SHAFTS + 1 ];
		Arrays.fill( line, '-' );
		return new String( line );
	}

	/**
	 * Prints a row of the map.
	 *
	 * DO NOT CHANGE THIS METHOD UNLESS the special characters don't work on your stystem!
	 * (Document it if that's the case)
	 *
	 * @param column The column which the player chose.
	 * @param row The row we want to print.
	 * @param currentGuess The number of the current guess.
	 */
	private void printRow( int column, int row, int currentGuess) {
		if( row == currentGuess ){
			printOrcRow( row );
		}
		else {
			String output = "|";
			for (int col = 0; col < MINE_SHAFTS; col++) {
				// Did the player check this mine shaft?
				boolean checked = false;
				for (int i : history) {
					if (col == i) { //
						checked = true;
					}
				}
				// Is there stone ?
				boolean isThereStone = false;
				if ((col + row) % 2 == 0) {
					isThereStone = true;
				}
				String emptyField = isThereStone ? "#" : " "; // How we draw empty terrain
				String hitTerrain = isThereStone ? "▓" : "░"; // How we draw terrain we have flushed.

				if (row < currentGuess) { // Are we behind the orcs?
					output += (checked ? hitTerrain : "▒");
				} else {
					// Have we hit the chieftain? Celebrate with special character!
					if (column == mineShaftId && col == column && currentGuess == movesMade()) {
						hitTerrain = "■";
					}
					output += (checked ? hitTerrain : emptyField);
				}
				output += "|";
			}
			System.out.println( output );
		}
	}

	/**
	 * Print the row where the orcs are right now.
	 *
	 * DO NOT CHANGE THIS METHOD UNLESS the special characters don't work on your stystem!
	 *
	 * If DEBUG is set to true, print the exact location instead.
	 * @param row The row where the orcs are supposed to be right now.
	 */
	private void printOrcRow(int row) {
		if (!DEBUG) {
			System.out.println(ORCS);
		} else {
			String output = "|";
			for (int col = 0; col < MINE_SHAFTS; col++) {
				// Did the player check this mine shaft?
				boolean checked = false;
				for (int i : history) {
					if (col == i) { //
						checked = true;
					}
				}
				// Is there stone ?
				boolean isThereStone = false;
				if ((col + row) % 2 == 0) {
					isThereStone = true;
				}
				String emptyField = isThereStone ? "#" : " "; // How we draw empty terrain
				String hitTerrain = isThereStone ? "▓" : "░";

				// Is the chieftain there? Special character
				if (col == mineShaftId) {
					hitTerrain = "■";
				}
				output += hitTerrain;
				output += "|";
			}
			System.out.println(output);
		}
	}

	/**
	 * Determines column's number from its code.
	 * Here 'a' is 1st column and 'l' is 12th
	 * returns 0 on unrecognised codes.
	 *
	 * DO NOT CHANGE THIS METHOD!
	 */
	private int getColumnAsInt( char column ) {
		switch ( column ) {
			case 'a':
				return 0;
			case 'b':
				return 1;
			case 'c':
				return 2;
			case 'd':
				return 3;
			case 'e':
				return 4;
			case 'f':
				return 5;
			case 'g':
				return 6;
			case 'h':
				return 7;
			case 'i':
				return 8;
			case 'j':
				return 9;
			case 'k':
				return 10;
			case 'l':
				return 11;
			default:
				return PARSING_FAILED;
		}
	}

	/**
	 * Determines column's code from its integer
	 * Here 'a' is int 0 and 'l' is 11
	 * returns 'z' on unrecognised integers.
	 *
	 * DO NOT CHANGE THIS METHOD
	 */
	private char getMineShaftAsChar( int mineShaft ) {
		switch ( mineShaft ) {
			case 0:
				return 'a';
			case 1:
				return 'b';
			case 2:
				return 'c';
			case 3:
				return 'd';
			case 4:
				return 'e';
			case 5:
				return 'f';
			case 6:
				return 'g';
			case 7:
				return 'h';
			case 8:
				return 'i';
			case 9:
				return 'j';
			case 10:
				return 'k';
			case 11:
				return 'l';
			default:
				return 'z';
		}
	}

	/**
	 * Use this to print the intro screen.
	 *
	 * DO NOT CHANGE THIS METHOD UNLESS the special characters don't work on your stystem!
	 * (Document it if that's the case)
	 *
	 * @return a String made of orc
	 */
	private String generateOrcIntroScreen() {
		String orc = "                           __,='`````'=/__\n" +
				"                          '//  /-\\ /-\\ \\ `'         _,-,\n" +
				"                          //|     ,_)   (`\\      ,-'`_,-\\\n" +
				"                        ,-~~~\\   /||\\  /-,      \\==```` \\__\n" +
				"                       /        `----'     `\\     \\       \\/\n" +
				"                    ,-`                  ,   \\  ,.-\\       \\\n" +
				"                   /      ,               \\,-`\\`_,-`\\_,..--'\\\n" +
				"                  ,`    ,/,              ,>,   )     \\--`````\\\n" +
				"                  (      `\\`---'`  `-,-'`_,<   \\      \\_,.--'`\n" +
				"                   `.      `--. _,-'`_,-`  |    \\\n" +
				"                    [`-.___   <`_,-'`------(    /\n" +
				"                    (`` _,-\\   \\ --`````````|--`\n" +
				"                     >-`_,-`\\,-` ,          |\n" +
				"                   <`_,'     ,  /\\          /\n" +
				"                    `  \\/\\,-/ `/  \\/`\\_/V\\_/\n" +
				"                       (  ._. )    ( .__. )    **************\n" +
				"                       |      |    |      |    Stop The Orcs!\n" +
				"                        \\,---_|    |_---./    **************\n" +
				"                        ooOO(_)    (_)OOoo" +
				"\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
		return orc;
	}
	
	private String generateMapIntro() {
		String mapImage =
				"|a|b|c|d|e|f|g|h|i|j|k|l|\n" 
				+ getLine() + "\n"
				+ ORCS + "\n" 
				+ "| |#| |#| |#| |#| |#| |#|" + "\n"
				+ "|#| |#| |#| |#| |#| |#| |" + "\n"
				+ "| |#| |#| |#| |#| |#| |#|" + "\n"
				+ "|#| |#| |#| |#| |#| |#| |" + "\n"
				+ "| |#| |#| |#| |#| |#| |#|" + "\n"
				+ "|#| |#| |#| |#| |#| |#| |" + "\n"
				+ getLine() + "\n"
				+ getLine();
		return mapImage;
		
	}
}
