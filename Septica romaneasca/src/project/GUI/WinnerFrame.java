package project.GUI;

import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinnerFrame extends JDialog{
	public WinnerFrame(final int whoWon){
		
		JLabel text;
		
		setSize(new Dimension( 500, 400));
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		JPanel decisionPanel = new JPanel();
		add(decisionPanel);
		if (whoWon == 0){
			text = new JLabel("Jocul s-a terminat la egalitate");
		}
		else
			text = new JLabel("Jucatorul " + whoWon + " a castigat");
		decisionPanel.add(text);
	}
}
