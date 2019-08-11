import java.awt.BorderLayout;

import javax.swing.*;

/**
 * GUI class to display the board. Extends JFrame so so instance of JFrame isnt needed
 * @author jordi
 *
 */
public class GUI extends JFrame { 
	
	JFrame frame;
	
	public GUI(){
		initializeGUI();
	}
	
	private void initializeGUI(){
		setTitle("Cluedo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,576);
		setLocationRelativeTo(null);	//displays in the middle of the display
		
		drawGame();
	}
	
	private void drawGame() {
		JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Game");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
        
        getContentPane().add(BorderLayout.NORTH, mb);
        setVisible(true); //makes the window visible
	}
	
	public static void main(String args[]){
		new GUI();
	}
}
