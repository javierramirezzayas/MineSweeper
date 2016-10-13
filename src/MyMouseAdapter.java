import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {

	public void mousePressed(MouseEvent e) {
		//This method is for when mouse is pressed.
		switch (e.getButton()) {
		case 1:		
			//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1) ) {
				//Had pressed outside
				//Do nothing
			} else {
				// If mouse is pressed inside the playable grid then return mousePressed true.
				myPanel.mousePressed = true;
			}
			
			myPanel.repaint();


			break;
			
		case 3:		
			//Right mouse button
			Component cFlag = e.getComponent();
			while (!(cFlag instanceof JFrame)) {
				c = cFlag.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrameFlag = (JFrame) cFlag;
			MyPanel myPanelFlag = (MyPanel) myFrameFlag.getContentPane().getComponent(0);
			Insets myInsetsFlag = myFrameFlag.getInsets();
			int x1Flag = myInsetsFlag.left;
			int y1Flag = myInsetsFlag.top;
			e.translatePoint(-x1Flag, -y1Flag);
			int xFlag = e.getX();
			int yFlag = e.getY();
			myPanelFlag.x = xFlag;
			myPanelFlag.y = yFlag;
			myPanelFlag.mouseDownGridX = myPanelFlag.getGridX(xFlag, yFlag);
			myPanelFlag.mouseDownGridY = myPanelFlag.getGridY(xFlag, yFlag);
			myPanelFlag.repaint();

			break;
			
		default:    
			//Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	
	
	public void mouseReleased(MouseEvent e) {
		// This method is for when mouse is released.
		switch (e.getButton()) {
		case 1:		
			//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			
			myPanel.mousePressed = false; // If by any means, mouse is released set mousePressed false.
			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1) ) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed

						//On the grid other than on the left column and on the top row:
						if(MyPanel.booleanArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] 
							&&( MyPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]==Color.WHITE)){
							// If mouse is pressed on the grid, the grid is uncovered and is a mine, 
							// then paint the grid black. 
							MyPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
							myPanel.repaint();	
							for(int i=0;i<9;i++){
								for(int j= 0;j<9;j++){
									if(MyPanel.booleanArray[i][j]){
										// If the first mine that is uncovered is black, 
										// then paint the remaining mines black.
										MyPanel.colorArray[i][j] = Color.BLACK;	
									}
								}	
							}
							myPanel.repaint();
						}
						else if(MyPanel.numbersArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == 0){
							// If the selected grid does not have any mines adjacent then call the emptyGrid Method.
							MineSweeperLogic.emptyGrid(myPanel.mouseDownGridX,myPanel.mouseDownGridY);
							myPanel.repaint();
						}
						else if(MyPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]==Color.WHITE
								&& MyPanel.numbersArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] > 0){
							// If the selected grid is covered, is not a bomb and has mines adjacent to it, 
							// then uncover the grid.
							MyPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.LIGHT_GRAY;
							MyPanel.notMines++;
							myPanel.repaint();
						}
						
						
						if(MineSweeperLogic.playerLost(myPanel.mouseDownGridX, myPanel.mouseDownGridY) 
								|| MineSweeperLogic.playerWon(myPanel.mouseDownGridX, myPanel.mouseDownGridY)){
							//If player lost or won the game:
							if(MineSweeperLogic.playerLost(myPanel.mouseDownGridX, myPanel.mouseDownGridY)){
								//If the player lost then call promptUser method.
								MineSweeperLogic.promptUser("SORRY, YOU LOST"
										+ "  Would you like to play again?", "GAME OVER!");
							}
							else{
								// If the player won then call promptUser method.
								MineSweeperLogic.promptUser("CONGRATULATIONS! YOU WON!"
										+ "  Would you like to play again?", "CONGRATULATIONS!");
							}
						}
					}
				}
				myPanel.repaint();
			}
			break;
			
		case 3:		
			//Right mouse button
			//Do nothing
			Component cFlag = e.getComponent();
			while (!(cFlag  instanceof JFrame)) {
				c = cFlag .getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrameFlag = (JFrame)cFlag ;
			MyPanel myPanelFlag  = (MyPanel) myFrameFlag .getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsetsFlag  = myFrameFlag .getInsets();
			int x1Flag  = myInsetsFlag .left;
			int y1Flag  = myInsetsFlag .top;
			e.translatePoint(-x1Flag, -y1Flag);
			int xFlag  = e.getX();
			int yFlag  = e.getY();
			myPanelFlag.x = xFlag ;
			myPanelFlag.y = yFlag ;
			int gridXFlag  = myPanelFlag.getGridX(xFlag, yFlag);
			int gridYFlag  = myPanelFlag.getGridY(xFlag, yFlag);

			if ((myPanelFlag.mouseDownGridX == -1) || (myPanelFlag.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridXFlag == -1) || (gridYFlag == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanelFlag.mouseDownGridX != gridXFlag ) || (myPanelFlag.mouseDownGridY != gridYFlag )) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						if(MyPanel.colorArray[myPanelFlag.mouseDownGridX][myPanelFlag.mouseDownGridY]==Color.WHITE && MyPanel.flags>0){
							// If the selected grid is covered and there are flags available, 
							// paint the grid red(indicating that there is a possible mine), 
							// also decrease the counter.
							MyPanel.colorArray[myPanelFlag.mouseDownGridX][myPanelFlag.mouseDownGridY] = Color.RED;
							myPanelFlag.repaint();
							MyPanel.flags--;
						}
						else if(MyPanel.colorArray[myPanelFlag.mouseDownGridX][myPanelFlag.mouseDownGridY]==Color.RED){
							// If the grid already has a flag (is red) then take the flag out and store it in the counter. 
							MyPanel.colorArray[myPanelFlag.mouseDownGridX][myPanelFlag.mouseDownGridY] = Color.WHITE;
							myPanelFlag.repaint();
							MyPanel.flags++;
						}
					}
				}
			}
			break;
			
		default:    
			//Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}



