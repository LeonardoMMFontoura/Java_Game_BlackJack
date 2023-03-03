package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.Chips;
/**
 * Tests for each method of Chips
 * 
 */
public class ChipsTest {
	
	@Test
	public void TestConstructor() {
		Chips c = new Chips();
		assertNotNull("The constructor has created a null object",c);
	}
	@Test
	public void TestHowMany() {
		Chips c = new Chips();
		assertEquals("HowMany is returning a wrong value",8,c.howMany(5));
	}
	@Test
	public void TestAddChips() {
		Chips c = new Chips();
		c.addChips(10, 5);
		assertEquals("AddChips added a wrong value",18,c.howMany(5));
	}
	@Test
	public void TestRemoveChips() {
		Chips c = new Chips();
		c.removeChips(4, 5);
		assertEquals("AddChips added a wrong value",4,c.howMany(5));
	}
	@Test
	public void TestTotalChips() {
		Chips c = new Chips();
		assertEquals("totalChips is returning a different amount than expected",500,c.totalChips());
	}

}
