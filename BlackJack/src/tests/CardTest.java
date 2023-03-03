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
		assertEquals("getValues(0) n�o est� retornando ACE",Card.Value.getValues(0),Value.ACE);
		assertEquals("getValues(12) n�o est� retornando KING",Card.Value.getValues(12),Value.KING);
		assertEquals("getValuesLength() n�o est� retornando 13",Card.Value.getValuesLength(),13);
	}
	@Test
	public void TestEnumSuit() {
		assertEquals("getSuit(0) n�o est� retornando HEARTS",Card.Suit.getSuits(0),Suit.HEARTS);
		assertEquals("getSuit(3) n�o est� retornando SPADES",Card.Suit.getSuits(3),Suit.SPADES);
		assertEquals("getSuitLength() n�o est� retornando 4",Card.Suit.getSuitLength(),4);
	}
	@Test
	public void TestCardConstructor() {
		Card c = new Card(Suit.HEARTS,Value.ACE);
		assertNotNull("A carta c criada est� nula",c);
	}
	@Test
	public void TestGetSuit() {
		Card c = new Card(Suit.HEARTS,Value.ACE);
		assertEquals("getSuit de c n�o est� retornando HEARTS",c.getSuit(),Card.Suit.HEARTS);
		
	}
	@Test
	public void TestGetValue() {
		Card c = new Card(Suit.HEARTS,Value.ACE);
		assertEquals("getValue de c n�o est� retornando ACE",c.getValue(),Card.Value.ACE);
	}
	@Test
	public void TestToString() {
		Card c = new Card(Suit.HEARTS,Value.ACE);
		assertEquals("O print da carta c n�o est� correspondendo ao correto","ACE of HEARTS",c.toString());
	}
}
