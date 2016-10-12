import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JOptionPane;

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

	
		
	
	//-------------addded javier
//	public static void checkEmptyGrid(int gridX, int gridY){ //necesita pintar
//
//		for(int x = gridX-1; x <= gridX+1 ; x++){
//			for(int y = gridY-1; y <= gridY+1 ; y++){
//				if (!(x == -1 || y == -1 || x >= TOTAL_COLUMNS || y >= TOTAL_ROWS) 
//						&& (MyPanel.numbersArray[x][y]>=0) && !(MyPanel.booleanArray[x][y])){
//					Color newColor = Color.LIGHT_GRAY;
//					colorArray[x][y] = newColor;
//					MyPanel.notMines++;
//					if((MyPanel.numbersArray[x][y]==0) && !(MyPanel.booleanArray[x][y])){
//						checkEmptyGrid(x, y);
//					}
//				}
//			}
		//}
	//}
	//--------------added javier

	public static boolean  playerWon(int mouseDownGridX, int mouseDownGridY){
		boolean status = false;
		if(mouseDownGridX!=-1 && mouseDownGridY!=-1){
		if(notMines == ((TOTAL_COLUMNS*TOTAL_ROWS)-10)){
			//g.setColor(Color.MAGENTA);
			//g.drawString("WINNER!", getHeight()/2, getHeight()/2);
			//Main.reset();
			status =  true;//gano

		}}
		return status;
	}
	public static boolean  playerLost(int mouseDownGridX, int mouseDownGridY){
		boolean status = false;
		if(mouseDownGridX!=-1 && mouseDownGridY!=-1){
			if(colorArray[mouseDownGridX][mouseDownGridY]==Color.BLACK){
				status = true;//perdio
			}}
		return status;
	}
	public static void likeToKeep(String theMessage, String titleMessage) {
		int dialogButton = 0;
		int dialogResult = JOptionPane.showConfirmDialog (null, theMessage, titleMessage,dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){
			Main.reset();
		}
		else{
			System.exit(0);
		}
	  }

}