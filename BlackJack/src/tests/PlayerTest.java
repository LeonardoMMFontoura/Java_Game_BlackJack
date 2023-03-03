package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.Card;
import model.Player;
import model.Card.Suit;
import model.Card.Value;

/**
 * Tests for each method of Player
 * 
 */
public class PlayerTest 
{
	@Test
	public void testCreatePlayer() 
	{
		Player a = new Player("Gerêmias");
		assertNotNull("Player is null",a);
	}
	
	@Test
	public void testAddCards()
	{
		Player a = new Player("Gerêmias");
		Card b = new Card(Suit.HEARTS,Value.FIVE);
		a.addCard(b);
		int expected = a.GetCardAmount();
		assertEquals("Cards not added",expected,1);
	}
	
	@Test
	public void testEmptyHand()
	{
		Player a = new Player("Gerêmias");
		Card b = new Card(Suit.HEARTS,Value.FIVE);
		a.addCard(b);
		a.emptyHand();
		int expected = a.GetCardAmount();
		assertEquals("Hand not emptied",expected,0);
	}
	
	@Test
	public void testHandValue()
	{
		Player a = new Player("Gerêmias");
		Card b = new Card(Suit.HEARTS,Value.FIVE);
		a.addCard(b);
		int expected = a.getHandValue();
		assertEquals("Hand",expected,5);
		
	}
}
