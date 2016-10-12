import java.awt.Color;

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

//	public static boolean playerStatus(int gridX, int gridY){
//		boolean status = false;
//		if(booleanArray[gridX][gridY] && notMines == ((TOTAL_COLUMNS*TOTAL_ROWS)-mines)){
//			status = true;
//		}
//		else if (booleanArray[gridX][gridY]==true){
//			System.out.println("Game Over!");
//		}
//		return status;
//	}

	//-------------addded javier
	public static void checkEmptyGrid(int gridX, int gridY){ //necesita pintar

		for(int x = gridX-1; x <= gridX+1 ; x++){
			for(int y = gridY-1; y <= gridY+1 ; y++){
				if (!(x == -1 || y == -1 || x >= TOTAL_COLUMNS || y >= TOTAL_ROWS) 
						&& (MyPanel.numbersArray[x][y]>=0) && !(MyPanel.booleanArray[x][y])){
					Color newColor = Color.LIGHT_GRAY;
					colorArray[x][y] = newColor;
					if((MyPanel.numbersArray[x][y]==0) && !(MyPanel.booleanArray[x][y])){
						checkEmptyGrid(x, y);
					}
				}
			}
		}
	}
	//--------------added javier


}