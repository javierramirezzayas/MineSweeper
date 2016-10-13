import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MineSweeperLogic extends MyPanel{


	public static int squareProperty(int gridX, int gridY){
		int squareCounter = 0;

		for(int i=gridX-1;i<=gridX+1;i++){
			for(int j=gridY-1;j<=gridY+1;j++){
				if(i==gridX && j==gridY && booleanArray[i][j]){
					return squareCounter = -1;

				}
				else
					if(!(i==gridX && j==gridY)&& !(i == -1 || j == -1 || i >= TOTAL_COLUMNS || j >= TOTAL_ROWS)
							&& booleanArray[i][j]==true){
						squareCounter++;
					}

			}
		}

		return squareCounter;
	}


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
		Toolkit.getDefaultToolkit().beep();
		int dialogResult = JOptionPane.showConfirmDialog (null, theMessage, titleMessage,dialogButton);
		if(dialogResult == JOptionPane.YES_OPTION){
			Main.reset();
		}
		else{
			System.exit(0);
		}
	}

	//--------------added javier(12/10/2016)
	public static void realcheckEmptyGrid(int gridX, int gridY){

		System.out.println("gridX = "+gridX);
		System.out.println("gridY = "+gridY);

		
		if (!(gridX <= -1 || gridY <= -1 || gridX >= TOTAL_COLUMNS || gridY >= TOTAL_ROWS) 
				&& MyPanel.numbersArray[gridX][gridY]==0 
				&& MyPanel.colorArray[gridX][gridY] == Color.WHITE
				&& MyPanel.booleanArray[gridX][gridY]==false){
			
			System.out.println("CONDITIONS ARE MET");
			
			colorArray[gridX][gridY] = Color.LIGHT_GRAY;
			MyPanel.notMines++;
			
			
			
			for(int i = gridX-1; i <= gridX + 1; i++ ){
				for(int j = gridY-1; j <= gridY + 1; j++){
					if(!((i==gridX) && (j==gridY))){
						realcheckEmptyGrid(i, j);
					}
				}
			}

		}
		else if(!(gridX <= -1 || gridY <= -1 || gridX >= TOTAL_COLUMNS || gridY >= TOTAL_ROWS) 
				&& MyPanel.numbersArray[gridX][gridY]>0 
				&& MyPanel.colorArray[gridX][gridY] == Color.WHITE
				&& MyPanel.booleanArray[gridX][gridY]==false){
			colorArray[gridX][gridY] = Color.LIGHT_GRAY;;
			MyPanel.notMines++;
		}
		else{
		System.out.println("CONDITIONS ARE NOT MET");
		}
		
	}


	//--------------added Javier(12/10/2016)



}