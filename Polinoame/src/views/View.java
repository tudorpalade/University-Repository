package views;

import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame{
	
	private JButton adunare;
	private JButton scadere;
	private JButton inmultire;
	private JButton impartire;
	private JButton derivare;
	private JButton integrare;
	private JTextField polinom1;
	private JTextField polinom2;
	private JTextField rezultat;
	
	public JButton getAdunare(){
		return adunare;
	}
	public JButton getScadere(){
		return scadere;
	}
	public JButton getInmultire(){
		return inmultire;
	}
	public JButton getImpartire(){
		return impartire;
	}
	public JButton getDerivare(){
		return derivare;
	}
	public JButton getIntegrare(){
		return integrare;
	}
	public JTextField getPolinom1(){
		return polinom1;
	}
	public JTextField getPolinom2(){
		return polinom2;
	}
	public void setRezultat(String rezultat){
		this.rezultat.setText(rezultat);
	}
	public View(){
		createView();
		setVisible(true);
		setSize(500, 400);
		setTitle("view");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void createView() {
		JLabel polinom1Label = new JLabel("polinom1:");
		JLabel polinom2Label = new JLabel("polinom2:");
		JLabel rezultatLabel = new JLabel("rezultat:");
		adunare = new JButton("ADUNA");
		scadere = new JButton("SCADE");
		inmultire = new JButton("INMULTESTE");
		impartire = new JButton("IMPARTE");
		derivare = new JButton("DERIVEAZA");
		integrare = new JButton("INTEGREAZA");
		JPanel mainPanel = new JPanel();
		polinom1 = new JTextField();
		polinom2 = new JTextField();
		rezultat = new JTextField();
		rezultat.setEditable(false);
		
		
		polinom1Label.setBounds(20, 10, 80, 20);
		polinom1.setBounds(90, 10, 140, 20);
		polinom2Label.setBounds(250, 10, 80, 20);
		polinom2.setBounds(320, 10, 140, 20);
		rezultatLabel.setBounds(20, 60, 80, 20);
		rezultat.setBounds(100, 50, 350, 40);
		adunare.setBounds(10, 100, 200, 70);
		scadere.setBounds(280, 100, 200, 70);
		inmultire.setBounds(10, 180, 200, 70);
		impartire.setBounds(280, 180, 200, 70);
		derivare.setBounds(10, 260, 200, 70);
		integrare.setBounds(280, 260, 200, 70);
		
		mainPanel.setLayout (null); 
		mainPanel.add(polinom1);
		mainPanel.add(polinom1Label);
		mainPanel.add(polinom2Label);
		mainPanel.add(polinom2);
		mainPanel.add(rezultatLabel);
		mainPanel.add(rezultat);
		mainPanel.add(adunare);
		mainPanel.add(scadere);
		mainPanel.add(inmultire);
		mainPanel.add(impartire);
		mainPanel.add(derivare);
		mainPanel.add(integrare);
		getContentPane().add(mainPanel);
	}
	public void addListener(MouseListener handler){
		adunare.addMouseListener(handler);
		scadere.addMouseListener(handler);
		inmultire.addMouseListener(handler);
		impartire.addMouseListener(handler);
		derivare.addMouseListener(handler);
		integrare.addMouseListener(handler);
	}
}
