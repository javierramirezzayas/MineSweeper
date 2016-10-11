import java.awt.Color;
import java.awt.Graphics;


public class MineSweeperLogic extends MyPanel{

	
	public static int squareProperty(int gridX, int gridY){
		int squareCounter = 0;
		for(int i=gridX-1;i<=gridX+1;i++){
			for(int j=gridY-1;j<=gridY+1;j++){
				if(!(i==gridX && j==gridY)&& !(i == -1 || j == -1 || i >= TOTAL_COLUMNS || j >= TOTAL_ROWS) && booleanArray[i][j]==true){
						squareCounter++;
				}
			}
		}
	
		return squareCounter;
	}
	
	public static boolean playerStatus(int gridX, int gridY){
		boolean status = false;
		if(booleanArray[gridX][gridY] && notMines == ((TOTAL_COLUMNS*TOTAL_ROWS)-mines)){
			status = true;
		}
		else if (booleanArray[gridX][gridY]==true){
			System.out.println("Game Over!");
		}
		return status;
	}
	
	
	

}