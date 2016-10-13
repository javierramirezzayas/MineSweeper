import javax.swing.JFrame;

public class Main {

	private static JFrame myFrame;
	private static MyPanel myPanel;

	public static void main(String[] args) {

		myFrame = new JFrame("MineSweeper");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setResizable(false);

		myFrame.setSize(323, 400);

		myPanel = new MyPanel();
		myPanel.newGame(); 
		myFrame.add(myPanel);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setLocationRelativeTo(null); 
		myFrame.setVisible(true);
	}

	//Method to dispose the current window and call main again.
	public static void reset(){
		myFrame.setVisible(false);
		myFrame.dispose();
		String[] args = new String[123];
		Main.main(args);
	}
}