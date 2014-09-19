/* 
 * MagicSquare.java
 * 
 * Author:          Computer Science E-22 staff
 * Modified by:     <your name>, <your e-mail address>
 * Date modified:   <current date>
 */

import java.util.*;

public class MagicSquare {
	private static final int FIRST_ROW = 0;
	private static final int FIRST_COL = 0;
	private static final int EMPTY_SQUARE = 0;

	// the current contents of the cells of the puzzle values[r][c]
	// gives the value in the cell at row r, column c
	private int[][] values;

	// the order (i.e., the dimension) of the puzzle
	private int order;

	// What each row and column should add up to
	private int sum; 

	// The minimum possible value that can be placed on the magic square
	private final int minNum=1; 
	// The maximum possible number that can be placed on the magic square
	private int maxNumber; 

	//The first possible number to try in a magic square
	int tryNum=1;

	private boolean[] doColumnsAddUpCorrectly;  //Tests to see if the columns add up correctly
	private boolean[] doRowsAddUpCorrectly;  //Tests to see if the rows add up correctly

	// Keeps track of available numbers, true if index number (=real number-1) is available
	private boolean[] isNumberAvailable;    

	/**
	 * Creates a MagicSquare object for a puzzle with the specified
	 * dimension/order.
	 */
	public MagicSquare(int order) {
		values = new int[order][order];
		this.order = order;
		this.maxNumber=order*order;
		this.sum =((this.order)*(this.order)*(this.order)+(this.order))/2;
		System.out.println("The rows and columns should add up to: " + sum);

		isNumberAvailable=new boolean[order*order];

		// All numbers 1 to maxNumber (represented by index 0 to maxNumber-1) 
		// are initially available.
		for(int i=0; i<this.order*this.order; i++)
			isNumberAvailable[i]=true;

		for (int row = 0; row < order; row++) 
		{
			for (int col = 0; col < order; col++) 
				values[row][col] = EMPTY_SQUARE;  
		}
	}

	/**
	 * This method should call the separate recursive-backtracking method
	 * that you will write, passing it the appropriate initial parameter(s).
	 * It should return true if a solution is found, and false otherwise.
	 */
	public boolean solve() {
		// Replace the line below with your implementation of this method.
		// REMEMBER: The recursive-backtracking code should NOT go here.
		// See the comments above.
		return this.makeAValidMagicSquaresRow(FIRST_ROW, FIRST_COL);
	}

	public boolean isValid(int row, int col) {

		int testSum=0;
		for(int i=0; i<this.order; i++){
			testSum+=values[row][i];
		}
		if(testSum==this.sum)
			return true;
		return false;
	}

	public boolean makeAValidMagicSquaresRow(int row, int col) {
		//If we get here, it means that we have gotten to a row that is off the board (past the final row), so all rows work and a solution was found
		if(row == order)
			return true;

			values[row][col]=tryNum++;

			System.out.println(values[row][col]);
			if(isValid(row, col))  //May want to re-name isSafe to isValid, but just to connect it to the Queens program.  
			{
				//The above row is ok, so move on to the next row by making a recursive call, WHICH COULD END THE PROGRAM BY RETURNING TRUE WHEN ROW==boardSize, which means that we have gotten to a row that is off the board (past the final row), so all rows work and a solution was found!
				makeAValidMagicSquaresRow(row+1, FIRST_COL); 

				//If we get here, it's because the previous method makeAValidMagicSquaresRow(row+1); RETURNED early, so we're going to be backtracking.  If it doesn't return early, it won't return at all except for the "return true;" part at the top.
				removeRow(row, col); // removes the Row and resets the arrays that we had filled earlier in the placeRow method 
			}
		}
		return false;
	}

	private void placeRow(int row, int col) {

	}

	public void removeRow(int row, int col) {


	}

	/**
	 * Displays the current state of the puzzle.
	 * You should not change this method.
	 */
	public void display() {
		for (int r = 0; r < order; r++) {
			printRowSeparator();
			for (int c = 0; c < order; c++) {
				System.out.print("|");
				if (values[r][c] == 0)
					System.out.print("   ");
				else {
					if (values[r][c] < 10) {
						System.out.print(" ");
					}
					System.out.print(" " + values[r][c] + " ");
				}
			}
			System.out.println("|");
		}
		printRowSeparator();
	}

	// A private helper method used by display()
	// to print a line separating two rows of the puzzle.
	private void printRowSeparator() {
		for (int i = 0; i < order; i++)
			System.out.print("-----");
		System.out.println("-");
	}

	public static void main(String[] args) {
		/*******************************************************
		 **** You should NOT change any code in this method ****
		 ******************************************************/

		Scanner console = new Scanner(System.in);
		System.out.print("What order Magic Square would you like to solve? ");
		int order = console.nextInt();

		MagicSquare puzzle = new MagicSquare(order);
		if (puzzle.solve()) {
			System.out.println("Here's the solution:");
			puzzle.display();
		} else {
			System.out.println("No solution found.");
		}
	}
}