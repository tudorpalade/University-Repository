package project;
import java.io.IOException;


import project.GUI.Controller;
import project.GUI.Play;
import project.GUI.Table;



public class JMain {

	public static void main(String[] args) throws IOException {
				Table table=new Table();
				table.setVisible(true);
				Play play = new Play(table);
				Controller controller = new Controller(play,table);
	} 
}
