/**
 * Kirsten Tapalla 
 * ktapalla@brandeis.edu
 * February 26, 2021
 * PA2 - Problem 2
 * Creates a reverse hangman game
 * Bugs/Notes: 
 * - execution of the hangman image is probably coded weirdly
 * - might guess a letter more than once, couldn't figure out how to fix it
 */

import java.util.*;

/**
 * This is the main class that runs the program
 * 
 * @author kirsten
 *
 */
public class Problem2 {
	/**
	 * Initializes class constant of maximum possible wrong guesses and alphabet length
	 * - maxPossErr is 6 because there are 6 possible body parts
	 * 	 (2 arms + 2 legs + 1 head + 1 torso = 6)
	 * - alphabetLen is given as a global variable because it never changes
	 */
	public static final int maxPossErr = 6;
	public static final int alphabetLen = 26;

	/**
	 * Draws entire hangman image
	 */
	public static String drawBase(int errCount) {
		String top = "+--+";
		String sec = "|  |";
		String body = drawBody(errCount);
		String bottom = "+-----";
		String draw = top + '\n' + sec + '\n' + body + "|" + '\n' + bottom;
		return draw;
	}

	/**
	 * Draws parts of hanging man (if any)
	 */
	public static String drawBody(int errCount) {
		String errCountAdj = " 0|\\/\\/";
		String body = "";
		int count = 1;
		if (errCount > 0) {
			body += "|  " + errCountAdj.charAt(count) + '\n';
			count++;
		}
		if (errCount > 1) {
			body += "|  " + errCountAdj.charAt(count) + '\n';
			count++;
		}
		if (errCount > 2) {
			body += "|   " + errCountAdj.charAt(count) + '\n';
			count++;
		}
		if (errCount > 3) {
			String bodySub = body.substring(0, body.length()-4);
			String rLeg = body.substring(body.length()-2);
			body = bodySub + errCountAdj.charAt(count) + " " + rLeg;
			count++;
		}
		if (errCount > 4) {
			String bodyTorso = body.substring(0,9);
			String bodyLegs = body.substring(9);
			body = bodyTorso + errCountAdj.charAt(count) + bodyLegs ;
			count++;
		}
		if (errCount > 5) {
			String bodyBeforeTorso = body.substring(0,7);
			String bodyArmAndLegs = body.substring(8);
			body = bodyBeforeTorso + errCountAdj.charAt(count) + bodyArmAndLegs ;
			count++;
		}
		if (errCount >= 0 && errCount < 3) {
			for (int rInd = errCount+1; rInd < 4; rInd++) {
				body += "|" + '\n';
			}
		}
		return body;
	}

	/**
	 * Hangman game is played
	 */
	public static void runHangman(Scanner console, int wLen, String word, int errCount, int corLetCount, String checkStr) {
		word = word.toUpperCase();
		String hyphen = initialHyphen(wLen, word);
		System.out.println(hyphen);
		String body = drawBase(errCount);
		System.out.println(body);
		char rLetter = rLetter(checkStr);
		guessPar(console, wLen, word, errCount, corLetCount, rLetter, checkStr, hyphen, body);
	}

	/**
	 * Generates new random letter and tries again
	 */
	public static void repeat(Scanner console, int wLen, String word, int errCount, int corLetCount, char rLetter, String checkStr, String hyphen, String body) {
		rLetter = rLetter(checkStr);
		guessPar(console, wLen, word, errCount, corLetCount, rLetter, checkStr, hyphen, body);
	}

	/**
	 * Edits hyphen string to add correct letters
	 */
	public static String editHyphen(int letCount, int wLen, String word, char rLetter, String hyphen) { 
		while(letCount > 0) {
			for(int i = 0; i < wLen; i++) {
				if(word.charAt(i) == rLetter) {
					hyphen = hyphen.substring(0, i) + rLetter + hyphen.substring(i + 1);
					letCount--;
				}
			}
		}
		return hyphen;
	}

	/**
	 * Returns initial blank hyphens
	 */
	public static String initialHyphen(int wLen, String word) {
		String blank = "";
		for (int i = 0; i < wLen; i++) {
			blank += "-";
		}
		return blank;
	}

	/**
	 * Guessing paragraph
	 */ 
	public static void guessPar(Scanner console, int wLen, String word, int errCount, int corLetCount, char rLetter, String checkStr, String hyphen, String body) {
		String guessPar = "I've got " + corLetCount + " of the " + wLen + " letters so far" + '\n' + 
				"I guess: " + rLetter + '\n' + 
				"Is that letter in the word? " ;
		System.out.print(guessPar);

		String yesOrNo = console.next();
		//Computer guessed correctly
		if (yesOrNo.charAt(0) == 'y' || yesOrNo.charAt(0) == 'Y') {
			System.out.print("How many of that letter are in the word? ");

			int letCount = console.nextInt();
			corLetCount += letCount;
			hyphen = editHyphen(letCount, wLen, word, rLetter, hyphen);
			System.out.println(hyphen);
			body = drawBase(errCount);
			System.out.println(body);
			checkStr += rLetter;
			//Can continue guessing
			if (corLetCount != wLen) {
				repeat(console, wLen, word, errCount, corLetCount, rLetter, checkStr, hyphen, body);
			}
			//Game won, guessed all letter is word
			else {
				System.out.println("Game over. I won this time.");
				console.close();
			}
		}
		//Computer guessed wrong
		else if (yesOrNo.charAt(0) == 'n' || yesOrNo.charAt(0) == 'N') {
			checkStr += rLetter;
			errCount++;
			body = drawBase(errCount);
			System.out.println(body);
			//Can continue guessing
			if (errCount != maxPossErr) {
				repeat(console, wLen, word, errCount, corLetCount, rLetter, checkStr, hyphen, body);
			}
			//Game lost, too many wrong guesses
			else {
				System.out.println("Game over. You beat me this time.");
				console.close();
			}
		}

	}

	/**
	 * Generates a random letter using random ASCII val
	 */
	public static char rLetter(String checkStr) {
		int rASCIIval =  rNum();
		char rLetter = (char) rASCIIval;
		checkRep(checkStr, rLetter);
		return rLetter;
	}

	/**
	 * Random ASCII val
	 */
	public static int rNum() {
		Random r = new Random();
		return r.nextInt(alphabetLen) + 65;
	}

	/**
	 * Checks if rLetter is repeated and add it to checkedStr
	 */ 
	public static void checkRep(String checkStr, char rLetter) {
		for (int i = checkStr.length()-1; i >= 0; i--) {
			char c = checkStr.charAt(i);
			//Letter was already guessed before, Generates new letter
			if (c == rLetter) { 
				checkStr += c;
				rLetter(checkStr);
			}
		}

	}

	/**
	 * Main method
	 */
	public static void main(String[] args) {
		//Print game explanation
		String openingMessage = 
				"This program plays a game of reverse hangman." + '\n' + 
				"You think up a word (by typing it on the computer) and I'll try to guess" + '\n' + 
				"the letters." + '\n';
		System.out.println(openingMessage);

		Scanner console = new Scanner(System.in);
		System.out.print("How many letters are in your word? "); 
		int wLen = console.nextInt();
		System.out.print("Please enter the word for me to guess (letters only): ");
		String word = console.next();
		int errCount = 0;
		int corLetCount = 0;
		String checkStr = "";
		runHangman(console, wLen, word, errCount, corLetCount, checkStr);
	}

}
