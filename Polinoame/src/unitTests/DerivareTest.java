package unitTests;

import static org.junit.Assert.*;

import models.Operatii;
import models.Polinom;

import org.junit.Test;

public class DerivareTest {

	@Test
	public void test() {
		Polinom polinom1 = new Polinom("x^3+2x-1");
		Polinom pRezultat = Operatii.derivare(polinom1);
		String output = pRezultat.formatRezultat();
		assertEquals("3.0x^2+2.0",output);
	}

}
