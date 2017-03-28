package project.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DecisionTaker extends JDialog{
	public boolean decision;
	public DecisionTaker(){
		setSize(new Dimension( 200, 100));
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		JPanel decisionPanel = new JPanel();
		add(decisionPanel);
		JButton buttonYES = new JButton("YES");
		JButton buttonNO = new JButton("NO");
		JLabel text = new JLabel("Do you wish to continue?");
		decisionPanel.add(text);
		decisionPanel.add(buttonYES);
		decisionPanel.add(buttonNO);
		buttonYES.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				decision=true;
				dispose();
			}
		});
		buttonNO.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				decision=false;
				dispose();
			}
		});
	}
}
