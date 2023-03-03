//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

import view.FrameBlackJack;
import model.Hand;
import model.Player;

public class SaveGame implements ActionListener
{
	FrameBlackJack frame;
	Scanner s = null;
	PrintWriter OutStream;
	public SaveGame(FrameBlackJack frame)
	{
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		try 
		{
			OutStream = new PrintWriter(new FileWriter("src/save.txt"));
			ArrayList<Player> PlayersInfo = frame.getGm().getPlayers();
			OutStream.println(PlayersInfo.size());
			for(Player player : PlayersInfo)
			{
				OutStream.println(player.GetPlayerName());
			}
			OutStream.println(frame.getGm().getcurrentPlayerName());
			OutStream.println((int)frame.getGm().getCurrentHand());
			//OutStream.println(frame.getGm().getCurrentPlayerChips());
			for(Player player : PlayersInfo)
			{
				OutStream.println((int)player.GetChips());
			}
			OutStream.println(frame.getGm().getAllHandsSize());
			for(Player player : PlayersInfo)
			{
				for(Hand hand : player.getHands())
				{
					OutStream.println(player.getHands().size());
					OutStream.println(hand.isSplit());
					OutStream.println(hand.getBettedChips());
					OutStream.println(hand.getHand().size());
					OutStream.println(hand.getHand());
				}
			}
			OutStream.println("Dealer");
			OutStream.println(frame.getGm().getBancaCards().size());
			OutStream.println(frame.getGm().getBancaCards());
			
			
		} 
		catch (IOException e) 
		{
			System.out.println("Cannot Save");
		}
		finally 
		{
			if (OutStream != null)
				OutStream.close();
		}
		
		
	}
	

}
