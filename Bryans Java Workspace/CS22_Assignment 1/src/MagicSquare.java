/* 
 * MagicSquare.java
 * 
 * Author:          Computer Science E-22 staff
 * Modified by:     <your name>, <your e-mail address>
 * Date modified:   <current date>
 */

import java.util.*;

public class MagicSquare {
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
				values[row][col] = 0;  
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
		return this.tryAMagicSquare(0);
	}

	/**
	 * 
	 * findACorrectNumber - goes recursively row by row, and loops through columns in each row.
	 * The program ends and returns true if we make it past the final row and "off the board."
	 * Otherwise, the program keep searching for a right combination of numbers.
	 * 
	 * @return true if a solution is found, else false.
	 */
	public boolean tryAMagicSquare(int row)
	{
		//If we get here, it means that we have gotten to a row that is off the board (past the final row), so all rows work
		if(row == this.order)
			return true;

		//Test each row's sum in the current magic box.  
		//If it dosn't add up right, make a recursive call to find a new magic box 	
		for(int i = 0; i < this.order; i++)
		{
			//Place a number in a column  
			this.makeAValidMagicSquaresRow(row);  

			//If the magic square is valid, then work on the columns
			if(this.isValid(row, i))    
			{
				//The current row is ok (for now), so move on to the next row by making a recursive call, WHICH COULD END THE PROGRAM BY RETURNING TRUE WHEN ROW==boardSize, which means that we have gotten to a row that is off the board (past the final row)
				tryAMagicSquare(row+1); 

				//If we get here, we've backtracked because our potential solution didn't work
				this.removeNumber(row, i); 
			}
		}
		return false;
	}   

	public boolean isValid(int row, int col) {
		// TODO Auto-generated method stub
		return true;
	}

	public void makeAValidMagicSquaresRow(int row) {
		int testNum = (minNum + (int)((maxNumber-minNum+1) * Math.random()));
		for(int i=0; i<this.order; i++)
		{
			do{
				if(isNumberAvailable[testNum-1])
					values[row][i]=testNum;
				else
					testNum = (minNum + (int)((maxNumber-minNum+1) * Math.random()));
			} while(!isNumberAvailable[testNum-1]);
			System.out.println("The numbers in the first row are: " + values[row][i]);
		}
	}

	public void removeNumber(int row, int col) {


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