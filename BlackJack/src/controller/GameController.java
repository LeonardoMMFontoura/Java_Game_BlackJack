//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro


package controller;


import javax.swing.JPanel;

import model.*;
import view.FrameBlackJack;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameController implements Observable,MouseListener
{
	
	ArrayList<Player> players = new ArrayList<Player>();
	Player dealer = new Player("Dealer");
	public Deck deck = new Deck();
	
	ArrayList<Observer> lob = new ArrayList<Observer>(); 
	FrameBlackJack frame = null;
	JPanel painelMesa = null;
	JPanel painelPlayer = null;
	
	
	Player currentPlayer = null;
	int currentHand = 0;
	boolean canstillPlay = false;
	boolean canstillBet = true;
	
	int[] Bets = new int[4];
	int currentBet = 0;
	 
	
	////
	//// COISAS Q A VIEW TA USANDO PRA TESTAR
	////
	public ArrayList<Card> getBancaCards()
	{
		if(dealer.getHands() != null)
			if(dealer.getHands().size() >= 1)
			{
				return dealer.getHands().get(0).getHand();
			}
		return null;
	}
	
	public int getDealerHandValueView()
	{
		if(dealer.getHands() != null)
			if(dealer.getHands().size() >= 1)
			{
				return dealer.getHands().get(0).getHandValueFORVIEW();
			}			
		return 0;
	}
	public ArrayList<Card> getCurrentPlayerCards()//Falta especificar qual mão
	{
		if(currentPlayer.getHands() != null)
		{
			if(currentPlayer.getHands().size() > currentHand)
			{
				return currentPlayer.getHands().get(currentHand).getHand();
			}	
		}
		return null;
	}
	public int getcurrentPlayerHandValueView()///Falta especificar qual
	{
		if(currentPlayer.getHands() != null)
			return currentPlayer.getHands().get(currentHand).getHandValueFORVIEW();
		return 0;
	}
	public String getcurrentPlayerName()
	{
		return currentPlayer.GetPlayerName();
	}
	public int getCurrentPlayerChips()
	{
		return currentPlayer.GetChips();
	}
	public int getCurrentBet()
	{
		if(currentPlayer.getHands() != null)
			if(currentPlayer.getHands().size() > currentHand)
			{
				return currentPlayer.getHands().get(currentHand).getBettedChips();
			}	
		return currentBet;
	}
	public boolean getCurrentSplit()///Falta especificar qual
	{
		if(currentPlayer.getHands() != null)
			return currentPlayer.getHands().get(currentHand).isSplit();
		return false;
	}
	public Player getcurrentPlayer()
	{
		return currentPlayer;
	}
	
	public boolean canStillBet()
	{
		return canstillBet;
	}
	///
	///
	///
	
	//Savesys
	public ArrayList<Player> getPlayers()
	{
		ArrayList<Player> temp = new ArrayList<Player>();
		for (Player player: players)
		{
			temp.add(player);
		}
		return temp;
	}
	public int getAllHandsSize()
	{
		int total = 0;
		for (Player player: players)
		{
			for(@SuppressWarnings("unused") Hand hand: player.getHands())
			{
				total++;
			}
		}
		return total;
	}
	public int getCurrentHand()
	{
		return currentHand;
	}
	//
	

	
	public boolean GeneratePlayers(ArrayList<String> playersNames, int[] chips)
	{
		if(chips == null)
		{
			for(String playerName : playersNames)
			{
				players.add(new Player(playerName));
			}
			System.out.print("\nThank you! Here are our players then:\n");
			for (Player player : players) {
				System.out.printf("\n%s",player.GetPlayerName());
				}
			System.out.print("\n");
			if (players.size() >= 1 && players.size() <= 4)
			{
				currentPlayer = players.get(0);
				return true;
			}
			else 
			{
				return false;
			}
		}
		else
		{
			int count=0;
			for(String playerName : playersNames)
			{
				players.add(new Player(playerName,chips[count]));
				count++;
			}
			System.out.print("\nThank you! Here are our players then:\n");
			for (Player player : players) {
				System.out.printf("\n%s",player.GetPlayerName());
				}
			System.out.print("\n");
			if (players.size() >= 1 && players.size() <= 4)
			{
				currentPlayer = players.get(0);
				return true;
			}
			else 
			{
				return false;
			}
		}
		
		
			
	}

	public void SetCurrentPlayer(String pName,int currentHandP)
	{
		for(Player player: players)
		{
			if(player.GetPlayerName().equals(pName))
			{
				currentPlayer = player;
			}
		}
		currentHand = currentHandP;
	}
	public void LoadAllHands(ArrayList<ArrayList<Card>> playerHands,ArrayList<Card> dealerHand, boolean[] splitedArray,int bettedChips[])
	{
		Hand dealerhand = new Hand(500,dealerHand,false);
		dealer.addHand(dealerhand);
		dealer.getHands().get(0).printHand(true,dealer.GetPlayerName());
		
		boolean HandAdded = false;
		for(int i = 0; i < players.size(); i++)
		{
			for(int j = 0; j< splitedArray.length; j++)
			{
				if(HandAdded && !splitedArray[j]) break;
				Hand temp = new Hand(bettedChips[j],playerHands.get(i),splitedArray[j]);
				players.get(i).addHand(temp);
				HandAdded = true;
			}
			HandAdded = false;
			
			for(Hand hand: players.get(i).getHands())
			{
				hand.printHand(false,players.get(i).GetPlayerName());
			}
		}
		for (Observer o: lob) {
			o.notify(this);
		}
		canstillPlay = true;
	}
	
	public void DistributeOpeningHands(Deck deck, int[] bets) 
	{
		Hand dealerhand = new Hand(500, deck);
		dealer.addHand(dealerhand);
		dealer.getHands().get(0).printHand(true,dealer.GetPlayerName());
		for(Player player : players) 
		{
			
			Hand temp = new Hand(bets[players.indexOf(player)],deck);
			player.addHand(temp);
			for(Hand hand : player.getHands())
			{
				hand.printHand(false,player.GetPlayerName());
			}
			
		}
		for (Observer o: lob) {
			o.notify(this);
		}
		canstillPlay = true;
	}
	
	public void PlayPlayerHand(Player player,String playerChoice) {
		System.out.printf("\nIt´s %s's turn.\n",player.GetPlayerName());
		ArrayList<Hand> Hands = player.getHands();
		Hand hand = Hands.get(currentHand);
		hand.printHand(false,player.GetPlayerName());

		System.out.print("What would you like to do?\n");
			//String playerChoice = input.next();
		if(playerChoice.equals("hit") && canstillPlay) 
		{
			player.hit(deck, hand);
			
		}
		else if(playerChoice.equals("stand") && canstillPlay) 
		{
			player.stand(hand);
			canstillPlay = false;
		}
		else if(playerChoice.equals("double") && canstillPlay && hand.getHand().size()==2 && !hand.isSplit())
		{
			player.doubleDown(deck, hand);
			player.stand(hand);
			canstillPlay = false;
		}
		else if(playerChoice.equals("surrender") && canstillPlay && hand.getHand().size()==2 && !hand.isSplit())
		{
			canstillPlay = false;
			player.addChips(hand.getBettedChips()/2);
			while(hand.getHandValue() <= 21)
			{
				player.hit(deck, hand);
			}
			
		}
		if(playerChoice.equals("split") && canstillPlay && hand.getHand().get(0).getRealValue() == hand.getHand().get(1).getRealValue() && hand.getHand().size() == 2 && !hand.isSplit())
		{	
			player.split(deck, player.getHands().get(0),player.GetPlayerName());
		}
		
		if(hand.getHandValue()>21) 
		{
			System.out.print("Busted!\n");
			canstillPlay = false;
		}

		hand.printHand(false,player.GetPlayerName());

		System.out.printf("\nFinal hand count for %s: %d\n",player.GetPlayerName(),player.getHands().get(currentHand).getHandValue());

		if (!canstillPlay && currentHand != Hands.size()-1)
		{
			currentHand++;
			canstillPlay = true;
		}
		else if(players.indexOf(player) != players.size()-1 && !canstillPlay)
		{
			currentPlayer = players.get(players.indexOf(player) + 1);
			canstillPlay = true;
			currentHand = 0;

		}
		else if(!canstillPlay)
		{
			//currentPlayer = players.get(0);
			PlayDealerHand();
			PrintResults();				
			DiscardHands();
			if(deck.getDeckSize()<(52*Settings.howManyDecks)-52*Settings.howManyDecks*Settings.deckPenetration)
				deck.resetDeck();
			canstillBet = true;
		}
		for (Observer o: lob) 
		{
			o.notify(this);
		}

		
	}
	
	private void PlayDealerHand() 
	{
		
		while(dealer.getHands().get(0).getHandValue()<17) 
		{
			dealer.getHands().get(0).addCard(deck.popCard());
			dealer.getHands().get(0).printHand(false,dealer.GetPlayerName());
		}
		
		for (Observer o: lob) 
		{
			o.notify(this);
		}
	}
	
	private String Results(Hand hand, Player player) 
	{
		int dealerScore = dealer.getHands().get(0).getHandValue();
		int playerScore = hand.getHandValue();
		String result = "";
		if (playerScore>21) 
			result = "loss";
		else if (playerScore==21 && hand.GetCardAmount()==2) 
		{
			if(dealerScore==21 && dealer.getHands().get(0).GetCardAmount()==2) result = "draw";
			else result = "blackjack";
		}
		else if (dealerScore>21) result = "win";
		else if (playerScore>dealerScore) result ="win";
		else if(playerScore==dealerScore) result ="draw";
		else result = "loss";

		if(result.equals("win"))
		{
			player.addChips(hand.getBettedChips()*2);
		}
		if(result.equals("blackjack"))
		{
			player.addChips((int) (hand.getBettedChips()*2.5f));
		}
		if(result.equals("draw"))
		{
			player.addChips((hand.getBettedChips()));
		}
		
		return result;
		
	}
	
	private void PrintResults() 
	{
		System.out.print("\n\n\nFINAL RESULTS:\n");
		for(Player player:players) 
		{
			for (int i =0; i < player.getHands().size() ; i++)
			{
				System.out.printf("\n%s - %d's Hand: %s",player.GetPlayerName(),i+1,Results(player.getHands().get(i),player));
			}
			
		}
		for (Observer o: lob) 
		{
			o.notify(this);
		}
		
	}
	
	//@SuppressWarnings("unused")
	private void DiscardHands() {
		for(Player player:players) 
		{	
			player.emptyHands();
		}
		dealer.emptyHands();
		for (Observer o: lob) 
		{
			o.notify(this);
		}
	}
	
	@Override
	public void addObserver(Observer o) 
	{
		lob.add(o);
		
	}

	@Override
	public void removeObserver(Observer o)
	{
		lob.remove(o);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			int X = e.getX();
			int Y = e.getY();
			System.out.print("\n X: "+X);
			System.out.print("\n Y: "+Y);
			
			if(X >= 800 && X <=850 && Y >= 120 && Y<= 168 && canstillBet && currentPlayer.GetChips() > currentBet+1)
			{
				currentBet += 1;

			}
			else if(X >= 850 && X <=906 && Y >= 120 && Y<= 168 && canstillBet  && currentPlayer.GetChips() > currentBet+5)
			{
				currentBet+=5;

			}
			else if(X >= 911 && X <=961 && Y >= 120 && Y<= 168 && canstillBet  && currentPlayer.GetChips() > currentBet+10)
			{
				currentBet+=10;

			}
			else if(X >= 828 && X <=878 && Y >= 164 && Y<= 212 && canstillBet  && currentPlayer.GetChips() > currentBet+20)
			{
				currentBet+=20;

			}
			else if(X >= 884 && X <=934 && Y >= 164 && Y<= 212 && canstillBet  && currentPlayer.GetChips() > currentBet+50)
			{
				currentBet+=50;

			}
			else if(X >= 856 && X <=934 && Y >= 208 && Y<= 256 && canstillBet && currentPlayer.GetChips() > currentBet+100)
			{
				currentBet+=100;
				
			}
			else if(X >= 1040 && X<= 1155 && Y>= 200 && Y <=348)
			{
				Bets[players.indexOf(currentPlayer)] = currentBet;
				currentPlayer.addChips(-currentBet);
				canstillBet = false;
				System.out.print("\nAlo\n"+currentBet);
			}
			for (Observer o: lob) 
			{
				o.notify(this);
			}
		}
		if(players.indexOf(currentPlayer) != (players.size()-1) && !canstillBet)
		{
			currentBet = 0;
			currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
			canstillBet = true;
		}
		else if(!canstillBet && !canstillPlay)
		{
			currentPlayer = players.get(0);
			DistributeOpeningHands(deck, Bets);
			currentBet = 0;
			currentHand =0;
		}
		for (Observer o: lob) 
		{
			o.notify(this);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}
