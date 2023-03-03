//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro


package model;

import java.util.ArrayList;

public class Hand 
{
	private int bettedChips;
	private ArrayList<Card> cards = new ArrayList<>();
	private boolean isSplit;
	
	public Hand(int bet, Deck deck) {
		addCard(deck.popCard());
		addCard(deck.popCard());
		bettedChips = bet;
		this.isSplit = false;
	}
	
	public Hand(int bet, Deck deck, Card splitedCard) 
	{
		addCard(splitedCard);
		addCard(deck.popCard());
		bettedChips = bet;
		isSplit = true;
	}
	public Hand(int bet ,ArrayList<Card> handCards,boolean Split)
	{
		for(Card card : handCards)
		{
			cards.add(card);
		}
		bettedChips = bet;
		isSplit = Split;
	}
	
	
	public int GetCardAmount() 
	{
		return this.cards.size();
	}
	public int addCard(Card card)
	{
		this.cards.add(card);
		return this.getHandValue();
	}
	public Card popCard(int index) 
	{
		Card temp = cards.get(index);
		this.cards.remove(index);
		return temp;
	}
	
	public boolean isSplit()
	{
		return isSplit;
	}
	public boolean setSplit()
	{
		this.isSplit = true;
		return isSplit;
	}
	
	public int getBettedChips()
	{
		return bettedChips;
	}
	public void doubleBettedChips()
	{
		bettedChips*=2;
	}
	
	public int getHandValue()
    {
        int value = 0;
        int aces = 0;

        for (Card card : cards)
        {
            switch (card.getValue())
            {
                case ACE : aces++; break;
                case TWO : value+=2; break;
                case THREE : value +=3; break;
                case FOUR : value +=4; break;
                case FIVE : value +=5; break;
                case SIX : value += 6; break;
                case SEVEN : value +=7; break;
                case EIGHT : value +=8; break;
                case NINE : value +=9; break;
                case TEN : value +=10; break;
                case JACK : value +=10; break;
                case QUEEN : value += 10; break;
                case KING: value += 10; break;
            } 
        }
        for(int i =0; i<aces; i++)
        {
            if (value <= 11 - aces)
            {
                value +=11;
            }
            else 
            {
                value +=1;
            }
        }
        return value;
    }
	
    public int getHandValueFORVIEW()
    {
    	int value = 0;
        int aces = 0;

        for (Card card : cards)
        {
        	if(card.getPosition().getX() != -1)
        	{
        		switch (card.getValue())
        		{
                	case ACE : aces++; break;
                	case TWO : value+=2; break;
                	case THREE : value +=3; break;
                	case FOUR : value +=4; break;
                	case FIVE : value +=5; break;
                	case SIX : value += 6; break;
                	case SEVEN : value +=7; break;
                	case EIGHT : value +=8; break;
                	case NINE : value +=9; break;
                	case TEN : value +=10; break;
                	case JACK : value +=10; break;
                	case QUEEN : value += 10; break;
                	case KING: value += 10; break;
        		} 
        	}
        }
        for(int i =0; i<aces; i++)
        {
            if (value<=10)
            {
                value +=11;
            }
            else 
            {
                value +=1;
            }
        }
        return value;
    }
    
	
    /**
     * Prints the current hand of a player
     * @param Showtop I heard that the you don't show the second card of the dealer on the first row of cards, 
     * so i added this boolean that allows it if the dealer will be an instance of a player
     */
    public ArrayList<Vector2> getCardsPosition()
    {
    	ArrayList<Vector2> temp = new ArrayList<Vector2>();
    	for(Card card: cards)
    	{
    		temp.add(card.getPosition());
    	}
    	return temp;
    }
    
    public String printHand(boolean hidden,String name)
    {
    	String hand ="";
        System.out.printf("\n%s's cards:", name);
    	if(hidden) {
    		System.out.printf("\n(1/2) %s(2/2) Hidden\n",cards.get(0).toString()+"\n");
    		cards.get(0).getPosition().set(Settings.BancaHandPosition[0]);
    		cards.get(1).getPosition().set(-1, -1);
    	}
    	else 
    	{
    		for (int i = 0; i<cards.size(); i++)
    		{
    			hand += "\n"+(i+1)+"/"+ this.cards.size()+ " " +  cards.get(i).toString();
    			if(name == "Dealer")
    			{
    				cards.get(i).getPosition().set(Settings.BancaHandPosition[i]);
    			}
    			else
    			{
    				cards.get(i).getPosition().set(Settings.PlayerHandPosition[i]);
    			}
    			
    		}
        
    		System.out.print(hand+"\n"+"Total value: "+this.getHandValue()+"\n");
		}
    	if(hand.length() > 1)
    	{
    		return hand.substring(1);
    	}
    	else
    	{
    		return hand;
    	}
    	
        
    }
    public ArrayList<Card> getHand()
    {
    	return this.cards;
    }
}