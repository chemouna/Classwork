/* 
 * MagicSquare.java
 * 
 * Author:          Computer Science E-22 staff
 * Modified by:     Bryan Miller, bmiller100wpm@gmail.com
 * Date modified:   9/4/2014
 */

import java.util.*;

public class MagicSquare {
	// The values array gives the current contents of the cells of the 
	// puzzle values[r][c] in the cell at row r, column c.  A value of 0
	// indicates a blank cell.
	private int[][] values;

	// the order (i.e., the dimension) of the puzzle
	private int order;

	// Also, add to the MagicSquare class any other fields and methods that 
	// are needed to maintain the state of a magic-square puzzle and to solve 
	// it using recursive backtracking.  Use proper encapsulation when adding 
	// new members of the class (use private variables).  Determine what state needs 
	// to be maintained
	// in order to check if you can assign a particular number to a particular
	// cell, given the current state of the puzzle.
	private int boardSize;  //The number of columns and rows the board has
	private int sum; //What the rows and columns should add up to
	
	private int maxNumber; //What the maximum possible number is for the order of the magic square

	//Fields from the Queens class, but we don't have to worry about the diagonal fields:
	//private boolean[][] squareEmpty;         // keeps track of empty squares.   I dont know if I need this one.
	private boolean[] doColumnsAddUpCorrectly;  //Tests to see if the columns add up correctly
	private boolean[] doRowsAddUpCorrectly;  //Tests to see if the rows add up correctly
	private boolean[]  isNumberAvailable;    // keeps track of available numbers, true if index number (=real number-1) is available, false if taken
	//private boolean[] addsUpCorrectly;  // keeps track of if the row adds up correctly.  Don't know if I need this one.
	private int[][] numberOnSquare;  // An int that is the number on the 2-d [row][col] of the square - should rename it to numberOnSquare eventually.  Do need this one
	private final int minNum=1; //This is the minimum possible value that can be placed on the magic square 

	/**
	 * Creates a MagicSquare object for a puzzle with the specified
	 * dimension/order.
	 */
	public MagicSquare(int order) {
		values = new int[order][order];
		this.order = order;
		
		this.boardSize=order; // I could just use the variable "order" instead, but just to help connect my program with the Queens program.
		this.maxNumber=order*order;
		//Find what the rows and columns should add up to
		this.sum =((this.order)*(this.order)*(this.order)+(this.order))/2;
		System.out.println("The rows and columns should add up to: " + sum);

		// Add code to this constructor as needed to initialize
		// the fields that you add to the object.

		//The below is from the Queens file, it initializes various fields defined as class fields above
		//squareEmpty = new boolean[boardSize][boardSize];
		isNumberAvailable=new boolean[order*order];
		for(int i=0; i<this.order*this.order; i++){
			isNumberAvailable[i]=true;
			System.out.println("Num " + i + " is: " + isNumberAvailable[i]);
		}
		
		numberOnSquare = new int[boardSize][boardSize];
		for (int row = 0; row < boardSize; row++) 
		{
			for (int col = 0; col < boardSize; col++) 
			{
				//squareEmpty[row][col] = true;  
				numberOnSquare[row][col] = 0;  //Initialize all squares to zero in the magic square per the instructions
			}
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

		this.recursiveBacktracker(0);  //Start at row 0 

		return false;
	}

	// MAKE THE MATH IN THE BELOW METHODS ADJUSTED FOR OUR PURPOSES

	/*
	 * placeQueen - place a queen (for Magic Square, instead of queen, it's a number) at the specified row and column
	 */
	public void placeQueen(int row, int col) {
		int testNum = (minNum + (int)((maxNumber-minNum+1) * Math.random()));  //Instead of true, this should be a number between 1 and the maxNumber possible
		//if testNum is anywhere in numberOnSquare already, then find a different number
		
		numberOnSquare[row][col] = testNum;
		//squareEmpty[row][col] = false;

	}

	/*
	 * removeQueen - remove the queen (for Magic Square, instead of queen, it's a number) at the specified row and column
	 */
	public void removeQueen(int row, int col) {
		numberOnSquare[row][col] = 0;  ////Empty squares are to be zero in the magic square per the instructions
		//squareEmpty[row][col] = true;
	}

	/*
	 * isSafe - returns true if it is safe to place a queen (for Magic Square, instead of queen, it's a number) at the
	 * specified square, and false otherwise.
	 */
	public boolean isSafe(int row, int col) {

		//It's safe if it's empty in this case
		//in the queens problem, there is a unique number for each diagonal, and the contents of the array say whether or not there is a queen (true or false) in that diagonal!!!!  it's kind of like flattening out the diagonals into rows of their own to see if there is a queen already in it, true or false in this row (or diagonal)
		if(col < this.order-1)
			//return (squareEmpty[row][col]); 	//Here I think we have to do the addition test to see if it all adds up, and return true if it does, false otherwise.
		//Just to clarify what's going on, else if(col== this.order) meaning we're at the end of a row, then:
		

		if(row < this.order-1){
			int rowSum=0;
			for(int i = 0; i < this.order; i++){
				rowSum+=numberOnSquare[row][i];
			}
			if(rowSum==this.sum)
				return true;
			return false;
		}
		
		//and if row==this.order test!
		int colSum=0;
		for(int i = 0; i < this.order; i++){
			colSum+=numberOnSquare[i][col];
		}
		if(colSum==this.sum){
			System.out.println("In isSafe() method");
			return true;
			
		}
		return false;
	}

	// Make sure that you don't attempt to assign a given number in more than one location.
	// Use recursive backtracking, but it should do so in a way that allows the 
	// program to be more efficient. One way to do this is to have the program consider
	// the squares in the following order: first fill the first row, then fill the remainder 
	// of the first column, then fill the remainder of the second row, then fill the 
	// remainder of the second column, etc. You are welcome to use a different 
	// approach, provided that it employs recursive backtracking. In particular, make 
	// sure that your revised algorithm still backtracks as soon as one of the row or
	// column constraints is violated.
	/**
	 * 
	 * recursiveBacktracker
	 * 
	 * @return true if a solution is found, else false.
	 */
	public boolean recursiveBacktracker(int row)
	{
		//If we get here, it means that we have gotten to a row that is off the board (past the final row), so all rows work and a solution was found
		if(row == boardSize)
			return true;

		//Try each column in the current row.  
		//If it's not a safe square, then the loop will continue to the next column.  If there are no safe squares in a column, then the loop will eventually end and the method will return (backtrack) to the previous recursive call. 	
		for(int col = 0; col < boardSize; col++)
		{
			if(isSafe(row, col))  //May want to re-name isSafe to isValid, but just to connect it to the Queens program.  
			{
				placeQueen(row, col);  //May want to re-name placeQueen to placeValue, but just to connect it to the Queens program

				//The above row is ok, so move on to the next row by making a recursive call, WHICH COULD END THE PROGRAM BY RETURNING TRUE WHEN ROW==boardSize, which means that we have gotten to a row that is off the board (past the final row), so all rows work and a solution was found!
				recursiveBacktracker(row+1); 

				//If we get here, we've backtracked
				removeQueen(row, col); //May want to re-name placeQueen to removeValue, but just to connect it to the Queens program
			}
		}

		return false;
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