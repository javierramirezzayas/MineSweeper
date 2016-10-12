import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 25;
	private static final int GRID_Y = 25;
	private static final int INNER_CELL_SIZE = 29;
	public static final int TOTAL_COLUMNS = 9;
	public static final int TOTAL_ROWS = 9;  
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	public static Color[][] colorArray = new Color[TOTAL_COLUMNS][TOTAL_ROWS];

	public static int mines = 0;
	public static int notMines = 0;
	public Random booleanDecider = new Random();
	public static boolean[][] booleanArray = new boolean[TOTAL_COLUMNS][TOTAL_ROWS];
	public static  int[][] numbersArray = new int[TOTAL_COLUMNS][TOTAL_ROWS];


	public MyPanel() {   //This is the constructor... this code runs first to initialize

		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}

		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				colorArray[x][y] = Color.WHITE;
				booleanArray[x][y] = false;
			}
		}
		while (mines<10){
			int x = booleanDecider.nextInt(9);
			System.out.println("x: "+x);
			int y = booleanDecider.nextInt(9);
			System.out.println("y: "+ y);
			if(!(booleanArray[x][y])){
				booleanArray[x][y] = true;
				mines++;
			}
		}
		
		//Setting Mine Indicators (numbers)
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   //The rest of the grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				MyPanel.numbersArray[x][y] = MineSweeperLogic.squareProperty(x,y);
			}
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right - 1;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;

		//Paint the background
		Color darkBlue = new Color(0,0,128);
		g.setColor(darkBlue);
		g.fillRect(x1, y1, width + 1, height + 1);

		//Draw the grid minus the bottom row (which has only one cell)
		//By default, the grid will be 10x10 (see above: TOTAL_COLUMNS and TOTAL_ROWS) 
		g.setColor(Color.BLACK);
		for (int y = 0; y <= TOTAL_ROWS ; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS; x++) {
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS )));
		}

		//Small square for bombs
		g.setColor(Color.WHITE);
		g.fillRect(GRID_X+1,(int) (getHeight()*0.85), INNER_CELL_SIZE*4, INNER_CELL_SIZE);
		g.setColor(Color.BLACK);
		g.fillRect(GRID_X+5,(int)(getHeight()*0.85)+ 4, 20, 20);
		g.drawString("Bombs: " + mines, GRID_X+28, (int)(getHeight()*0.90));


		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS; y++) {
				if ((x == 0) || (y != TOTAL_ROWS)) {
					Color c = colorArray[x][y];
					g.setColor(c);
					g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);

					// If square is pressed and it has an indicator (number) that is not 0: Paint the indicator  
					if ((colorArray[x][y] == Color.LIGHT_GRAY) && (numbersArray[x][y] != 0)){
						g.setColor(Color.RED);
						g.drawString(String.valueOf(numbersArray[x][y]), (x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 11),
								(y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 20));
					}
				}
			}	
		}
		if(mouseDownGridX!=-1 && mouseDownGridY!=-1){
			if(colorArray[mouseDownGridX][mouseDownGridY]==Color.BLACK){
				g.setColor(Color.ORANGE);
				g.drawString("GAME OVER!", getHeight()/2, getWidth()/2);
			}
			else {
				if(notMines == ((TOTAL_COLUMNS*TOTAL_ROWS)-mines)){
					g.setColor(Color.MAGENTA);
					g.drawString("WINNER!", getHeight()/2, getHeight()/2);
				}
			}
		}
	}



	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;

		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}

		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);

		if (x == 0 && y == TOTAL_ROWS-1 ) {    //The lower left extra cell
			return x;
		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}

		return x;
	}
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
			return y;
		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}
}

