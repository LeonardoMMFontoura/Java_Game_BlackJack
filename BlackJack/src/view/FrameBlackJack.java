//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro

package view;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.Continue;
import controller.GameController;
import controller.NewGame;
import controller.PlayerSelection;
//import controller.GameController;
import controller.SaveGame;

public class FrameBlackJack extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2260256195875317772L;
	final int LARG_DEFAULT = 1200;
	final int ALT_DEFAULT = 700;
	
	JanelaInicial painelInicial = null;
	PlayerSelector painelPlayerSelect = null;
	JanelaBanca painelMesa = null;
	JanelaPlayer painelPlayer = null;
	
	GameController gm = null;
	
	public static void main(String args[]) 
	{
		@SuppressWarnings("unused")
		FrameBlackJack frame = new FrameBlackJack();	
		
	}
	
	public FrameBlackJack() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl / 2 - LARG_DEFAULT / 2;
		int y = sa / 2 - ALT_DEFAULT / 2;
		setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setTitle("BlackJack");
		
		gm = new GameController();
		painelInicial = new JanelaInicial();
		painelPlayerSelect = new PlayerSelector();
		
		
		painelInicial.addNewGameListener(new NewGame(this));
		painelPlayerSelect.addStartGame(new PlayerSelection(this));
		
		painelInicial.addContinueListener(new Continue(this));
		
		
		painelMesa = new JanelaBanca(this);
		painelMesa.addSaveListener(new SaveGame(this));
		
		painelPlayer = new JanelaPlayer(this);
		
		painelMesa.addMouseListener(gm);
		
		
	    getContentPane().add(painelInicial);

		setVisible(true);
		

	}
	
	
	public void abrePainelInicial() 
	{
		getContentPane().removeAll();
	    getContentPane().add(painelInicial);
	    repaint();
	}
	
	public void abrePainelPlayerSelect() 
	{
		getContentPane().removeAll();
	    getContentPane().add(painelPlayerSelect);
	    repaint();
	}
	
	public ArrayList<String> GetPlayersNames() 
	{
		ArrayList<String> input = new ArrayList<>();
		for(int i = 0; i < 4 ; i++)
		{
			String playerName = painelPlayerSelect.GetTextFieldText(i);
			if(!playerName.equals(("Player "+(i+1)+" Name"))) 
			{
				System.out.print("Player"+(i+1)+": "+playerName+"\n");
				input.add(playerName);
			}
		}
		return input;
	}
	
	public void abrePainelMesaJogador() 
	{
		getContentPane().removeAll();
	    getContentPane().add(painelMesa);
	    getContentPane().add(painelPlayer);
	    repaint();
	}
	public GameController getGm()
	{
		return this.gm;
	}
	
	
	public void addMouseListenerMesa(MouseListener ml) 
	{
		painelMesa.addMouseListener(ml);
	}

}
