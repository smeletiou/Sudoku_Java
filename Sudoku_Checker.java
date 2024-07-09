package smelet01.hw1;
/**
 * @author Sotirios Rafail Meletiou AM941797
 */
public class Sudoku_Checker {

	private String[][] grid;
/**
 * This Method checks the Rows of the table if they are correct.
 * 
 * it checks row by row to see if theres any number more than once or if theres any blank space 
 * and prints the coordinates of the mistake 
 * If there are no mistakes to a row it returns true else if there are mistakes it returns false 
 * @param grid the grid/table of the game 
 * @return false or true  so it knows when to stop
 */
	public boolean checkRows(String[][] grid) {
		this.grid = grid;
		for (int i = 1; i < this.grid.length - 1; i++)
			if (i != 4 && i != 8 )
				for (int j = 1; j < this.grid[0].length - 1; j++)
					if (j != 4 && j != 8)
						for (int ch = j + 1; ch < this.grid.length - 1; ch++)

							if (grid[i][j].equals(grid[i][ch]) || grid[i][j].equals("   ")
									|| grid[i][ch].equals("   ")) {
								System.out.println("Rows are not done the problem is" + "(" + grid[i][ch] + ") on "
										+ "(" + CorrectingPosition(i) + "," + CorrectingPosition(ch) + ")");
								return false;
							}
		System.out.println("Rows are done.");
		return true;

	}

