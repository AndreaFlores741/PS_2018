import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CalculadoraTest {
	
	Calculadora cal;
	int a, b;
	
	@Before
	public void setUp() throws Exception{
		cal=new Calculadora();
	}
	@Test
	public void testSumar() throws Exception{
		int a = 1;
		int b = 2;
		int r = cal.Sumar(a, b);
		int esperado = a+b;
		assertEquals(esperado, r,0);
	}
	@Test
	public void testRestar() {
		a = 5;
		b = 6;
		int r = cal.Restar(a, b);
		int esperado = a-b;
		assertEquals(r,esperado,0);
	}
	@Test
	public void testMultiplicar() {	
		a = 5;
		b = 6;
		int r = cal.Multiplicar(a, b);
		int esperado = a*b;
		assertEquals(r,esperado,0);
	}
	@Test
	public void noOkDividirTest() {
		a = 5;
		b = -3;
		int r = cal.Dividir(a, b);
		int noEsperado = a;
		assertNotEquals(r,noEsperado);
	}
	@Test
	public void okDividirTest() {
		a = 5;
		b = 6;
		int r = cal.Dividir(a, b);
		int esperado = a/b;
		assertEquals(r,esperado,0.0);
	}
	@Test
	public void dividirCeroTest() {
		int a = 5;
		int b = 0;
		int r = cal.Dividir(a, b);
		int esperado = 0;	
		assertEquals(r,esperado,0.0);
	}
	@Test
	public void dividirCeroTest2() {
		int a = 5;
		int b = 0;
		int r = cal.Dividir(a, b);
		int esperado = 0;	
		Exception e = new ArithmeticException();
		assertEquals(cal.Dividir(a, b),e);
	}
	
}
