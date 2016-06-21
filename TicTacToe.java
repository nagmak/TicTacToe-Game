// Tic Tac Toe game
// A program that lets 1 human play versus the computer

import java.util.*;
import java.util.Scanner;

public class TicTacToe{
	private static char[][] board = new char[3][3];
	private static int user = 1;
	private static Random r = new Random();

	public static void main(String[] args){
		init_board(); // initializes + displays empty board

		// Game-play Setup
		System.out.println("\n-- ULTIMATE TIC TAC TOE --\n");
		Scanner key = new Scanner(System.in);
		System.out.print("User 1, Enter your Name: ");
		String name1 = key.nextLine();
		String name2 = "Computer";
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
				row_col_choice(row); // row selection

				System.out.print("[col (0 - 2)]: ");
				int col = key.nextInt();
				row_col_choice(col); // col selection
				update_board(name1_char, name2_char, row, col);
			}
			else if (user == 2){
				System.out.println(name2 + "'s turn.");
				System.out.print("Row selected [row (0 - 2)]: ");
				int row = computer_choice();
				row_col_choice(row);

				System.out.print("Column selected [col (0 - 2)]: ");
				int col = computer_choice();
				row_col_choice(col);
				update_board(name1_char, name2_char, row, col);
			}

			// Checks for Win/Tie
			if (detect_wintie(name1_char) == true){
				System.out.println(name1 + ", YOU WIN!");
				user = 0;
				break;
			}
			if (detect_wintie(name2_char) == true){
				System.out.println(name2 + ", YOU WIN!");
				user = 0;
				break;
			}
			if (calculate_tie() == true){
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
		for(int row = 0; row < 3; row++){
			for (int col = 0; col < 3; col++){
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
	public static void update_board(char name1_char, char name2_char, int r, int c){
		for (int row = 0; row < 3; row++){
			for (int col = 0; col < 3; col++){
				if (board[r][c] == ' '){ // checks if the spot is empty
					if (user == 1){
						board[r][c] = name1_char;
					}
					else if (user == 2){
						board[r][c] = name2_char;
					}	
				}
			}
		}

		display_board(); // display
	}

	// Winner or Tie detection
	public static boolean detect_wintie(char select){

		// Checks for 3 in a row/col/diagonal
		if (board[0][0] == select && board[0][1] == select && board[0][2] == select){
			return true;
		}
		else if (board[1][0] == select && board[1][1] == select && board[1][2] == select){
			return true;
		}
		else if (board[2][0] == select && board[2][1] == select && board[2][2] == select){
			return true;
		}
		else if (board[0][0] == select && board[1][0] == select && board[2][0] == select){
			return true;
		}
		else if (board[0][1] == select && board[1][1] == select && board[2][1] == select){
			return true;
		}
		else if (board[0][2] == select && board[1][2] == select && board[2][2] == select){
			return true;
		}
		else if (board[0][0] == select && board[1][1] == select && board[2][2] == select){
			return true;
		}
		else if (board[2][0] == select && board[1][1] == select && board[0][2] == select){
			return true;
		}
		return false; // game continues
	}

	// Calculates scores to detect a tie
	public static boolean calculate_tie(){
		int scoreX = 0;
		int scoreO = 0;
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if (board[i][j] == 'X' || board[i][j] == 'x'){
					scoreX++;
				}
				if (board[i][j] == 'O' || board[i][j] == 'o'){
					scoreO++;
				}
			}
		}

		if ((scoreO == 4 && scoreX == 5) || (scoreO == 5 && scoreX == 4)){ // calculates tie based on score
				System.out.println("It's a tie!");
				return true;
		}
		return false;
	}
	
	public static int computer_choice(){
		int row_col = r.nextInt(2);
		return row_col;
	}

	public static int row_col_choice(int row_col){
		row_col = key.nextInt();
		while(row_col < 0 || row_col > 2){
			System.out.print("Incorrect input. Please enter a value between 0 and 2: ");
			row_col = key.nextInt();
		}

		return row_col;
	}
}
