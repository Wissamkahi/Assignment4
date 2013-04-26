/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	/*Adds the canvas on the screen */
	public void init() {
		canvas = new HangmanCanvas ();
		add(canvas);
	}
	

    public void run() {
    	
    	println ("Welcome to Hangman!");
    	canvas.reset();
    	
    	/* get the word to be guessed */
    	hangmanWord = new HangmanLexicon ();
    	int wordLength = hangmanWord.getWordCount();
		int index = rgen.nextInt (0, wordLength-1);
		String wordToGuess = hangmanWord.getWord(index);
		
		/*Define the userGuess string*/
		println (wordToGuess);
		String userGuess = initializedString (wordToGuess);
		
		/*Display the empty word with dashes on the canvas */
		canvas.displayWord(userGuess);
		
		/* Get the user guess as long as number of guesses is not 0 */
		while (numberOfGuesses>0) {
			println ("The word now looks like this: "+ userGuess);
			println ("You have " + numberOfGuesses + " guesses left.");
			
			/* Ask the user to guess the character */
			char guessedCharacter = getUserGuess();
			
			/* Compare against word to be guessed and fill in the guessed character */
			userGuess = updateGuessedWord (wordToGuess, userGuess, guessedCharacter);
			canvas.displayWord(userGuess);
			
			/* Handling correct and incorrect guesses */
			if (!correctGuess) {
				numberOfGuesses--;
				canvas.noteIncorrectGuess(guessedCharacter);
				println ("There are no "+ guessedCharacter + "'s in the word");
			} else {
				println ("Your guess is correct");
			}
			
			/* Check if the user guessed the entire word */
			if (checkForWin (userGuess)) {
				println ("You won man!");
				break;
			}
		}
		if (!checkForWin (userGuess)) {
			println ("You're completely hung.");
			println ("The word was "+ wordToGuess);
			println ("You lose");
		}
    }
	
    
    /**Initializes the word to guess with dashes
     * 
     * @param word to be guessed
     * @return a string of dashes with the same length
     */
    private String initializedString (String str) {
    	String result = "";
    	for (int i=0; i<str.length(); i++) {
    		result += '-';
    	}
    	return result;
    }
    
	/** 
	 * Reads the guess from the user 
	 * and checks it's legal (i.e. one char only)
	 * returns the guessed letter to the caller
	 * and converts all characters to uppercase
	 */
	private char getUserGuess() {
		String str = readLine ("Your guess: ");
		while (!isLegalGuess(str)) {
			println ("Your guess can only be a character between A and Z");
			str = readLine ("Re-enter your guess: ");
		}
		char guess = str.charAt(0);
		if (Character.isLowerCase(guess)) guess=Character.toUpperCase(guess);
		return guess;
	}
	
	
	/**
	 * Checks that the guessed character is one letter only
	 * @param g is the guessed character
	 * @returns true if guessed character meets the conditions (is one character only and is a letter)
	 */
	private boolean isLegalGuess(String g) {
		if (g.length()>1) return false;
		if ( ((g.charAt(0)<'A') || (g.charAt(0)>'Z')) && ( (g.charAt(0)<'a') || (g.charAt(0)>'z') ) ) return false;
		return true;
	}
	
	
	/**Updates the last user guessed word with the new character and reduces the number of guesses if no match
	 * 
	 * @param target is the hangman word to be guessed
	 * @param str is the last status of the user guesses word
	 * @param guessedChar is the character the user just guessed
	 * @return the updated guessed word with the new character if correct or same as before if no match
	 */
	
	private String updateGuessedWord (String target, String str, char guessedChar) {
		String result = "";
		correctGuess = false;
		for (int i=0; i<str.length(); i++) {
			char ch = target.charAt(i);
			if (ch != guessedChar) {
				result += str.charAt(i);
			}
			else {
				result += ch;
				correctGuess = true;
			}
		}
		return result;
	}	
	
	/** 
	 * Check if the user has guessed the entire word
	 * @param str is the last status of the user guessed word
	 * @return true if word entirely guessed and false otherwise
	 */
	private boolean checkForWin (String str) {
		for (int i=0; i<str.length(); i++) {
			if (str.charAt(i) == '-') return false;
		}
		return true;
	}
    
    /* Constants for the text version of the game */
    private static final int NUMBER_GUESSES = 8;
    
    /* Private instance variables */
    private HangmanLexicon hangmanWord; /* The word to be guessed */
    private HangmanCanvas canvas; /* Declares the instance variable for the canvas */
    private RandomGenerator rgen = RandomGenerator.getInstance (); /*Random generator */
    private int numberOfGuesses = NUMBER_GUESSES; /*Initializes to the number of guesses required */
    private boolean correctGuess; /*Boolean variable to track correct guesses within the program */
}
