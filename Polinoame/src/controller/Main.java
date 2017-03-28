package controller;
import models.Models;
import views.View;

public class Main {

	public static void main(String[] args) {
		View view = new View();
		Models models = new Models();
		Controller controller = new Controller(view, models);
	}

}
