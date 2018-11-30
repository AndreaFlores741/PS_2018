import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Ventana1_Test {
private FrameFixture ventana1;
	
	@Before
	public void setUp() {
 
		ventana1 = new FrameFixture(new ventana1());
	}
	
	@After
	public void tearDown() {
 
		ventana1.cleanUp();
	}
	
	@Test
	public void refrescar_test() {
		

		ventana1.button("Refrescar").click();
	}
}
