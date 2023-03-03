//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import controller.GameController;
import model.Card;
import model.Observable;
import model.Observer;
import model.Vector2;

public class JanelaPlayer extends JPanel implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5968787499341257225L;
	JButton btDouble;
	JButton btSplit;
	JButton btHit;
	JButton btStand;
	JButton btSurrender;
	
	JLabel labTotal;
	JLabel labSaldo;
	JLabel labAposta;
	JLabel labName;
	JLabel labSplit; 
	Font customFont;
	
	BufferedImage[] img_Cartas;
	BufferedImage[] player_BG;
	
	GameController gm;
	
	// array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]
    {
        "gif", "png", "bmp"
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() 
    {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    
    JanelaPlayer(FrameBlackJack frame)
    {
    	setLayout(null);
    	setLocation(0,350);
    	setSize(new Dimension(1200,350));
    	
		gm = frame.getGm();
		
		btDouble = MyButton("Double");
		btSplit = MyButton("Split");
		btHit = MyButton("Hit");
		btStand = MyButton("Stand");
		btSurrender = MyButton("Surrender");
		
		labTotal = new JLabel("Valor da mão do Player: 0");
		labSaldo = new JLabel("Saldo: 0");
		labAposta = new JLabel("Apostado: 0");
		labName = new JLabel("Player: player"); //Pode ser JLabel ?
		labSplit = new JLabel("É split: Não"); //Pode ser JLabel ?
		
		player_BG = new BufferedImage[1];
		img_Cartas = new BufferedImage[52];
		try 
		{
			player_BG[0] = ImageIO.read(new File("src/view/Assets/PlayerBG2.png"));
			
			File cardDir = new File("src/view/Assets/Cartas");
			int count = 0;
			for(File f : cardDir.listFiles(IMAGE_FILTER))
			{
				
				img_Cartas[count] = ImageIO.read(f);
				count++;
			}
			
		} 
		catch (IOException e) 
		{
			System.out.println("Problema ao ler imagens do disco: " + e);
		}
		
		try 
		{
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/view/Assets/Fonts/AlegreyaSansSC-Bold.ttf"));
		     ge.registerFont(customFont);
		} 
		catch (IOException|FontFormatException e) 
		{
			System.out.println("Problema a fonte do jogo no disco " + e);
		}
		
		
		btDouble.setBounds(225,230,155,45);
		btDouble.setFont(customFont.deriveFont(25f));
		btSplit.setBounds(410,230,155,45);
		btSplit.setFont(customFont.deriveFont(25f));
		btHit.setBounds(595,230,155,45);
		btHit.setFont(customFont.deriveFont(25f));
		btStand.setBounds(780,230,155,45);
		btStand.setFont(customFont.deriveFont(25f));
		btSurrender.setBounds(950,30,155,45);
		btSurrender.setFont(customFont.deriveFont(25f));
		
		
		labName.setBounds(20,10,500,200);
		labName.setFont(customFont.deriveFont(25f));
		labName.setForeground(Color.white);
		
		labTotal.setBounds(20,40,500,200);
		labTotal.setFont(customFont.deriveFont(25f));
		labTotal.setForeground(Color.white);
		
		labSplit.setBounds(20,70,500,200);
		labSplit.setFont(customFont.deriveFont(25f));
		labSplit.setForeground(Color.white);
		
		labSaldo.setBounds(950,40,250,200);
		labSaldo.setFont(customFont.deriveFont(25f));
		labSaldo.setForeground(Color.white);
		
		labAposta.setBounds(950,70,250,200);
		labAposta.setFont(customFont.deriveFont(25f));
		labAposta.setForeground(Color.white);
		
		btDouble.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				gm.PlayPlayerHand(gm.getcurrentPlayer(), "double");
			}
		});
		btSplit.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				gm.PlayPlayerHand(gm.getcurrentPlayer(), "split");
			}
		});
		btHit.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				gm.PlayPlayerHand(gm.getcurrentPlayer(), "hit");
			}
		});
		btStand.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				gm.PlayPlayerHand(gm.getcurrentPlayer(), "stand");
			}
		});
		btSurrender.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				gm.PlayPlayerHand(gm.getcurrentPlayer(), "surrender");
			}
		});
		
		

		
		
		add(btSurrender);
		add(btDouble);
		add(btSplit);
		add(btHit);
		add(btStand);
		add(labName);
		add(labTotal);
		add(labSplit);
		add(labSaldo);
		add(labAposta);
		
		gm.addObserver(this);
    }
    public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(player_BG[0],0,0, (ImageObserver)null);
		
		if(gm.getCurrentPlayerCards() != null)
		{	
			for (Card card :gm.getCurrentPlayerCards()) //dar um jeito de entregar a currentHand aqui
			{
				Vector2 pos = card.getPosition();
				if(card.getValue().ordinal() > 0 && (int)card.getValue().ordinal() != 9)
				{
					
					g2d.drawImage(img_Cartas[(card.getValue().ordinal() - 1) * 4 + (int)card.getSuit().ordinal() ], pos.getX(), pos.getY()-350,(ImageObserver)null);
				}
				else
				{
					int ordinal = card.getValue().ordinal();
					if (ordinal == 0)
					{
						g2d.drawImage(img_Cartas[32 + ((card.getValue().ordinal()) * 4) + (int)card.getSuit().ordinal() ], pos.getX(), pos.getY()-350,(ImageObserver)null);
					}
					else if (ordinal == 9)
					{
						g2d.drawImage(img_Cartas[img_Cartas.length - 5 + (int)card.getSuit().ordinal() ], pos.getX(), pos.getY()-350,(ImageObserver)null);
					}
					
				}
			}
			labTotal.setText("Valor da mão: "+ gm.getcurrentPlayerHandValueView());
			labSplit.setText("É split: " + (gm.getCurrentSplit() ? "Sim":"Não"));
			
		}
		labName.setText("Player: "+ gm.getcurrentPlayerName());
		labSaldo.setText("Saldo total: "+gm.getCurrentPlayerChips());
		labAposta.setText("Aposta: "+gm.getCurrentBet());
	}
    
    private JButton MyButton(String text)
    {
    	JButton temp = new JButton(text);
    	temp.setForeground(Color.WHITE); 
    	temp.setBackground(Color.getHSBColor(0.01f,1, 0.605f));
		Border border = BorderFactory.createBevelBorder(0, Color.WHITE,Color.BLACK);
		Border border2 = BorderFactory.createBevelBorder(1,Color.BLACK,Color.WHITE,Color.getHSBColor(0.01f,1, 0.605f),Color.WHITE);
		Border compound = new CompoundBorder(border, border2); 
		temp.setBorder(compound);
		
		return temp;
    	
    }
    
    
    
    void addDoubleListener(ActionListener doub) 
	{
		btDouble.addActionListener(doub);
	}
	
	void addSplitListener(ActionListener spl) 
	{
		btSplit.addActionListener(spl);
	}
	
	void addHitListener(ActionListener hit) 
	{
		btHit.addActionListener(hit);
	}
	
	void addStandListener(ActionListener std) 
	{
		btStand.addActionListener(std);
	}
    
    
    

	@Override
	public void notify(Observable o) {
		repaint();	
	}

}
