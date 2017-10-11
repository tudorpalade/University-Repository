package GUI;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.Board;
import board.Tile;
import piece.Piece;

public class BoardGUI extends JFrame{
	
	List<JLabel> labelList = new ArrayList<>();
	private JLabel textBox = new JLabel("White player`s turn");
	
	public BoardGUI() {
		setSize(686, 709);
		setTitle("Chess");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		createView();
		
	}
	
	public void createView() {
		getContentPane().removeAll();
		JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(128,64,0));
        createLabels(mainPanel); 
		getContentPane().add(mainPanel);	
		validate();
		
		}
	
	
	public void populateBoard(Board board) {
		for (Map.Entry<Tile, Piece> entry : board.getBoard().entrySet())
		{
			if (entry.getValue() != null) {
				labelList.get(entry.getKey().getTileNumber()).setIcon(entry.getValue().getIcon());
			}
		    
		}
		
		
	}

	public void createLabels(JPanel mainPanel) {
		labelList = new ArrayList<>();
		boolean sem=false;
		int startX = 60;
		int startY = 60;
		int x = 60;
		int y = 60;
		int j = 0;
		for (int i=0;i<64;i++) {
			 JLabel label = new JLabel(); 
			 label.setBounds(x , y , 70, 70);
			 if (sem == true) {
				 label.setBackground(new Color(209,139,71)); // #D18B47
				 label.setOpaque(true);
				 sem=!sem;
			 }
			 else {
				 label.setBackground(new Color(255,206,158));  //;#FFCE9E
				 label.setOpaque(true);
				 sem = !sem;
			 }
			 labelList.add(label);
			 mainPanel.add(label);
			 if ( (i == 7*(j+1) +j )) {
				 x = startX;
				 y = startY + 70 *( i / 7);
				 j=j+1;
				 sem = !sem;
			 }
			 else {
				 x = x+70;
			 }	 
		}
		textBox.setBounds(200, 5, 380, 50);
		textBox.setFont (textBox.getFont ().deriveFont (30.0f));
		textBox.setForeground(Color.white);
		mainPanel.add(textBox);
	}
	
	public void addLabelListener(MouseListener handler1){
		for (JLabel jLabel : labelList) {
			jLabel.addMouseListener(handler1);
		}
	}

	public void refresh(Board board) {
		createView();
		populateBoard(board);
	}
	
	public void setTextBox(String string) {
		this.textBox.setText(string);
	}
	public JLabel getTextBox() {
		return this.textBox;
	}
}
