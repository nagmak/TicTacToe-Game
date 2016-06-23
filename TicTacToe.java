// Tic Tac Toe game
// A program that lets 1 human play versus the computer

import java.util.*;
import java.util.Scanner;

public class TicTacToe{
	private static char[][] board = new char[3][3];
	private static int user = 1;
	private static Random r = new Random();
	private static Scanner key = new Scanner(System.in);
	private static final String COMPUTER_NAME = "Computer";
	private static final int ROWS = 3, COLS = 3, FULL = 9;

	public static void main(String[] args){
		initializeBoard();

		// Game-play Setup
		System.out.println("\n-- ULTIMATE TIC TAC TOE --\n");
		System.out.print("User 1, Enter your Name: ");
		String name1 = key.nextLine();
		System.out.println(name1 + ", please select your character[X] or [O]: ");
		char name1Char = key.next().charAt(0);
		char computerChar = '\0';

		// Error handling for character selection
		while(name1Char != 'X' && name1Char != 'x' && name1Char !='O' && name1Char != 'o'){
				System.out.print("Incorrect character. Please enter a character[X] or [O]: ");
				name1Char = key.next().charAt(0);
		}
		
		// Determines the Computer's character
		if (name1Char == 'X' || name1Char == 'x'){
			System.out.println(COMPUTER_NAME + "'s character is now " + 'O');
			computerChar = 'O';
		}
		else if (name1Char == 'o' || name1Char == 'O'){
			System.out.println(COMPUTER_NAME + "'s character is now " + 'X');
			computerChar = 'X';
		}

		// Main Program
		while(user != 0){
			if (user == 1){
				System.out.println(name1 + ", it's your turn.");
				System.out.print("Where do you want to put it? [col (0 - 2)]: ");
				int col = key.nextInt();
				col = rowcolErrorCheck(col); // col error check

				System.out.print("[row (0 - 2)]: ");
				int row = key.nextInt();
				row = rowcolErrorCheck(row); // row error check
				updateBoard(name1Char, col, row);
				user = 2;
			}
			else if (user == 2){
				System.out.println("\n" + COMPUTER_NAME + "'s turn.");
				computerChoice(computerChar);
				System.out.print("\n" + COMPUTER_NAME + " has made its move.");
				displayBoard();
				user = 1;
			}

			// Checks for Win/Tie
			if (detectWintie(name1Char)){
				System.out.println(name1 + ", YOU WIN!");
				user = 0;
				break;
			}
			if (detectWintie(computerChar)){
				System.out.println(COMPUTER_NAME + " WINS.");
				user = 0;
				break;
			}
			if (calculateTie()){
				System.out.println("It's a TIE.");
				user = 0;
				break;
			}
		}
	}

	// Initial board with blank characters
	public static void initializeBoard(){
		for(int col = 0; col < COLS; col++){
			for (int row = 0; row < ROWS; row++){
				board[col][row] = ' ';
			}
		}
		System.out.println("Welcome to a game of Tic Tac Toe. I hope you're ready.");
		displayBoard(); // display
	}

	// Displays the Tic Tac Toe board
	public static void displayBoard(){
		System.out.print("\n   0   1   2\n");
		System.out.print("0| " + board[0][0] + " | " + board[1][0] + " | " + board[2][0] + "\n");
		System.out.print("  -----------\n");
		System.out.print("1| " + board[0][1] + " | " + board[1][1] + " | " + board[2][1] + "\n");
		System.out.print("  -----------\n");
		System.out.print("2| " + board[0][2] + " | " + board[1][2] + " | " + board[2][2] + "\n");
	}

	// Updates board with human player selection
	public static void updateBoard(char name1Char, int col, int row){
		while(board[col][row] != ' '){
			System.out.println("Oops. That spot has been taken.");
			System.out.print("Select a col[0 - 2]: ");
			col = key.nextInt();
			System.out.print("Select a row[0 - 2]: ");
			row = key.nextInt();
		}

		if (board[col][row] == ' '){
			board[col][row] = name1Char;
		}
		displayBoard(); // display
	}

	// Updates board with computer selection
	public static void computerChoice(char computerChar){
		int col = r.nextInt(COLS);
		int row = r.nextInt(ROWS);
		while(board[row][col] != ' '){
			col = r.nextInt(COLS);
			row = r.nextInt(ROWS);
		}

		if (board[col][row] == ' '){
			board[col][row] = computerChar;
		}
	}

	// Winner or Tie detection
	public static boolean detectWintie(char select){

		// Checks for 3 in a col
		for (int col = 0; col < COLS; col++){
			if (board[col][0] == select && board[col][1] == select && board[col][2] == select){
				return true;
			}
		}
		// Checks for 3 in a row
		for (int row = 0; row < COLS; row++){
			if (board[0][row] == select && board[1][row] == select && board[2][row] == select){
				return true;
			}
		}
		// Checks for 3 in a diagonal
		if (board[0][0] == select && board[1][1] == select && board[2][2] == select){
			return true;
		}
		if (board[2][0] == select && board[1][1] == select && board[0][2] == select){
			return true;
		}
		return false; // game continues
	}

	// Decides if there is a tie
	public static boolean calculateTie(){
		int score = 0;
		for (int col = 0; col < COLS; col++){
			for (int row = 0; row < ROWS; row++){
				if (board[col][row] != ' '){
					score++;
				}
			}
		}
		// Checks if the board is full
		if(score == FULL){
			return true;
		}

		return false;
	}

	// Error handling for row/col human player input
	public static int rowcolErrorCheck(int row_col){
		while(row_col < 0 || row_col > 2){
			System.out.print("Incorrect input. Please enter a value between 0 and 2: ");
			row_col = key.nextInt();
		}

		return row_col;
	}
}
