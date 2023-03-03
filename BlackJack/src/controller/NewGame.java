//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//import model.FachadaModel;
import view.FrameBlackJack;

public class NewGame implements ActionListener
{
	FrameBlackJack frame;
	public NewGame(FrameBlackJack frame)
	{
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		frame.abrePainelPlayerSelect();
	}
}
