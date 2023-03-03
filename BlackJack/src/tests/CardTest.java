package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.Card;
import model.Card.Suit;
import model.Card.Value;

/**
 * Tests for each method of Card
 * 
 */
public class CardTest {
	@Test
	public void TestEnumValue() {
		assertEquals("getValues(0) não está retornando ACE",Card.Value.getValues(0),Value.ACE);
		assertEquals("getValues(12) não está retornando KING",Card.Value.getValues(12),Value.KING);
		assertEquals("getValuesLength() não está retornando 13",Card.Value.getValuesLength(),13);
	}
	@Test
	public void TestEnumSuit() {
		assertEquals("getSuit(0) não está retornando HEARTS",Card.Suit.getSuits(0),Suit.HEARTS);
		assertEquals("getSuit(3) não está retornando SPADES",Card.Suit.getSuits(3),Suit.SPADES);
		assertEquals("getSuitLength() não está retornando 4",Card.Suit.getSuitLength(),4);
	}
	@Test
	public void TestCardConstructor() {
		Card c = new Card(Suit.HEARTS,Value.ACE);
		assertNotNull("A carta c criada está nula",c);
	}
	@Test
	public void TestGetSuit() {
		Card c = new Card(Suit.HEARTS,Value.ACE);
		assertEquals("getSuit de c não está retornando HEARTS",c.getSuit(),Card.Suit.HEARTS);
		
	}
	@Test
	public void TestGetValue() {
		Card c = new Card(Suit.HEARTS,Value.ACE);
		assertEquals("getValue de c não está retornando ACE",c.getValue(),Card.Value.ACE);
	}
	@Test
	public void TestToString() {
		Card c = new Card(Suit.HEARTS,Value.ACE);
		assertEquals("O print da carta c não está correspondendo ao correto","ACE of HEARTS",c.toString());
	}
}
