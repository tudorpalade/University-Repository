package unitTests;

import static org.junit.Assert.*;

import models.Operatii;
import models.Polinom;

import org.junit.Test;

public class InmultireTest {

	@Test
	public void test() {
		Polinom polinom1 = new Polinom("x^3+2x-1");
		Polinom polinom2 = new Polinom("x^5+3x");
		Polinom pRezultat = Operatii.inmultire(polinom1,polinom2);
		String output = pRezultat.formatRezultat();
		assertEquals("x^8+2.0x^6-x^5+3.0x^4+6.0x^2-3.0x",output);
	}

}
