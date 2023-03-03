//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import model.Card;
import model.Card.Suit;
import model.Card.Value;
import view.FrameBlackJack;

public class Continue implements ActionListener
{
	FrameBlackJack frame;
	BufferedReader InStream;
	
	public Continue(FrameBlackJack frame)
	{
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Scanner s = null;
		try 
		{
			InStream = new BufferedReader(new FileReader("src/save.txt"));
			s = new Scanner(InStream);
			int nPlayers = s.nextInt();
			ArrayList<String> PlayersNames = new ArrayList<String>();
			ArrayList<ArrayList<Card>> PlayersHands = new ArrayList<ArrayList<Card>>();
			
			ArrayList<Card> DealerHand = new ArrayList<Card>();
			int[] chips = new int[nPlayers];
			for(int i = 0; i< nPlayers; i++)
			{
				PlayersNames.add(s.next());
			}
			String currentPlayer = (s.next());
			int currentHand = s.nextInt();
						
			for(int i = 0; i<nPlayers; i++)
			{
				chips[i] = s.nextInt();
			}
			
			int totalHandsSize = s.nextInt();
			boolean[] splitedArray = new boolean[totalHandsSize];
			int[] bettedChips = new int[totalHandsSize];
			
			for(int i = 0; i<nPlayers; i++)
			{
				int Handcount = s.nextInt();
				
				for(int k = 0; k<Handcount;k++)
				{
					boolean isSplited = s.next().equals("false") ? false: true;
					splitedArray[k] = isSplited;
					bettedChips[k] = s.nextInt();
					
					ArrayList<Card> TempHand = new ArrayList<Card>();
					int handSize = s.nextInt();
				
					
					for(int j =0; j< handSize;j++)
					{
						String value = s.next();
						char bracket = 91;
						if(value.charAt(0) == bracket)
						{
							value = value.substring(1);
						}
							
						s.next();
						String temp = s.next();
						String suit = temp.substring(0,temp.length()-1);
						Card tempCard = new Card(Suit.toSuit(suit),Value.toValue(value));
						TempHand.add(tempCard);
					}
					PlayersHands.add(TempHand);
				}
				
			}
			s.next();
			int handSize = s.nextInt();
			for(int j =0; j< handSize;j++)
			{
				String value = s.next();
				char bracket = 91;
				if(value.charAt(0) == bracket)
				{
					value = value.substring(1);
				}
					
				s.next();
				String temp = s.next();
				String suit = temp.substring(0,temp.length()-1);
				Card tempCard = new Card(Suit.toSuit(suit),Value.toValue(value));
				DealerHand.add(tempCard);
			}
			
			frame.getGm().GeneratePlayers(PlayersNames, chips);
			frame.getGm().SetCurrentPlayer(currentPlayer,currentHand);
			frame.getGm().LoadAllHands(PlayersHands, DealerHand, splitedArray, bettedChips);
			
			
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			System.out.println("Cannot read save from disk");
		}
		finally
		{
			if (s != null)
				s.close();
		}
		frame.abrePainelMesaJogador();
		
		
	}
	
	
}
