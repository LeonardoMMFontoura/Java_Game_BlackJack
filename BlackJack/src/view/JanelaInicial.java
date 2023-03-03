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

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class JanelaInicial extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8523586395084960537L;
	JButton btNovoJogo;
	JButton btContinuar;
	BufferedImage[] menu_BG;
	
	/**
	 * Creates a JPanel responsible for the First Menu Panel
	 * @param frame
	 */
	JanelaInicial()
	{
		setSize(new Dimension(1200,700));
		setLayout(null);
		btNovoJogo = new JButton("Novo Jogo");
		btContinuar = new JButton("Continuar");
		
		btNovoJogo.setBounds(450,400,300,50); 
		btContinuar.setBounds(450,460,300,50);
		
		//Loading the background of the first menu
		menu_BG = new BufferedImage[1];
		try 
		{
			menu_BG[0] = ImageIO.read(new File("src/view/Assets/MenuInicial.png"));
		} 
		catch (IOException e) 
		{
			System.out.println("Problema ao ler imagens do disco: " + e);
		}
		
		
		add(btNovoJogo);
		add(btContinuar);
			
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//Drawing the Background of the First Menu
		g2d.drawImage(menu_BG[0],0,0, (ImageObserver)null);
	}
	/**
	 * Adds a Listener for the New Game Button
	 * @param save
	 */
	void addNewGameListener(ActionListener ngl) 
	{
		btNovoJogo.addActionListener(ngl);
	}
	/**
	 * Adds a Listener for the Continue Button
	 * @param save
	 */
	void addContinueListener(ActionListener cl) 
	{
		btContinuar.addActionListener(cl);
	}
	
}