package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.*;
/**
 * Tests for each method of Deck
 * 
 */
public class DeckTest 
{
	@Test
	public void testFillDeck()
	{
		Deck a = new Deck(); //The constructor already uses the fillDeck() and Shuffle() function
		a.clearDeck();
		a.fillDeck();
		int expected = a.getDeckSize();
		assertEquals("Deck not filled correctly",expected,52);
	}
	
	@Test
	public void testResetDeck()
	{
		Deck a = new Deck(); //The constructor already uses the fillDeck() and Shuffle() function
		a.removeCard(0);
		a.removeCard(0);
		a.resetDeck();
		int expected = a.getDeckSize();
		assertEquals("Deck not filled correctly when being reset",expected,Settings.howManyDecks*52);
	}
	
	@Test
	public void testCreateDeck()
	{
		Deck a = new Deck(); //The constructor already uses the fillDeck() and Shuffle() function
		assertNotNull("Created deck is Null",a);
		int expected = a.getDeckSize();
		assertEquals("Created deck not filled with the proper amount of cards",expected,Settings.howManyDecks*52);
	}
	
	@Test
	public void testRemoveCard()
	{
		Deck a = new Deck(); //The constructor already uses the fillDeck() and Shuffle() function
		int oldSize = a.getDeckSize();
		a.removeCard(0);
		int newSize = a.getDeckSize();
		assertNotEquals("A card cannot be removed",oldSize,newSize);
		
	}
	//Shuffle() and GetDeckSize() don't have an test case because they're essentially public "getters" of java native methods for the ArrayList type.
	@Test
	public void testToString()
	{
		Deck a = new Deck();
		int deckSize = a.getDeckSize();
		for (int i = 0; i<deckSize-1;i++)
		{
			a.removeCard(0);
		}
		String expected = a.toString();
		Card b = a.popCard();
		assertEquals("Not printing cards correctly",expected,"1/1 "+b.toString()+"\n");
		
	}

}
