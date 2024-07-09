package smelet01.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import edu.princeton.cs.introcs.*;//import StdLib
/**
 * @author Sotirios Rafail Meletiou AM941797
 */
public class Sudoku {

	private String[][] grid;// grid and board
	private boolean[][] startTable;// truth table to make non_removable tokens

	/**
	 * Constructor for the two tables. 
	 * initializes the two dimensional table that is
	 * the board/grid of the game and initializes another table that is used to make
	 * the starting given numbers non_revomable and the are 13x13 instead of 9x9
	 * because the borders are loaded in the same table
	 */
	public Sudoku() {
		this.grid = new String[13][13];
		this.startTable = new boolean[13][13];
	}

	/**
	 * Getter of the grid.
	 * @return returns the grid
	 */
	public String[][] getGrid() {
		return grid;
	}

	/**
	 * This method generates the borders of each 3x3 and 9x9 boxes.
	 * 
	 * @return returns the grid table with the borders on it
	 */
	public String[][] GenerateGrid() {
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				grid[i][j] = "   ";

		for (int r = 0; r < 12; r++)
			for (int c = 0; c <= 12; c += 4)
				grid[r][c] = "|";

		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++) {
				if (i == 0 || i == 4 || i == 8 || i == 12)
					grid[i][j] = "---";
				if (((i == 0 || i == 4 || i == 8 || i == 12) && ((j == 0) || j == 4 || j == 8 || j == 12)))
					grid[i][j] = "+";
			}

