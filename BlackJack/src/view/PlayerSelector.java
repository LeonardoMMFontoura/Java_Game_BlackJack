//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro

package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerSelector extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2355205419096487467L;
	JTextField textField0;
	JTextField textField1;
	JTextField textField2;
	JTextField textField3;
	
	JButton btComecarJogo;
	
	BufferedImage[] selection_BG;
	
	
	/**
	 * Creates a JPanel responsible for the Player Selection Menu
	 * @param frame
	 */
	PlayerSelector()
	{
		setSize(new Dimension(1200,700));
		setLayout(null);
		
		btComecarJogo = new JButton("Começar jogo");
		
		textField0 = new JTextField("Player 1 Name",10);
		textField1 = new JTextField("Player 2 Name",10);
		textField2 = new JTextField("Player 3 Name",10);
		textField3 = new JTextField("Player 4 Name",10);
		
		btComecarJogo.setBounds(450,460,300,50); 
		
		textField0.setBounds(20, 400, 280, 30);
		textField0.setFont(textField0.getFont().deriveFont(30f));
		
		textField1.setBounds(310, 400, 280, 30);
		textField1.setFont(textField1.getFont().deriveFont(30f));
		
		textField2.setBounds(600, 400, 280, 30);
		textField2.setFont(textField2.getFont().deriveFont(30f));
		
		textField3.setBounds(890, 400, 280, 30);
		textField3.setFont(textField3.getFont().deriveFont(30f));
		
		selection_BG = new BufferedImage[1];
		//Loading the Background Image
		try 
		{
			selection_BG[0] = ImageIO.read(new File("src/view/Assets/PlayerSelection.png"));
		} 
		catch (IOException e) 
		{
			System.out.println("Problema ao ler imagens do disco: " + e);
		}
		
		add(btComecarJogo);
		add(textField0);
		add(textField1);
		add(textField2);
		add(textField3);
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(selection_BG[0],0,0, (ImageObserver)null);
	}
	/**
	 * Adds a Listener for the Start Game Button
	 * @param save
	 */
	void addStartGame(ActionListener stl) 
	{
		btComecarJogo.addActionListener(stl);
	}
	
	/**
	 * Public converter from a player Number/Ordinal position to it's Name
	 * @param playerNumber
	 * @return PlayerName
	 */
	public String GetTextFieldText(int playerNumber)
	{
		//Maybe having a Dictionary on this class, and making this method just a public getter for it, would be better (my past self did something unnecessary)
		String input = null;
		switch(playerNumber)
		{
			case 0: input = textField0.getText(); break;
			case 1: input = textField1.getText(); break;
			case 2: input = textField2.getText(); break;
			case 3: input = textField3.getText(); break;
		}
				
		return input;
	}
	


}
