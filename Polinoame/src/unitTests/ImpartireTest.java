package unitTests;

import static org.junit.Assert.*;

import java.util.List;

import models.Operatii;
import models.Polinom;

import org.junit.Test;

public class ImpartireTest {

	@Test
	public void test() {
		Polinom polinom1 = new Polinom("x^5+3x");
		Polinom polinom2 = new Polinom("x^3+2x-1");
		List<Polinom> pRezultate = Operatii.impartire(polinom1,polinom2);
		String cat = pRezultate.get(0).formatRezultat();
		String rest =  pRezultate.get(1).formatRezultat();
		assertEquals("x^2-2.0",cat);
		assertEquals("x^2+7.0x-2.0",rest);
	}

}
