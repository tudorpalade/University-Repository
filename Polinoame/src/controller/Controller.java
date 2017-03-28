package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


import models.Models;
import models.Operatii;
import models.Polinom;

import views.FrameImpartire;
import views.View;

public class Controller {
	
	private View view;
	private Models models;
	
	public Controller(View view, Models models){
		this.view = view;
		this.models = models;
		view.addListener(new Listener());
	}
	
	public class Listener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent event) {
			String pol1 = "";
			String pol2 = "";
			Polinom polinom1;
			Polinom polinom2;
			if (view.getPolinom1().getText().equals("") && view.getPolinom2().getText().equals("")){
			}
			else {
				if (!view.getPolinom1().getText().equals("") && !view.getPolinom2().getText().equals("")){
					pol1 = String.format("%s", view.getPolinom1().getText());
					polinom1 = new Polinom(pol1);
					pol2 = String.format("%s", view.getPolinom2().getText());
					polinom2 = new Polinom(pol2);
					if(event.getSource() == view.getAdunare()){
						if (!view.getPolinom1().getText().equals("") && !view.getPolinom2().getText().equals("")){
							Polinom pRezultat = Operatii.adunare(polinom1, polinom2);
							if (!pRezultat.getPolinomList().isEmpty())
								view.setRezultat(pRezultat.formatRezultat());
							else
								view.setRezultat("0");
						}
					}
					else if (event.getSource() == view.getScadere()){
						if (!view.getPolinom1().getText().equals("") && !view.getPolinom2().getText().equals("")){
							Polinom pRezultat = Operatii.scadere(polinom1, polinom2);
							if (!pRezultat.getPolinomList().isEmpty())
								view.setRezultat(pRezultat.formatRezultat());
							else
								view.setRezultat("0");
						}
					}
					else if (event.getSource() == view.getInmultire()){
						if (!view.getPolinom1().getText().equals("") && !view.getPolinom2().getText().equals("")){
							Polinom pRezultat = Operatii.inmultire(polinom1, polinom2);
							view.setRezultat(pRezultat.formatRezultat());
						}
					}
					else if (event.getSource() == view.getImpartire()){
						if (!view.getPolinom1().getText().equals("") && !view.getPolinom2().getText().equals("")){
							FrameImpartire frameImpartire = new FrameImpartire();
							frameImpartire.setVisible(true);
							
							List<Polinom> polinoameRezultate = Operatii.impartire(polinom1, polinom2);
							if (!polinoameRezultate.get(0).getPolinomList().isEmpty())
								frameImpartire.setCat(polinoameRezultate.get(0).formatRezultat());
							else
								frameImpartire.setCat("0");
							if (!polinoameRezultate.get(1).getPolinomList().isEmpty())
								frameImpartire.setRest(polinoameRezultate.get(1).formatRezultat());
							else
								frameImpartire.setRest("0");
						}
					}
				}
				else if(!view.getPolinom1().getText().equals("")){
							pol1 = String.format("%s", view.getPolinom1().getText());
							polinom1 = new Polinom(pol1);
							if (event.getSource() == view.getDerivare()){
								if (!view.getPolinom1().getText().equals("")){
									Polinom pRezultat = Operatii.derivare(polinom1);
									if (!pRezultat.getPolinomList().isEmpty())
										view.setRezultat(pRezultat.formatRezultat());
									else
										view.setRezultat("0");
								}
							}
							else if (event.getSource() == view.getIntegrare()){
								if (!view.getPolinom1().getText().equals("")){
									String aux ="";
									Polinom pRezultat = Operatii.integrare(polinom1);
									aux = pRezultat.formatRezultat();
									aux += (" +c");
									view.setRezultat(aux);
								}
							}
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}	
	}
}
