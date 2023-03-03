//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro


package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    /**
     *  An implementation of a deck using ArrayList
     *  
     */
	private ArrayList<Card> cards;
	
    /**
     * Creates a full deck of cards
     * @param numberofDecks
     */
    public void fillDeck()
    {
        for (int i = 0; i < Card.Suit.getSuitLength(); i++)
        {
            for (int j = 0; j< Card.Value.getValuesLength(); j++)
            {
                Card card = new Card(Card.Suit.getSuits(i),Card.Value.getValues(j));
                this.cards.add(card);
            }
        } 
    }
    /**
     * Creates a Deck with any multiple of "standard sized deck"'s total of cards
     * @param numberofDecks total of decks created
     * @param willBeShuffled if it should be shuffled
     */
    public Deck()
    {
    	this.cards = new ArrayList<>();
    	for (int i =0; i < Settings.howManyDecks;i++)
    	{
    		fillDeck();
    	}
    		shuffle();
    }
    
    /**
     * Remove all cards of a deck
     * @param deck
     */
    public void resetDeck()
    {
    	this.cards.clear();
    	for (int i =0; i < Settings.howManyDecks;i++)
    	{
    		fillDeck();
    	}
    		shuffle();
    }
    public void clearDeck()
    {
    	this.cards.clear();
    }
    /**
     * Remove a card of the deck at the specified index
     * @param index
     */
    public void removeCard(int index)
    {
        this.cards.remove(index);
    }
    /**
     * Shuffles the entire deck
     */
    public void shuffle()
    {
        Collections.shuffle(this.cards);
    }
    /**
     * Get a card of the "top" of the deck
     * @return Card instance
     */
    public Card popCard()
    {
    	Card temp;
        temp = this.cards.get(0);
        this.removeCard(0);
        
        return temp;
    }
    /**
     * Get the size of the deck
     * @return Size of the arrayList of cards
     */
    public int getDeckSize()
    {
        return cards.size();
    }
    
    /**
     * Serves as a debug function to visualize the entire deck later
     */
    public String toString()
    {
        String deck ="";
        int count = 0;
        for (Card card : cards)
        {
        	count+=1;
            deck += count+"/"+ this.getDeckSize()+ " " +  card.toString() + "\n";
        }
        return deck;
    }

}