		return grid;
	}

	/**
	 * This method fills up the grid table that has the border of the game in with
	 * the numbers that it gets from the given file.
	 * 
	 * It reads the file row by row and puts the numbers to their corresponding
	 * position. If the number is (0) then it puts a blank space
	 * 
	 * @param f The file with the numbers
	 * @return returns the grid with the starting numbers on
	 * @throws FileNotFoundException in case the file can not be read
	 */
	public String[][] FillTable(File f) throws FileNotFoundException {
		Scanner scan = new Scanner(f);
		int num = 0;
		for (int row = 1; row < 13; row++)
			for (int col = 1; col < 13; col++) {
				if (grid[row][col].equals("   ")) {
					num = scan.nextInt();
					grid[row][col] = " " + num + " ";
					// if(num!=0)
					// startTable[row][col]=true;

				}
				if (grid[row][col].equals(" 0 ")) {
					grid[row][col] = "   ";
					// startTable[row][col]=false;
				}
			}
		scan.close();
		return grid;
	}

	/**
	 * This method is to make the borders and the starting numbers non_removable.
	 * 
	 * it is called right after the {@link FillTable} method to make the starting
	 * table "static"
	 */
	public void NonRemovable() {
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid.length; j++)
				if (grid[i][j].equals("  ") || grid[i][j].equals("---") || grid[i][j].equals("+")
						|| grid[i][j].equals("|")
						|| ((grid[i][j].equals(" 1 ")) || (grid[i][j].equals(" 2 ")) || (grid[i][j].equals(" 3 "))
								|| (grid[i][j].equals(" 4 ")) || (grid[i][j].equals(" 5 "))
								|| (grid[i][j].equals(" 6 ")) || (grid[i][j].equals(" 7 "))
								|| (grid[i][j].equals(" 8 ")) || (grid[i][j].equals(" 9 "))))

					startTable[i][j] = true;
				else
					startTable[i][j] = false;
	}

	/**
	 * This method prints the board with the numbers and the grid.
	 * 
	 * It prints it after every move
	 * 
	 * 
	 */
	public String toString() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++)
				System.out.print(grid[i][j]);

			System.out.println();
		}
		return "";
	}

	/**
	 * A method to correct the given coordinates of the table.
	 * 
	 * because the table is 13x13 the positions of the numbers are not in the
	 * "right" spot for example without this if you wanted the 9th number you had to
	 * write 11 to access it and it is called two times one for the row and one for
	 * the column So the 4th number is in the 4th place and not 5th the 9th number
	 * is on the 9th place and not the 11th and so on (numbers 1,2,3 were already on
	 * the correct place)
	 * 
	 * @param num its the number that was given to be corrected
	 * @return returns the correct position to the table
	 */
	public int CorrectingPosition(int num) {
		switch (num) {
		case 4: // the 4th number was on the 5th place of the table
			num = 5;
			break;
		case 5:// the 5th number was on the 6th place of the table
			num = 6;
			break;
		case 6:// the 6th number was on the 7th place of the table
			num = 7;
			break;
		case 7:// the 7th number was on the 9th place of the table
			num = 9;
			break;
		case 8:// the 8th number was on the 10th place of the table
			num = 10;
			break;
		case 9:// the 9th number was on the 11th place of the table
			num = 11;
			break;
		}
		return num;// return corrected placement
	}

	/**
	 * A method to add numbers on the table.
	 * 
	 * You give the (row,col) coordinates to the specific cell you want to add a
	 * number (it has to be an empty cell. if its not empty you have to call
	 * {@link removeNum} first)
	 * 
	 * @param grid give the current state of the table
	 */
	public void addNum(String[][] grid) {
		int row, col, num, crow, ccol;// crow(corrected row) ccol(corrected column)
		 Scanner scan =new Scanner(System.in);
		System.out.println("Give row ");
		row = scan.nextInt();
		System.out.println("Give column");
		col = scan.nextInt();

		crow = CorrectingPosition(row);// calls the correction method
		ccol = CorrectingPosition(col);

		while (!grid[crow][ccol].equals("   ")) {// you can only add a number on a blank space and can not overwrite the
													// previous one
			System.out.println("It has to be an empty cell to add a number");
			System.out.println("Give row ");
			row = scan.nextInt();
			System.out.println("Give column");
			col =scan.nextInt();
			crow = CorrectingPosition(row);// calls the correction method
			ccol = CorrectingPosition(col);
		}

		System.out.println("Give number");
		num = scan.nextInt();

		grid[crow][ccol] = " " + num + " "; // it puts a blank space on the left and right so the table can be more
											// readable and symmetric
      
	}

	/**
	 * A method to remove numbers from the table.
	 * 
	 * It can only remove the numbers that were placed from the player and not the
	 * starting ones
	 * 
	 * @param grid gives the current state of the table
	 */
	public void removeNum(String[][] grid) {
		int row, col, crow, ccol;// crow(corrected row) ccol(corrected column)
        Scanner scan =new Scanner(System.in);
		System.out.println("Give row");
		row = scan.nextInt();
		System.out.println("Give column");
		col = scan.nextInt();
		crow = CorrectingPosition(row);// calls the correction method
		ccol = CorrectingPosition(col);
		while (startTable[crow][ccol] == true) {// in case you tried to remove a starting number

			System.out.println("You cant remove this token");
			System.out.println("Give row");
			row = scan.nextInt();
			System.out.println("Give column");
			col = scan.nextInt();
			crow = CorrectingPosition(row);// calls the correction method
			ccol = CorrectingPosition(col);
		}

		grid[crow][ccol] = "   ";// replaces the removed number with blank
    
	}

	
