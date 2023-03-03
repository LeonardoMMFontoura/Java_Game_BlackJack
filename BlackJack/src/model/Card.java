//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro

package model;

public class Card 
 {
	 /**
	  * 
	  * Possible Values of a card
	  *
	  */
	 public enum Value 
	    {
	        ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,KING,QUEEN;
		 
		 	/**
		 	 * Get a specific item of the Value Enumerator by it's index 
		 	 * @param index
		 	 * @return
		 	 */
	        public static Value getValues(int index)
	        {
	            Value[] values = Value.values();
	            return values[index];
	        } 
	        /**
	         * Get the number of elements in the Value Enumerator
	         * @return
	         */
	        public static int getValuesLength()
	        {
	            return Value.values().length;
	        }
	        public static Value toValue(String stringValue)
	        {
	        	Value valor = null;
	        	for(int i = 0; i < Value.values().length; i++)
	        	{
	        		if(Value.getValues(i).toString().equals(stringValue))
	        		{
	        			valor = Value.getValues(i);
	        		}
	        	}
				return valor;
	        }
	        
	    }
	 /**
	  * 
	  * Possible Suits of a card
	  *
	  */
	    public enum Suit 
	    {
	    	CLUBS, DIAMONDS, HEARTS, SPADES;
	    	
	    	/**
	    	 * Get a specific item of the Suit Enumerator by it's index 
	    	 * @param index
	    	 * @return
	    	 */
	        public static Suit getSuits(int index)
	        {
	            Suit[] naipes = Suit.values();
	            return naipes[index];
	        }
	        /**
	         * Get the number of elements in the Value Enumerator
	         * @return
	         */
	        public static int getSuitLength()
	        {
	            return Suit.values().length;
	        }
	        public static Suit toSuit(String stringValue)
	        {
	        	Suit valor = null;
	        	for(int i = 0; i < Suit.values().length; i++)
	        	{
	        		if (Suit.getSuits(i).toString().equals(stringValue))
	        		{
	        			valor = Suit.getSuits(i);
	        		}
	        		
	        	}
				return valor;
	        }
	    } 
	    
	    private Suit naipe;
	    private Value valor;
	    
	    private Vector2 position = new Vector2(0,0);
	    
	    /**
	     * Card Constructor
	     * @param naipe
	     * @param valor
	     */
	    public Card (Suit naipe, Value valor)
	    {
	        this.naipe = naipe;
	        this.valor = valor;
	        this.position.set(-2,-2);
	    }
	    /**
	     * Get the Suit of a card
	     * @return
	     */
	    public Suit getSuit()
	    {
	        return this.naipe;
	    }
	    /**
	     * Get the Value of a card
	     * @return
	     */
	    public Value getValue()
	    {
	        return this.valor;
	    }
	    
	    public int getRealValue()
	    {
	    	if(this.valor.ordinal() < 9)
	    	{
	    		return this.valor.ordinal()+1;
	    	}
	    	else
	    	{
	    		return 10;
	    	} 	
	    }
	    public Vector2 getPosition()
	    {
	    	return this.position;
	    }
	    
	    /**
	     * A way of transform a card into a string;
	     */
	    public String toString()
	    {
	        return this.valor+ " of " +this.naipe;
	    }

}
