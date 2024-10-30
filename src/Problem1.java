/**
 * Kirsten Tapalla 
 * ktapalla@brandeis.edu
 * February 26, 2021
 * PA2 - Problem 1
 * Creates a guessing game where you think up an integer and 
 * the computer keeps guessing until it gets it right
 * Bugs/Notes: 
 * - if user inputs invalid answer, Error message appears and Scanner console will close
 */

import java.util.*;

/**
 * This is the main class that runs the program
 * 
 * @author kirsten
 *
 */
public class Problem1 {
	/**
	 *Creates global variable of min and max 
	 */
	public static final int classMin = 1;
	public static final int classMax = 100;

	/**
	 *Runs new game 
	 */
	public static void runGame(int minNum, int maxNum, int guessCount, int gameCount, int sumGuessCount, int greatestNumGuess) {
		gameCount++;
		System.out.println("Think of a number...");
		Scanner console = new Scanner(System.in);
		guess(minNum, maxNum, console, guessCount, gameCount, sumGuessCount, greatestNumGuess);
	}

	/**
	 *Generates and returns random number guess 
	 */
	public static int randNum(int minNum, int maxNum) {
		Random r = new Random();
		int numDiff = maxNum - minNum + 1;
		int rNum = r.nextInt(numDiff) + minNum;
		return rNum;
	}

	/**
	 *Computer guesses and reacts based on user's response 
	 */
	public static void guess(int minNum, int maxNum, Scanner console, int guessCount, int gameCount, int sumGuessCount, int greatestNumGuess) {
		guessCount++;
		//Generates and initializes random number
		int rNum = randNum(minNum, maxNum);
		String compGuess = "My guess: " + rNum;
		System.out.println(compGuess);
		String input = console.nextLine();
		//Checks whether higher, lower, or correct
		if (input.charAt(0) == 'h' || input.charAt(0) == 'H') {	
			//New guess changing min number
			guessHigher(rNum, maxNum, console, guessCount, gameCount, sumGuessCount, greatestNumGuess);
		}
		else if (input.charAt(0) == 'l' || input.charAt(0) == 'L') {
			//New guess changing max number
			guessLower(minNum, rNum, console, guessCount, gameCount, sumGuessCount, greatestNumGuess);
		}
		else if (input.charAt(0) == 'c' || input.charAt(0) == 'C') {
			System.out.println("I got it right in " + guessCount + " guesses" + '\n');
			//Finds greater guess count at the end of each game
			greatestNumGuess = findGreaterGuess(greatestNumGuess, guessCount);
			//Asks user if they want to play again, resets to class constant min and max
			playAgain(classMin, classMax, console, guessCount, gameCount, sumGuessCount, greatestNumGuess);
		}
		//User response error
		else {
			System.out.println("Error: Valid Response Needed. Game will close. Please restart.");
			console.close();
		}
	}

	/**
	 *Computer changes the minNum and calls on guess() method to guess from a higher range 
	 */
	public static void guessHigher(int minNum, int maxNum, Scanner console, int guessCount, int gameCount, int sumGuessCount, int greatestNumGuess) {
		minNum++;
		guess(minNum, maxNum, console, guessCount, gameCount,sumGuessCount, greatestNumGuess);
	}

	/**
	 *Computer changes the maxNum and calls on guess() method to guess from a lower range 
	 */
	public static void guessLower(int minNum, int maxNum, Scanner console, int guessCount, int gameCount, int sumGuessCount, int greatestNumGuess) {
		maxNum--;
		guess(minNum, maxNum,console, guessCount, gameCount,sumGuessCount, greatestNumGuess);
	}

	/**
	 *Asks if user wants to play again 
	 */
	public static void playAgain(int minNum, int maxNum, Scanner console, int guessCount, int gameCount, int sumGuessCount, int greatestNumGuess) {
		sumGuessCount += guessCount;
		System.out.print("Do you want to play again? ");
		String input = console.next();
		//Runs new game if user wants to play again
		if (input.charAt(0) == 'y' || input.charAt(0) == 'Y') {
			System.out.println();
			//Resets guessCount to 0 for new game
			guessCount = 0;
			runGame(minNum, maxNum, guessCount, gameCount, sumGuessCount, greatestNumGuess);
		}
		//Ends game if user declines 
		else if (input.charAt(0) == 'n' || input.charAt(0) == 'N') {
			System.out.println();
			endGame(console, sumGuessCount, gameCount, greatestNumGuess);
		}
		//User response error
		else {
			System.out.println("Error: Valid Response Needed. Game will close. Please restart.");
			console.close();
		}
	}

	/**
	 *User is done playing, prints statistics 
	 */
	public static void endGame(Scanner console, int sumGuessCount, int gameCount, int greatestNumGuess) {
		double average = (double) sumGuessCount/gameCount;
		System.out.println("Overall results:");
		System.out.printf("%20s%d\n", "total games   = ", gameCount);
		System.out.printf("%20s%d\n", "total guesses = ", sumGuessCount);
		System.out.printf("%20s%.1f\n", "guesses/game  = ", average);
		System.out.printf("%20s%d\n", "max guesses   = ", greatestNumGuess);
		console.close();
	}

	/**
	 *Returns greatest known guess count 
	 */
	public static int findGreaterGuess(int greatestNumGuess, int guessCount) {
		if (guessCount > greatestNumGuess) {
			return guessCount;
		}
		else {
			return greatestNumGuess;
		}
	}

	/**
	 *Main method 
	 */
	public static void main(String[] args) {
		//Print game explanation
		String openingMessage = 
				"This program allows you to play a guessing game." + '\n' + 
				"Think of a number between " + classMin + " and " + classMax + '\n' + 
				"and I will guess until I get it." + '\n' + 
				"For each guess, tell me if the" + '\n' +
				"right answer is higher or lower than your guess, or if it is correct." + '\n';
		System.out.println(openingMessage);
		int guessCount = 0;
		int gameCount = 0;
		int sumGuessCount = 0;
		int greatestNumGuess = 1; //There's always at least one guess
		//Begins running game
		runGame(classMin, classMax, guessCount, gameCount, sumGuessCount, greatestNumGuess);
	}

}
