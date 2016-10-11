import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JFrame;
public class MyMouseAdapter extends MouseAdapter {

	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
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
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
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
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
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
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
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
						if(MyPanel.booleanArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] &&( myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]==Color.WHITE)){

							Color newColor =Color.BLACK;
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();	
							for(int i=0;i<9;i++){
								for(int j= 0;j<9;j++){
									if(MyPanel.booleanArray[i][j]){
										myPanel.colorArray[i][j]=newColor;
										
								}
							}	
						}
							
							myPanel.repaint();
						}
						else if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY]==Color.WHITE){
							Color newColor =Color.LIGHT_GRAY;
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							MyPanel.notMines++;
							System.out.println(MyPanel.notMines);
							System.out.println("Mines" + MyPanel.mines);
							myPanel.repaint();

							//--- added by javier
							int number = MineSweeperLogic.squareProperty(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
							MyPanel.numbersArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = number;
							//--- added by javier
						}



					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
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
						if(myPanelFlag.colorArray[myPanelFlag.mouseDownGridX][myPanelFlag.mouseDownGridY]==Color.WHITE){
							Color newColor =Color.RED;
							myPanelFlag.colorArray[myPanelFlag.mouseDownGridX][myPanelFlag.mouseDownGridY] = newColor;
							myPanelFlag.repaint();
						}
						else if(myPanelFlag.colorArray[myPanelFlag.mouseDownGridX][myPanelFlag.mouseDownGridY]==Color.RED){
							Color newColor =Color.WHITE;
							myPanelFlag.colorArray[myPanelFlag.mouseDownGridX][myPanelFlag.mouseDownGridY] = newColor;
							myPanelFlag.repaint();
						}


					}


				}

			}


			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}



