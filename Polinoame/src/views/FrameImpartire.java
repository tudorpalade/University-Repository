package views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameImpartire extends JFrame{
	private JTextField cat;
	private JTextField rest;
	
	public void setCat(String cat){
		this.cat.setText(cat);
	}
	
	public void setRest(String rest){
		this.rest.setText(rest);
	}
	
	public FrameImpartire(){
		createView();
		setVisible(false);
		setSize(500, 200);
		setTitle("impartire");
		setResizable(false);
		setLocationRelativeTo(null);
	}

	private void createView() {
		JPanel mainPanel= new JPanel();
		JLabel catLabel = new JLabel("cat:");
		JLabel restLabel = new JLabel("rest:");
		
		cat = new JTextField();
		rest = new JTextField();
		
		cat.setEditable(false);
		rest.setEditable(false);
		
		catLabel.setBounds(20,20,80,20);
		cat.setBounds(110, 10, 350, 40);
		restLabel.setBounds(20,100,80,20);
		rest.setBounds(110, 90, 350, 40);
		
		mainPanel.setLayout (null); 
		mainPanel.add(catLabel);
		mainPanel.add(cat);
		mainPanel.add(restLabel);
		mainPanel.add(rest);
		
		getContentPane().add(mainPanel);
	}
}
