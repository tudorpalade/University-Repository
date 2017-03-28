package models;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Polinom {
	
	private List<Monom> polinomList = new ArrayList<>();
	
	public Polinom() {
	}
	
	public Polinom(final String expresiePolinom){
		spargerePolinom(expresiePolinom);
	}
	
	public List<Monom> getPolinomList(){
		return this.polinomList;
	}
	
	public String formatRezultat(){
		String rezultatFinal="";
		for (Monom monom : this.polinomList){
			String coeficient = String.valueOf(monom.getCoeficient());
			if (monom.getGrad() > 1)
				rezultatFinal += gradMaiMare1(this,monom);
			else if (monom.getGrad()==0)
					if (this.polinomList.indexOf(monom) == 0)
						 rezultatFinal += (coeficient);
					else
						if (monom.getCoeficient() > 0)
							rezultatFinal += ("+"+coeficient);
						else
							rezultatFinal += (coeficient);
			 else if (monom.getGrad() ==1)
				 rezultatFinal += grad1(this,monom);		
		}
		return rezultatFinal;
	}
	
	
	
	private String grad1(Polinom polinom, Monom monom) {
		String coeficient = String.valueOf(monom.getCoeficient());
		String rezultatFinal = "";
		if (this.polinomList.indexOf(monom) == 0){
			if (monom.getCoeficient() == 1 || monom.getCoeficient() == -1){
				if (monom.getCoeficient() == 1){
					rezultatFinal += ("x");
				} else rezultatFinal += ("-x");
			} else rezultatFinal += (coeficient+"x");
		} else {
			if (monom.getCoeficient() == 1 || monom.getCoeficient() == -1){
				if (monom.getCoeficient() == 1){
					rezultatFinal += ("+x");
				} else rezultatFinal += ("-x");
			} else if (monom.getCoeficient() >1){
				rezultatFinal += ("+"+coeficient+"x");
			}
				else if (monom.getCoeficient() <-1 ){
					rezultatFinal +=(coeficient+"x");
			}
				else if (monom.getCoeficient() >-1 && monom.getCoeficient() <1){
					if (monom.getCoeficient() >-1 && monom.getCoeficient() <0)
						rezultatFinal +=(coeficient+"x");
					else
						rezultatFinal +=("+"+coeficient+"x");
				}
		}
		return rezultatFinal;
	}

	private String gradMaiMare1(Polinom polinom, Monom monom) {
		String coeficient = String.valueOf(monom.getCoeficient());
		String grad = String.valueOf(monom.getGrad());
		String rezultatFinal = "";
		if (polinom.polinomList.indexOf(monom) == 0){
			if (monom.getCoeficient() == 1 || monom.getCoeficient() == -1){
				if (monom.getCoeficient() == 1){
					rezultatFinal += ("x^"+grad);
				} else rezultatFinal += ("-x^"+grad);
			} else rezultatFinal += (coeficient+"x^"+grad);
		} else {
			if (monom.getCoeficient() == 1 || monom.getCoeficient() == -1){
				if (monom.getCoeficient() == 1){
					rezultatFinal += ("+x^"+grad);
				} else rezultatFinal += ("-x^"+grad);
			} else if (monom.getCoeficient() >1){
				rezultatFinal += ("+"+coeficient+"x^"+grad);
			}
				else if (monom.getCoeficient() <-1 ){
					rezultatFinal +=(coeficient+"x^"+grad);
			}
				else if (monom.getCoeficient() >-1 && monom.getCoeficient() <1){
					if (monom.getCoeficient() >-1 && monom.getCoeficient() <0)
						rezultatFinal +=(coeficient+"x");
					else
						rezultatFinal +=("+"+coeficient+"x");
				}
		}
		return rezultatFinal;
	}

	public int getGradMax(){
		int gradMax = 0;
		for (Monom monom : this.polinomList){
			if (monom.getGrad() > gradMax)
				gradMax = monom.getGrad();
		}
		return gradMax;
	}
	
	private void spargerePolinom(final String expresiePolinom) {
		String input = expresiePolinom;
		String pattern = "([+-]?\\d*\\.?\\d*)[xX](\\^(\\d+))?|([+-]?\\d+\\.?\\d*)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher( input );
		while (m.find()) {
			if (m.group(4)!=null){
				polinomList.add(new Monom(Double.parseDouble(m.group(4)),0));
			}
			else {
				if (m.group(1).equals("-") || m.group(1).equals("") || m.group(1).equals("+")){
					if (m.group(1).equals("-")){
						if (m.group(2)==null)
							polinomList.add(new Monom(-1,1));
						else 
							polinomList.add(new Monom(-1,Integer.parseInt(m.group(3))));
					}
					else {
						if (m.group(2)==null)
							polinomList.add(new Monom(1,1));
						else 
							polinomList.add(new Monom(1,Integer.parseInt(m.group(3))));
					}
				}
				else{
					if (m.group(2)==null)
						polinomList.add(new Monom(Double.parseDouble(m.group(1)),1));
					else 
						polinomList.add(new Monom(Double.parseDouble(m.group(1)),Integer.parseInt(m.group(3))));
				}
			}
		}
	}
}
