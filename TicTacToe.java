// Tic Tac Toe game
// A program that lets 1 human play versus the computer

import java.util.*;
import java.util.Scanner;

public class TicTacToe{
	private static char[][] board = new char[3][3];
	private static int user = 1;
	private static Random r = new Random();
	private static Scanner key = new Scanner(System.in);
	private static final String name2 = "Computer";
	private static final int ROWS = 3, COLS = 3;

	public static void main(String[] args){
		init_board(); // initializes + displays empty board

		// Game-play Setup
		System.out.println("\n-- ULTIMATE TIC TAC TOE --\n");
		System.out.print("User 1, Enter your Name: ");
		String name1 = key.nextLine();
		System.out.println(name1 + ", please select your character[X] or [O]: ");
		char name1_char = key.next().charAt(0);
		char name2_char = '\0';
		while(name1_char != 'X' && name1_char != 'x' && name1_char !='O' && name1_char != 'o'){
				System.out.print("Incorrect character. Please enter a character[X] or [O]: ");
				name1_char = key.next().charAt(0);
		}
		
		// Determines the Computer's character
		if (name1_char == 'X' || name1_char == 'x'){
			System.out.println(name2 + "'s character is now " + 'O');
			name2_char = 'O';
		}
		else if (name1_char == 'o' || name1_char == 'O'){
			System.out.println(name2 + "'s character is now " + 'X');
			name2_char = 'X';
		}

		// Main Program
		while(user != 0){
			if (user == 1){
				System.out.println(name1 + ", it's your turn.");
				System.out.print("Where do you want to put it? [row (0 - 2)]: ");
				int row = key.nextInt();
				row = row_col_choice(row); // row error check

				System.out.print("[col (0 - 2)]: ");
				int col = key.nextInt();
				col = row_col_choice(col); // col error check
				update_board(name1_char, row, col);
			}
			else if (user == 2){
				System.out.println("\n" + name2 + "'s turn.");
				computer_choice(name2_char);
				System.out.print("\n" + name2 + " has made its move.");
				display_board();
			}

			// Checks for Win/Tie
			if (detect_wintie(name1_char) == true){
				System.out.println(name1 + ", YOU WIN!");
				user = 0;
				break;
			}
			if (detect_wintie(name2_char) == true){
				System.out.println(name2 + " WINS.");
				user = 0;
				break;
			}
			if (calculate_tie() == true){
				System.out.println("It's a TIE.");
				user = 0;
				break;
			}
			else{ // creates turns to continue the game
				if (user == 1){
					user = 2;
				}
				else if (user == 2){
					user = 1;
				}
			}
			
		}
	}

	// Initial board with blank characters
	public static void init_board(){
		for(int row = 0; row < ROWS; row++){
			for (int col = 0; col < COLS; col++){
				board[row][col] = ' ';
			}
		}
		System.out.println("Welcome to a game of Tic Tac Toe. I hope you're ready.");
		display_board(); // display
	}

	// Displays the Tic Tac Toe board
	public static void display_board(){
		System.out.print("   0   1   2\n");
		System.out.print("0| " + board[0][0] + " | " + board[1][0] + " | " + board[2][0] + "\n");
		System.out.print("  -----------\n");
		System.out.print("1| " + board[0][1] + " | " + board[1][1] + " | " + board[2][1] + "\n");
		System.out.print("  -----------\n");
		System.out.print("2| " + board[0][2] + " | " + board[1][2] + " | " + board[2][2] + "\n");
	}

	// Updates board with user selection
	public static void update_board(char name1_char, int row, int col){
		if (board[row][col] == ' '){
			board[row][col] = name1_char;
		}

		display_board(); // display
	}

	// Winner or Tie detection
	public static boolean detect_wintie(char select){

		// Checks for 3 in a row/col/diagonal
		for (int row = 0; row < ROWS; row++){
			if (board[row][0] == select && board[row][1] == select && board[row][2] == select){
				return true;
			}
		}

		for (int col = 0; col < COLS; col++){
			if (board[0][col] == select && board[1][col] == select && board[2][col] == select){
				return true;
			}
		}

		if (board[0][0] == select && board[1][1] == select && board[2][2] == select){
			return true;
		}
		if (board[2][0] == select && board[1][1] == select && board[0][2] == select){
			return true;
		}
		return false; // game continues
	}

	// Calculates scores to detect a tie
	public static boolean calculate_tie(){
		int score = 0;
		for (int i = 0; i < ROWS; i++){
			for (int j = 0; j < COLS; j++){
				if (board[i][j] != ' '){
					score++;
				}
			}
		}

		if(score == 9){
			return true;
		}

		return false;
	}
	
	public static void computer_choice(char name2_char){
		for (int row = 0; row < ROWS; row++){
			for (int col = 0; col < COLS; col++){
				if (board[row][col] == ' '){
					board[row][col] = name2_char;
					return;
				}
			}
		}
		return;
	}

	public static int row_col_choice(int row_col){
		while(row_col < 0 || row_col > 2){
			System.out.print("Incorrect input. Please enter a value between 0 and 2: ");
			row_col = key.nextInt();
		}

		return row_col;
	}
}
