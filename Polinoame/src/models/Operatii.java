package models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operatii {
	public static Monom adunareMonom(final Monom m1, final Monom m2){
		return new Monom(m1.getCoeficient() + m2.getCoeficient(), m1.getGrad());
	}
	public static Monom scadereMonom(final Monom m1, final Monom m2){
		return new Monom(m1.getCoeficient() - m2.getCoeficient(), m1.getGrad());
	}
	public static Monom inmultireMonom(final Monom m1, final Monom m2){
		return new Monom(m1.getCoeficient() * m2.getCoeficient(), m1.getGrad() + m2.getGrad());
	}
	public static Monom impartireMonom(final Monom m1, final Monom m2){
		return new Monom(m1.getCoeficient() / m2.getCoeficient(), m1.getGrad() - m2.getGrad());
	}
	public static Polinom adunare(final Polinom p1, final Polinom p2){
		int sem=0;
		Polinom pRezultat = new Polinom();
	    for (Monom item : p1.getPolinomList())
	    	pRezultat.getPolinomList().add(item);
		for (Monom monomP2 : p2.getPolinomList()){
			sem=0;
			for (Monom monomPRezultat : pRezultat.getPolinomList()){
				if (monomP2.getGrad() == monomPRezultat.getGrad()){	
					Monom aux = adunareMonom(monomPRezultat,monomP2);
					pRezultat.getPolinomList().set(pRezultat.getPolinomList().indexOf(monomPRezultat), aux);
					sem=1;
					break;
				}
			}
			if (sem == 0)
				pRezultat.getPolinomList().add(new Monom(monomP2.getCoeficient(),monomP2.getGrad()));
		}
		return sortareDupaGrad(pRezultat);
	}
	
	public static Polinom scadere(final Polinom p1, final Polinom p2){
		int sem=0;
		Polinom pRezultat = new Polinom();
	    for (Monom item : p1.getPolinomList())
	    	pRezultat.getPolinomList().add(item);
		for (Monom monomP2 : p2.getPolinomList()){
			sem=0;
			for (Monom monomPRezultat : pRezultat.getPolinomList()){
				if (monomP2.getGrad() == monomPRezultat.getGrad()){	
					Monom aux = scadereMonom(monomPRezultat,monomP2);
					pRezultat.getPolinomList().set(pRezultat.getPolinomList().indexOf(monomPRezultat), aux);
					sem=1;
					break;
				}
			}
			if (sem == 0){
				double coef = monomP2.getCoeficient() * (-1);
				pRezultat.getPolinomList().add(new Monom(coef,monomP2.getGrad()));
			}
		}
		return sortareDupaGrad(pRezultat);
	}
	
	public static Polinom inmultire(final Polinom p1,final Polinom p2){
		int sem = 0;
		Polinom pRezultat = new Polinom();
		for (Monom monomP1 : p1.getPolinomList()){
			for (Monom monomP2 : p2.getPolinomList()){
				sem=0;
				Monom monomAux = inmultireMonom(monomP1,monomP2);
				for (Monom monomPRezultat : pRezultat.getPolinomList()){
					if (monomPRezultat.getGrad() == monomAux.getGrad()){
						Monom aux = adunareMonom(monomPRezultat,monomAux);
						pRezultat.getPolinomList().set(pRezultat.getPolinomList().indexOf(monomPRezultat), aux);
						sem = 1;
						break;
					}
				}
				if (sem == 0)
					pRezultat.getPolinomList().add(new Monom(monomAux.getCoeficient(),monomAux.getGrad()));
			}
		}
		return sortareDupaGrad(pRezultat);
	}
	
	public static List<Polinom> impartire(Polinom D, Polinom I){
		List<Polinom> listaPolinoame = new ArrayList<>();
		sortareDupaGrad(D);
		sortareDupaGrad(I);
		Polinom C = new Polinom();
		if (I.getGradMax() == 0) {
				while (!D.getPolinomList().isEmpty()){
					Monom cat = impartireMonom(D.getPolinomList().get(0),I.getPolinomList().get(0));
					Polinom ajutator = new Polinom();
					ajutator.getPolinomList().add(cat);
					Polinom aux = inmultire(I,ajutator);
					C.getPolinomList().add(cat);
					D = scadere(D,aux);
					sortareDupaGrad(D);
				}
		}
		else if (D.getGradMax() >= I.getGradMax()){
			while ((D.getGradMax() >= I.getGradMax()) && (D.getGradMax()!=0) ){
				Monom cat = impartireMonom(D.getPolinomList().get(0),I.getPolinomList().get(0));
				Polinom ajutator = new Polinom();
				ajutator.getPolinomList().add(cat);
				Polinom aux = inmultire(I,ajutator);
				C.getPolinomList().add(cat);
				D = scadere(D,aux);
				sortareDupaGrad(D);
			}
		}
		listaPolinoame.add(C);
		listaPolinoame.add(D);
		return listaPolinoame;
	}
	
	public static Polinom integrare(final Polinom p1){
		Polinom pRezultat = new Polinom();
		for(Monom monom : p1.getPolinomList()){
			if (monom.getGrad() == 0){
				pRezultat.getPolinomList().add(new Monom(monom.getCoeficient(),1));
			}
			else{
				int nouGrad = monom.getGrad()+1;
				Monom aux = new Monom(monom.getCoeficient() / nouGrad , nouGrad);
				pRezultat.getPolinomList().add(aux);
			}
		}
		return sortareDupaGrad(pRezultat);
	}
	
	public static Polinom derivare(final Polinom p1){
		Polinom pRezultat = new Polinom();
		for(Monom monom : p1.getPolinomList()){
			if (monom.getGrad() != 0){
				Monom aux = new Monom(monom.getCoeficient() * monom.getGrad(), monom.getGrad()-1);
				pRezultat.getPolinomList().add(aux);
			}
		}
		return sortareDupaGrad(pRezultat);
	}
	public static Polinom sortareDupaGrad(final Polinom polinom){
		List<Monom> trash = new ArrayList<>();
		Collections.sort(polinom.getPolinomList());
		for (Monom monom : polinom.getPolinomList()){
			if (monom.getCoeficient() == 0)
				trash.add(monom);
		}
		polinom.getPolinomList().removeAll(trash);
		return polinom;
	}
}
