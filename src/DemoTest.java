import org.junit.*;
import org.fest.swing.fixture.FrameFixture;
import org.fest.util.*;

public class DemoTest {
 
	private FrameFixture demo;
	
	@Before
	public void setUp() {
 
		demo = new FrameFixture(new Demo());
	}
	
	@After
	public void tearDown() {
 
		demo.cleanUp();
	}
	
	@Test
	public void test() {
		
		demo.label("myLabel").requireText("Button wasn't clicked!");
		demo.button("myButton").click();
		demo.label("myLabel").requireText("Button was clicked!");
	}
}