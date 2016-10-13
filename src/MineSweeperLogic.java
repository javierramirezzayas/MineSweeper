import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class MineSweeperLogic extends MyPanel{

	private static final long serialVersionUID = 1L;

	public static int checkIndicator(int gridX, int gridY){
		// This method checks if there are mines adjacent to the grid selected.
		// It takes in as parameters the two dimensions of the grid.
		// Returns the number of mines that are adjacent to the grid selected.

		int indicatorCounter = 0;

		for(int i=gridX-1;i<=gridX+1;i++){
			for(int j=gridY-1;j<=gridY+1;j++){
				if(i==gridX && j==gridY && booleanArray[i][j]){
					// If the selected grid is a mine stop searching and return -1.
					return indicatorCounter = -1;
				}
				else if(notOutOfBounds(i, j) && booleanArray[i][j]){
					// If the grid is not the selected grid, is not out of bounds and is a mine then increment the indicatorCounter. 
					indicatorCounter++;
				}
			}
		}
		return indicatorCounter;
	}

	public static void emptyGrid(int gridX, int gridY){
		// This method checks if the selected grid is empty. 
		// It takes in as parameters the two dimensions of the grid.
		// Is a void method, returns nothing. 

		if (notOutOfBounds(gridX, gridY) 
				&& numbersArray[gridX][gridY]==0 
				&& colorArray[gridX][gridY] == Color.WHITE
				&& !(booleanArray[gridX][gridY])){
			// If the selected grid is not out of bounds, has 0 mines adjacent to it, has not been uncovered 
			// and is not a mine, uncover the grid and increment the notMines counter. 
			
			colorArray[gridX][gridY] = Color.LIGHT_GRAY;
			MyPanel.notMines++;

			for(int i = gridX-1; i <= gridX + 1; i++ ){
				for(int j = gridY-1; j <= gridY + 1; j++){
					if(!((i==gridX) && (j==gridY))){
						// Calls the method again only for grids adjacent to the selected one.
						emptyGrid(i, j);
					}
				}
			}
		}
		else if(notOutOfBounds(gridX, gridY) 
				&& numbersArray[gridX][gridY]>0 
				&& colorArray[gridX][gridY] == Color.WHITE
				&& !(booleanArray[gridX][gridY])){
			// If the selected grid is not out of bounds, has mines adjacent to it, has not been uncovered 
			// and is not a mine, uncover the grid and increment the notMines counter. 
			colorArray[gridX][gridY] = Color.LIGHT_GRAY;;
			MyPanel.notMines++;
		}
	}

	public static boolean  playerWon(int gridX, int gridY){
		// This method decides if the player has won or not.
		// It takes in as parameters the two dimensions of the grid.
		// Returns true if the player has won, false otherwise. 
		
		boolean status = false;
		if(gridX!=-1 && gridY!=-1){
			if(notMines == ((TOTAL_COLUMNS*TOTAL_ROWS)-mines)){
				// If the number of notMines clicked is the same as the total of grids minus the number of bombs
				// Set status to true, which means that the player has won.
				status =  true;
			}
		}
		return status;
	}
	public static boolean  playerLost(int mouseDownGridX, int mouseDownGridY){
		// This method decides if the player has lost or not.
		// It takes in as parameters the two dimensions of the grid.
		// Returns true if the player has lost, false otherwise. 
		
		boolean status = false;
		if(mouseDownGridX!=-1 && mouseDownGridY!=-1){
			if(colorArray[mouseDownGridX][mouseDownGridY]==Color.BLACK){
				// If the grid selected is a mine (black color)
				// Set status to true, which means the player has lost.
				status = true;
			}
		}
		return status;
	}

	public static void promptUser(String theMessage, String titleMessage) {
		// This method prompts the user to keep playing or quit.
		// Takes as parameters two strings theMessage that will be asked and the title of the JOptionPane.
		// It is a void method, returns nothing.
		
		Toolkit.getDefaultToolkit().beep();
		int dialogResult = JOptionPane.showConfirmDialog (null, theMessage, titleMessage,0);
		if(dialogResult == JOptionPane.YES_OPTION){
			// If the user clicks the yes button to play again, then call the reset method.
			Main.reset();
		}
		else{
			// If the user does not click the yes button, exit the game.
			System.exit(0);
		}
	}
	
	public static boolean notOutOfBounds(int gridX, int gridY){
		// This method checks if the selected grid is NOT out of bounds.
		// It takes in as parameters the two dimensions of the grid.
		// It returns true if the selected grid is not out of bounds and false otherwise.
		
		if(!(gridX <= -1 || gridY <= -1 || gridX >= TOTAL_COLUMNS || gridY >= TOTAL_ROWS)){
			// If the selected grid is not out of bounds return true.
			return true;
		}
		else{
			// If the selected grid is out of bounds return false.
			return false;
		}
	}
}

 