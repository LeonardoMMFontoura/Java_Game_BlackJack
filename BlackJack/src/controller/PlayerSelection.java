//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.FrameBlackJack;

public class PlayerSelection implements ActionListener 
{
	FrameBlackJack frame;
	public PlayerSelection(FrameBlackJack frame)
	{
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		ArrayList<String> players = frame.GetPlayersNames();
		System.out.print("Alooo lista de players adquirida");
		frame.abrePainelMesaJogador();
		frame.getGm().GeneratePlayers(players,null);
	}
	

}
