import java.awt.BorderLayout;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;

/**
 * GUI class to display the board. Extends JFrame so so instance of JFrame isnt
 * needed
 * 
 * @author jordi
 *
 */
public class GUI extends JFrame implements ActionListener, WindowListener {

	JFrame frame;
	JMenuBar menuBar;
	JMenuItem menuBarOpen;
	JMenuItem menuBarSave;

	public GUI() {
		initializeGUI();
	}

	private void initializeGUI() {
		setTitle("Cluedo");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//Anonymous class to make sure user wants to close the window
		addWindowListener(this);
		
		setSize(1024, 576);
		setLocationRelativeTo(null); // displays in the middle of the display

		drawGame();
	}
	
	/**
	 * checks that the user wants to close the window with a yes or no window
	 */
	public void windowClosing(WindowEvent e) {
		int r = JOptionPane.showConfirmDialog(this, 
				new JLabel("Exit Cluedo?"), "Confirm Exit",
									JOptionPane.YES_NO_OPTION, 
									JOptionPane.QUESTION_MESSAGE);
		
		if (r == JOptionPane.YES_OPTION) 
			System.exit(0);
		
	}

	private void drawGame() {
		menuBar = new JMenuBar();
		JMenu m1 = new JMenu("FILE");
		JMenu m2 = new JMenu("Game");
		menuBar.add(m1);
		menuBar.add(m2);
		menuBarOpen = new JMenuItem("Open");
		menuBarOpen.addActionListener(this);
		menuBarSave = new JMenuItem("Save as");
		m1.add(menuBarOpen);
		m1.add(menuBarSave);

		getContentPane().add(BorderLayout.NORTH, menuBar);
		setVisible(true); // makes the window visible
	}

	public void actionPerformed(ActionEvent e) {
		// opens file in the file chooser
		if (e.getSource() == menuBarOpen) {
			menuBarOpenSelected();
		}
	}

	/**
	 * function used when open is selected in the menubar to open a file
	 */
	public void menuBarOpenSelected() {
		JFileChooser fileChooser = new JFileChooser();
		int openFile = fileChooser.showOpenDialog(this);
		if (openFile == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String extention = file.getName();
			if (!isTxtFile(extention)) {
				throw new Error("Not a txt file");
			}
			String selectedFile = file.getPath();
			try {
				BufferedReader sc = new BufferedReader(new FileReader(selectedFile));
				sc.close();
			} catch (Exception error) {
				System.out.println(error);
			}
		}
	}

	/**
	 * Checks whether a file is a txt file or not
	 * 
	 * @param fileString
	 * @return true if a txt file
	 */
	private boolean isTxtFile(String fileString) {
		int i = fileString.lastIndexOf('.');
		String extention = "";
		if (i != -1) { // checks there is a . int the file name
			if (i > 0 && i < fileString.length() - 1) {
				extention = fileString.substring(i + 1).toLowerCase();
			}
			System.out.println(extention);
			if (extention.equals("txt")) {
				return true;
			}
		}
		return false;
	}

	public static void main(String args[]) {
		new GUI();
	}
}