/**
 * This method saves the numbers from the table on a file for future use.
 * 
 * The switch in this method is to make the output file look just like the input file 
 * @param File a new file for output
 * @throws FileNotFoundException in case the file cannot be found
 */
	public void outputFile(String File) throws FileNotFoundException {
		String Output = File;
		PrintWriter write = new PrintWriter(Output);

		for (int i = 1; i < 12; i++)
			if (i != 4 && i != 8 && i != 12) {
				for (int j = 1; j < 12; j++) {
					switch (grid[i][j]) {
					case " 1 ":
						write.print("1");
						write.print(" ");
						break;
					case " 2 ":
						write.print("2");
						write.print(" ");
						break;
					case " 3 ":
						write.print("3");
						write.print(" ");
						break;
					case " 4 ":
						write.print("4");
						write.print(" ");
						break;
					case " 5 ":
						write.print("5");
						write.print(" ");
						break;
					case " 6 ":
						write.print("6");
						write.print(" ");
						break;
					case " 7 ":
						write.print("7");
						write.print(" ");
						break;
					case " 8 ":
						write.print("8");
						write.print(" ");
						break;
					case " 9 ":
						write.print("9");
						write.print(" ");
						break;
					case "   ":
						write.print("0");
						write.print(" ");
					}
				}
				if(i!=11)
				write.println();
			}
		write.close();
	}

	/**
	 * Main method.
	 * It calls all the methods in the program ,first scans the file with the starting numbers ,then creates the table and generates the grid/borders {@link GenerateGrid}
	 * then fills up the table with the starting numbers {@link FillTable}.Then it makes the starting table non removable with {@link NonRemovable} tlh it makes the starting numbers and the borders static
	 * And then is the menu of the game that you can either add number {@link addNum} or  remove a number {@link removeNum} or quit the game
	 * To end the game you either have to quit or the all 3 of the  checkRows , checkCols and heckBoxes returns true
	 * 
	 * @param args in the arguments section you put the path of the file or the name
	 *             inside the project
	 * @throws FileNotFoundException in case the file can not be found
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// File Ftable = new File("C:\\Users/sotir/EPL133/Homeworks/Table");
		File Ftable = new File(args[0]);
		Scanner scan = new Scanner(Ftable);

		Sudoku sudoku = new Sudoku();//calls the constructor for the grid
		Sudoku_Checker check = new Sudoku_Checker();//calls the Sudoku_Checker class
		sudoku.GenerateGrid();//generates the grid/border
		sudoku.FillTable(Ftable); // calls the method o fill up the table with the f file
		sudoku.NonRemovable();// calls this method to make the starting table "static"
        
		sudoku.toString();
		boolean end = false, rows=false ,cols=false ,boxes=false;
		int answer = 0;
		do {// control menu
			System.out.println("1: Add Number.");
			System.out.println("2: Remove Number.");
			System.out.println("3: Quit.");
			answer = StdIn.readInt();//reads the answer (it doesnt work with the scanner for some reason)
			switch (answer) {
			case 1:// add number
				sudoku.addNum(sudoku.getGrid());
				sudoku.toString(); // prints the board
				rows=check.checkRows(sudoku.getGrid());//calls the checkRows from the Sudoku_Checker
				cols=check.checkCols(sudoku.getGrid());//calls the checkCols from the Sudoku_Checker
				boxes=check.checkBoxes(sudoku.getGrid());//calls the checkBoxes from the Sudoku_Checker
				if(rows==true&&cols==true&&boxes==true)
				//if(check.checkEnd(sudoku.getGrid())==true)
				end = true;
				break;
			case 2:// remove number
				sudoku.removeNum(sudoku.getGrid());
				sudoku.toString(); // prints the board
				rows=check.checkRows(sudoku.getGrid());//calls the checkRows from the Sudoku_Checker
				cols=check.checkCols(sudoku.getGrid());//calls the checkCols from the Sudoku_Checker
				boxes=check.checkBoxes(sudoku.getGrid());//calls the checkBoxesfrom the Sudoku_Checker	
				if(rows==true&&cols==true&&boxes==true)
				//if(check.checkEnd(sudoku.getGrid())==true)
				end = true;
				break;
			}
		} while (answer != 3 && end == false);// quit or finished 
		if (answer == 3) {
			System.out.println("You quitted the game.");
			sudoku.outputFile("OutPut");//saves the current numbers on the board to an output file
		} else if (end == true) {
			System.out.println();
			System.out.println("=====================================");
			System.out.println("  Congratulations you SOLVED IT !!! ");
			System.out.println("=====================================");
			sudoku.outputFile("OutPut");
		}
		scan.close();
	}
}