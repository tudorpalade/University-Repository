package unitTests;

import static org.junit.Assert.*;

import models.Operatii;
import models.Polinom;

import org.junit.Test;

public class IntegrareTest {

	@Test
	public void test() {
		Polinom polinom1 = new Polinom("x^3+2x-1");
		Polinom pRezultat = Operatii.integrare(polinom1);
		String output = pRezultat.formatRezultat();
		assertEquals("0.25x^4+x^2-x",output);
	}

}