	/**
	 * This method checks the columns of the table if they are correct.
	 * 
	 *it checks column by column to see if theres any number more than once or if theres any blank space 
     * and prints the coordinates of the mistake 
     * If there are no mistakes to a row it returns true else if there are mistakes it returns false 
     * @param grid the grid/table of the game 
     * @return false or true so it knows when to stop
     */
	public boolean checkCols(String[][] grid) {
		this.grid = grid;
		for (int j = 1; j < this.grid[0].length - 1; j++)
			if (j != 4 && j != 8)
				for (int i = 1; i < this.grid.length - 1; i++)
					if (i != 4 && i != 8)
						for (int ch = i + 1; ch < this.grid.length - 1; ch++)

							if (grid[i][j].equals(grid[ch][j]) || grid[i][j].equals("   ")
									|| grid[ch][j].equals("   ")) {
								System.out.println("Columns are not done the problem is" + " (" + grid[ch][j] + ") on "
										+ "(" + CorrectingPosition(ch) + "," + CorrectingPosition(j) + ")");
								return false;
							}
		System.out.println("Columns are done.");
		return true;
	}
/**
 * This method corrects the position of the number to show they correct place of the mistake.
 * like the {@link CorrectingPosition} of the Sudoku class it changes the number to the correct position because the table is 13x13 
 * @param num the number to be corrected
 * @return returns the corrected number 
 */
	public int CorrectingPosition(int num) {
		switch (num) {
		case 5:
			num = 4;
			break;
		case 6:
			num = 5;
			break;
		case 7:
			num = 6;
			break;
		case 9:
			num = 7;
			break;
		case 10:
			num = 8;
			break;
		case 11:
			num = 9;
			break;
		}
		return num;// return corrected placement
	}
 /**
 * This is the method that takes the information from the rows,columns and boxes to see if they are finished so the game can end (Den thn xrhshmopoihsa logo enws post tou kuriou Purrou sto forum ,einai h leitourgia 6 tou programmatos all thn ekana monh ths sthn main kalontas kanonika  tis alles 3 3exorista(oi entoles ths stin main uparxoun akoma alla einai se sxolia)).
 * 
 * if the {@link checkRows} ,the {@link checkCols},and the {@link checkBoxes} returns true the game will end 
 * @param grid the grid/table of the game 
 * @return returns true if all 3 of them are true else it returns false
 */
	public boolean checkEnd(String[][] grid) {
		boolean[] ending = new boolean[3];
		ending[0] = checkRows(grid);
		ending[1] = checkCols(grid);
		ending[2] = checkBoxes(grid);
       int TrueCount=0;
		for (int i = 0; i < ending.length; i++)
			if (ending[i] ==true)
				TrueCount++;
			
		if(TrueCount==3)
			return true;
		else
		    return false;
	}
/**
 * This method checks the 9 boxes of the grid to see if they are correct.
 * this is the last check of the game first we check the rows and the columns and then the boxes to see if the table is finished
 * @param grid the grid/table of the game 
 * @return returns true if all 9 boxes are finished correctly else it returns false 
 */
	public boolean checkBoxes(String[][] grid) {
		boolean[] boxes = new boolean[9];
		for(int j=0;j<boxes.length;j++)
			boxes[j]=false;
		
		boxes[0] = checkBoxOne(grid);
		boxes[1] = checkBoxTwo(grid);
		boxes[2] = checkBoxThree(grid);
		boxes[3] = checkBoxFour(grid);
		boxes[4] = checkBoxFive(grid);
		boxes[5] = checkBoxSix(grid);
		boxes[6] = checkBoxSeven(grid);
		boxes[7] = checkBoxEight(grid);
		boxes[8] = checkBoxNine(grid);

		int TrueCount=0;
		for (int i = 0; i < boxes.length; i++)
			if (boxes[i] == true)
				TrueCount++;
			
		if(TrueCount==9) {
			System.out.println("All Boxes are done.");
			return true;
		}
		else
			return false;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/**
	 * This method checks the 1st box if its finished.
	 * It takes the box number by number and checks it with the remaining numbers 
	 * from this box to see if theres a number more than once or a blank space
	 * @param grid the grid/table of the game 
	 * @return true if the box is correctly finished
	 */
	public boolean checkBoxOne(String[][] grid) {
		this.grid = grid;
		int i = 0;
		int j = 0;
		int cr = 1;
		int cc = 0;
		for (i = 1; i < 4; i++)
			for (j = 1; j < 4; j++)
				for (cr = 1; cr < 4; cr++)
					for (cc = j + 1; cc < 4; cc++)
						if (grid[i][j].equals(grid[cr][cc]) || grid[i][j].equals("   ")
								|| (grid[cr][cc].equals("   "))) {

							System.out.println("1st box is not ready ");
							return false;
						}
		return true;

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/**
	 * This method checks the 2nd box if its finished.
	 * It takes the box number by number and checks it with the remaining numbers 
	 * from this box to see if theres a number more than once or a blank space
	 * @param grid the grid/table of the game 
	 * @return true if the box is correctly finished
	 */
	public boolean checkBoxTwo(String[][] grid) {
		this.grid = grid;
		int i = 0;
		int j = 0;
		int cr = 1;
		int cc = 0;
		for (i = 1; i < 4; i++)
			for (j = 5; j < 8; j++)
				for (cr = 1; cr < 4; cr++)
					for (cc = j + 1; cc < 8; cc++)
						if (grid[i][j].equals(grid[cr][cc]) || grid[i][j].equals("   ")
								|| (grid[cr][cc].equals("   "))) {

							System.out.println("2nd box is not ready ");
							return false;
						}
		return true;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/**
	 * This method checks the 3rd box if its finished.
	 * It takes the box number by number and checks it with the remaining numbers 
	 * from this box to see if theres a number more than once or a blank space
	 * @param grid the grid/table of the game 
	 * @return true if the box is correctly finished
	 */
	public boolean checkBoxThree(String[][] grid) {
		this.grid = grid;
		int i = 0;
		int j = 0;
		int cr = 1;
		int cc = 0;
		for (i = 1; i < 4; i++)
			for (j = 9; j < 12; j++)
				for (cr = 1; cr < 4; cr++)
					for (cc = j + 1; cc < 12; cc++)
						if (grid[i][j].equals(grid[cr][cc]) || grid[i][j].equals("   ")
								|| (grid[cr][cc].equals("   "))) {
							System.out.println("3rd box is not ready ");
							return false;
						}
		return true;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/**
	 * This method checks the 4th box if its finished.
	 * It takes the box number by number and checks it with the remaining numbers 
	 * from this box to see if theres a number more than once or a blank space
	 * @param grid the grid/table of the game 
	 * @return true if the box is correctly finished
	 */
	public boolean checkBoxFour(String[][] grid) {
		this.grid = grid;
		int i = 0;
		int j = 0;
		int cr = 1;
		int cc = 0;
		for (i = 5; i < 8; i++)
			for (j = 1; j < 4; j++)
				for (cr = 5; cr < 8; cr++)
					for (cc = j + 1; cc < 4; cc++)
						if (grid[i][j].equals(grid[cr][cc]) || grid[i][j].equals("   ")
								|| (grid[cr][cc].equals("   "))) {

							System.out.println("4th box is not ready ");
							return false;
						}
		return true;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/**
	 * This method checks the 5th box if its finished.
	 * It takes the box number by number and checks it with the remaining numbers 
	 * from this box to see if theres a number more than once or a blank space
	 * @param grid the grid/table of the game 
	 * @return true if the box is correctly finished
	 */
	public boolean checkBoxFive(String[][] grid) {
		this.grid = grid;
		int i = 0;
		int j = 0;
		int cr = 1;
		int cc = 0;
		for (i = 5; i < 8; i++)
			for (j = 5; j < 8; j++)
				for (cr = 5; cr < 8; cr++)
					for (cc = j + 1; cc < 8; cc++)
						if (grid[i][j].equals(grid[cr][cc]) || grid[i][j].equals("   ")
								|| (grid[cr][cc].equals("   "))) {
							System.out.println("5th box is not ready ");
							return false;
						}
		return true;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/**
	 * This method checks the 6th box if its finished.
	 * It takes the box number by number and checks it with the remaining numbers 
	 * from this box to see if theres a number more than once or a blank space
	 * @param grid the grid/table of the game 
	 * @return true if the box is correctly finished
	 */
	public boolean checkBoxSix(String[][] grid) {
		this.grid = grid;
		int i = 0;
		int j = 0;
		int cr = 1;
		int cc = 0;
		for (i = 5; i < 8; i++)
			for (j = 9; j < 12; j++)
				for (cr = 5; cr < 8; cr++)
					for (cc = j + 1; cc < 12; cc++)
						if (grid[i][j].equals(grid[cr][cc]) || grid[i][j].equals("   ")
								|| (grid[cr][cc].equals("   "))) {
							System.out.println("6th box is not ready ");
							return false;
						}
		return true;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/**
	 * This method checks the 7th box if its finished.
	 * It takes the box number by number and checks it with the remaining numbers 
	 * from this box to see if theres a number more than once or a blank space
	 * @param grid the grid/table of the game 
	 * @return true if the box is correctly finished
	 */
	public boolean checkBoxSeven(String[][] grid) {
		this.grid = grid;
		int i = 0;
		int j = 0;
		int cr = 1;
		int cc = 0;
		for (i = 9; i < 12; i++)
			for (j = 1; j < 4; j++)
				for (cr = 9; cr < 12; cr++)
					for (cc = j + 1; cc < 4; cc++)
						if (grid[i][j].equals(grid[cr][cc]) || grid[i][j].equals("   ")
								|| (grid[cr][cc].equals("   "))) {
							System.out.println("7th box is not ready ");
							return false;
						}
		return true;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/**
	 * This method checks the 8th box if its finished.
	 * It takes the box number by number and checks it with the remaining numbers 
	 * from this box to see if theres a number more than once or a blank space
	 * @param grid the grid/table of the game 
	 * @return true if the box is correctly finished
	 */
	public boolean checkBoxEight(String[][] grid) {
		this.grid = grid;
		int i = 0;
		int j = 0;
		int cr = 1;
		int cc = 0;
		for (i = 9; i < 12; i++)
			for (j = 5; j < 8; j++)
				for (cr = 9; cr < 12; cr++)
					for (cc = j + 1; cc < 8; cc++)
						if (grid[i][j].equals(grid[cr][cc]) || grid[i][j].equals("   ")
								|| (grid[cr][cc].equals("   "))) {
							System.out.println("8th box is not ready ");
							return false;
						}
		return true;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/**
	 * This method checks the 9th box if its finished.
	 * It takes the box number by number and checks it with the remaining numbers 
	 * from this box to see if theres a number more than once or a blank space
	 * @param grid the grid/table of the game 
	 * @return true if the box is correctly finished
	 */
	public boolean checkBoxNine(String[][] grid) {
		this.grid = grid;
		int i = 0;
		int j = 0;
		int cr = 1;
		int cc = 0;
		for (i = 9; i < 12; i++)
			for (j = 9; j < 12; j++)
				for (cr = 9; cr < 12; cr++)
					for (cc = j + 1; cc < 12; cc++)
						if (grid[i][j].equals(grid[cr][cc]) || grid[i][j].equals("   ")
								|| (grid[cr][cc].equals("   "))) {
							System.out.println("9th box is not ready ");
							return false;
						}
		return true;
	}
}