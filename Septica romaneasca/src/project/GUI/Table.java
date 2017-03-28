package project.GUI;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Table extends JFrame{
	public JLabel[] player1Card = new JLabel[4];
	public JLabel[] player2Card = new JLabel[4];
	public JLabel[] pileStack = new JLabel[8];
	public Button button = new Button("DEAL");
	public JLabel deck = new JLabel("DECK");
	public boolean allowDeal=true;
	public boolean[] allowCards = {true,true,true,true};
	public Table(){
		createView();
		
		setSize(1000, 600);
		setTitle("JCards");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public void createView() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.GREEN);
		mainPanel.add(setPlayer1Panel(),BorderLayout.PAGE_END);
		mainPanel.add(setPlayer2Panel(),BorderLayout.PAGE_START);
		mainPanel.add(setCenterPanel(),BorderLayout.CENTER);
		getContentPane().add(mainPanel);	
		}
	
	public JPanel setPlayer2Panel() {
		ImageIcon imageIcon = new ImageIcon("spate.png");
			for (int i=0;i<4;i++){                         //creem 8 etichete si le setam dimensiunile
				player2Card[i] = new JLabel("Cartea i");
				player2Card[i].setPreferredSize(new Dimension(61,86));
				player2Card[i].setIcon(imageIcon);
			}
			
			JPanel playerPanel = new JPanel(new FlowLayout());
			playerPanel.setBackground(Color.decode("#5bee15"));
			for(int i=0;i<4;i++)
				playerPanel.add(player2Card[i]);
			return playerPanel;
	}
	public JPanel setPlayer1Panel() {
		for (int i=0;i<4;i++){                         //creem 8 etichete si le setam dimensiunile
			player1Card[i] = new JLabel();
			player1Card[i].setPreferredSize(new Dimension(61,86));
		}
		JPanel playerPanel = new JPanel(new FlowLayout());
		playerPanel.setBackground(Color.decode("#5bee15"));
		for(int i=0;i<4;i++)
			playerPanel.add(player1Card[i]);
		return playerPanel;
}
	public JPanel setCenterPanel() {
		for (int i=0;i<8;i++){                         //creem 8 etichete si le setam dimensiunile
			pileStack[i] = new JLabel();
			pileStack[i].setPreferredSize(new Dimension(61,86));
		}
		JPanel pileStackPanel = new JPanel(new FlowLayout());  //adaugam etichetele in panel
		pileStackPanel.setBackground(Color.decode("#5bee15"));
		for (int i=0;i<8;i++){
			pileStackPanel.add(pileStack[i]);
		}
		
		deck.setPreferredSize(new Dimension(61,86));
		ImageIcon imageIcon = new ImageIcon("spate.png");
		deck.setIcon(imageIcon);
			
		JPanel centerPanel = new JPanel(new FlowLayout());
		centerPanel.setBackground(Color.decode("#5bee15"));
		centerPanel.add(pileStackPanel);
		centerPanel.add(button);
		centerPanel.add(deck);
		return centerPanel;	
	}
	
	public void addCardsListener(MouseListener handler1){
		player1Card[0].addMouseListener(handler1);
		player1Card[1].addMouseListener(handler1);
		player1Card[2].addMouseListener(handler1);
		player1Card[3].addMouseListener(handler1);
	}
	
	public void addDealListener(ActionListener listener){
		button.addActionListener(listener);
	}
	
	public JLabel getPlayer1Image(int i){
		return player1Card[i];
	}
	public JLabel getPlayer2Image(int i){
		return player2Card[i];
	}
	public void setAllowDeal(boolean b) {
		this.allowDeal = b;
		
	}
}
