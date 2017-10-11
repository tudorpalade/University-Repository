package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import piece.Piece;
import player.Player;

public class GraveyardFrame extends JFrame{
	public GraveyardFrame(final Player player) {
		setSize(300,300);
		setTitle("Graveyard Zone~ Watch out!!");
		setResizable(false);
	//	setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);    ///?
		setVisible(true);
		createView(player);
	}
	
	public void createView(Player player) {
		getContentPane().removeAll();
		JPanel mainPanel = new JPanel(new FlowLayout());
		mainPanel.setBackground(new Color(128,64,0));
		createLabels(player ,mainPanel);
		getContentPane().add(mainPanel);
		validate();
	}

	private void createLabels(Player player , JPanel mainPanel) {
		List<Piece> graveyard = player.getPlayerGraveyard();
		int sizeOfgraveyard = player.getPlayerGraveyard().size();
		
		for (int i = 0 ; i < sizeOfgraveyard ; i++) {
			JLabel label = new JLabel();
			label.setSize(60, 60);         //?
			label.setIcon(graveyard.get(i).getIcon());
			mainPanel.add(label);
		}
	}
}
