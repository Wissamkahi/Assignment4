/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {
	
	public HangmanLexicon() {
		
		String word = null;
		try {
			BufferedReader rd = new BufferedReader (new FileReader("HangmanLexicon.txt"));
			while ( (word = rd.readLine()) != null ) {
				wordlist.add(word);
			}
			rd.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordlist.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordlist.get(index);
		}

	
	/* Private instance variables */
	ArrayList <String> wordlist = new ArrayList <String>();
}
