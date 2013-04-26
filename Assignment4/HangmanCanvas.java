/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {


/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		add (scaffold);
		add (beam);
		add (rope);
		add (g_user_word);
		add (g_user_guesses);
		userGuesses="";
		incorrectGuessCounter = 0;
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		g_user_word.setLabel(word);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		userGuesses += letter;
		g_user_guesses.setLabel(userGuesses);
		incorrectGuessCounter++;
		switch (incorrectGuessCounter) {
			case 1: add(head);
			break;
			case 2: add (body);
			break;
			case 3: {
				add (r_arm);
				add (r_hand);
				break;
			}
			case 4: {
				add(l_arm);
				add (l_hand);
				break;
			}
			case 5: add (r_hip);
			break;
			case 6: add (l_hip);
			break;
			case 7: {
				add (r_leg);
				add (r_foot);
				break;
			}
			case 8: {
				add(l_leg);
				add (l_foot);
				break;
			}
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	/* private instance variables */
	private int center_x = 400;
	private int center_y = 400;
	private GLine beam = new GLine (center_x-BEAM_LENGTH,center_y-SCAFFOLD_HEIGHT, center_x, center_y-SCAFFOLD_HEIGHT);		
	private GLine scaffold = new GLine (center_x-BEAM_LENGTH,center_y-SCAFFOLD_HEIGHT,center_x-BEAM_LENGTH,center_y);
	private GLine rope = new GLine (center_x, center_y-SCAFFOLD_HEIGHT,center_x, center_y-SCAFFOLD_HEIGHT+ROPE_LENGTH);
	private GOval head = new GOval (center_x - HEAD_RADIUS, center_y-SCAFFOLD_HEIGHT+ROPE_LENGTH, HEAD_RADIUS*2, HEAD_RADIUS*2);
	private GLine body = new GLine (center_x, center_y-SCAFFOLD_HEIGHT+HEAD_RADIUS*2+ROPE_LENGTH,center_x, center_y-SCAFFOLD_HEIGHT+HEAD_RADIUS*2+ROPE_LENGTH+BODY_LENGTH);
	private GLine r_arm = new GLine (center_x, center_y-SCAFFOLD_HEIGHT+HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD,center_x+UPPER_ARM_LENGTH,center_y-SCAFFOLD_HEIGHT+HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD);
	private GLine l_arm = new GLine (center_x, center_y-SCAFFOLD_HEIGHT+HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD,center_x-UPPER_ARM_LENGTH,center_y-SCAFFOLD_HEIGHT+HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD);
	private GLine r_hand = new GLine (center_x+UPPER_ARM_LENGTH,center_y-SCAFFOLD_HEIGHT+HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD,center_x+UPPER_ARM_LENGTH,center_y-SCAFFOLD_HEIGHT+HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
	private GLine l_hand = new GLine (center_x-UPPER_ARM_LENGTH,center_y-SCAFFOLD_HEIGHT+HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD, center_x-UPPER_ARM_LENGTH,center_y-SCAFFOLD_HEIGHT+HEAD_RADIUS*2+ROPE_LENGTH+ARM_OFFSET_FROM_HEAD+LOWER_ARM_LENGTH);
	private GLine r_hip = new GLine (center_x, center_y-LEG_LENGTH, center_x+HIP_WIDTH, center_y-LEG_LENGTH);
	private GLine l_hip = new GLine (center_x, center_y-LEG_LENGTH, center_x-HIP_WIDTH, center_y-LEG_LENGTH);
	private GLine r_leg = new GLine (center_x+HIP_WIDTH, center_y-LEG_LENGTH, center_x+HIP_WIDTH, center_y);
	private GLine l_leg = new GLine (center_x-HIP_WIDTH, center_y-LEG_LENGTH, center_x-HIP_WIDTH, center_y);
	private GLine r_foot = new GLine (center_x+HIP_WIDTH, center_y, center_x+HIP_WIDTH+FOOT_LENGTH, center_y);
	private GLine l_foot = new GLine (center_x-HIP_WIDTH, center_y, center_x-HIP_WIDTH-FOOT_LENGTH, center_y);
	private GLabel g_user_word = new GLabel("",center_x-BEAM_LENGTH,center_y+80);
	private GLabel g_user_guesses = new GLabel ("", center_x-BEAM_LENGTH, center_y+ 80 + g_user_word.getHeight());
	private String userGuesses;
	private int incorrectGuessCounter;
}
