package unitTests;

import static org.junit.Assert.*;

import models.Operatii;
import models.Polinom;

import org.junit.Test;

public class ScadereTest {

	@Test
	public void test() {
		Polinom polinom1 = new Polinom("x^3+2x-1");
		Polinom polinom2 = new Polinom("x^5+3x");
		Polinom pRezultat = Operatii.scadere(polinom1,polinom2);
		String output = pRezultat.formatRezultat();
		assertEquals("-x^5+x^3-x-1.0",output);
	}

}
