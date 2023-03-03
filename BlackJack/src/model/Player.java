//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro


package model;

import java.util.ArrayList;

public class Player 
{
	private String name;
	private int chips;
	
	/**
	 * Public getter for the private player.name variable
	 * @return
	 */
	public String GetPlayerName(){
		return this.name;
	}
	/**
	 * Public getter for the private player.chips variable
	 * @return
	 */
	public int GetChips() {
		return chips;
	}
	
	private ArrayList<Hand> hands = new ArrayList<Hand>();
	
	/**
	 * Player Constructor
	 * @param Pname
	 */
	public Player(String Pname)
	{
		this.chips = Settings.startingChips;
		this.name = Pname;
		
		this.emptyHands();
	}
	public Player(String Pname,int chips)
	{
		this.chips = chips;
		this.name = Pname;
		
		this.emptyHands();
	}
	
	/**
	 * Empties all hands of a player
	 */
	public void emptyHands()
	{
		hands.clear();
	}
	/**
	 * Empties a specific hand of a player
	 */
	public void emptyHand(int index) 
	{
		hands.remove(index);
	}
	/**
	 * Adds a Hand to ArrayList of all player hands
	 */
	public int addHand(Hand hand)
	{
		this.hands.add(hand);
		return hand.getHandValue();
	}
	/**
	 * Adds a specified value of Chips to the player's Chips 
	 * @param value
	 */
	public void addChips (int value)
	{
		this.chips += value;
	}
	
    /**
     * Does the Hit
     * @param deck
     * @param hand
     * @return
     */
    public int hit(Deck deck, Hand hand) 
    {
    	hand.addCard(deck.popCard());
    	return hand.getHandValue();
    }
    
    public int stand(Hand hand) {
    	return hand.getHandValue();
    }
    
    public int doubleDown(Deck deck, Hand hand) {
    	this.chips-= (hand.getBettedChips());
    	hand.doubleBettedChips();
    	return hit(deck,hand);
    }
    
    public int split(Deck deck, Hand hand, String playerName) 
    {
        this.hands.add(new Hand(hand.getBettedChips(),deck,hand.popCard(1)));
        /*this.hands.get(0).setSplit();*/
        this.hands.get(0).addCard(deck.popCard());
        this.hands.get(0).printHand(false,playerName);
        this.hands.get(1).printHand(false,playerName);
        return hand.getHandValue();
    }
    
    public ArrayList<Hand> getHands()
    {
    	return this.hands;
    }
    

}
