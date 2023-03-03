//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
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
import model.Settings;
import model.Vector2;

public class JanelaBanca extends JPanel implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3860899268491076447L;
	JButton btSalvar;
	JButton btEncerrar;
	JButton btRecomecar;
	
	JLabel labTotal;
	Font customFont;
	
	BufferedImage[] img_Fichas;
	BufferedImage[] img_Cartas;
	BufferedImage[] banca_BG;
	
	GameController gm;
	
	/**
	 * 	Array of supported extensions
	 */
    static final String[] EXTENSIONS = new String[]
    {
        "gif", "png", "bmp"
    };
    
    /**
	 * 	Filter to identify images based on their extensions
	 */
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
	
	/**
	 * Creates a JPanel responsible for the "Banca"
	 * @param frame
	 */
	JanelaBanca (FrameBlackJack frame)
	{
		setSize(new Dimension(1200,350));
		setLayout(null);
		gm = frame.getGm(); //Needs to get "Fachada" in the future,not the GameController
		
		btSalvar = MyButton("Salvar");
		btEncerrar = MyButton("Encerrar");
		btRecomecar = MyButton("Recomeçar");
		
		labTotal = new JLabel("Valor da Banca : 0");
		
		banca_BG = new BufferedImage[1];
		img_Fichas = new BufferedImage[6];
		img_Cartas = new BufferedImage[53];
		
		try 
		{
			banca_BG[0] = ImageIO.read(new File("src/view/Assets/BancaBG.png"));
			
			img_Fichas[0] = ImageIO.read(new File("src/view/Assets/Fichas/ficha1.png"));
			img_Fichas[1] = ImageIO.read(new File("src/view/Assets/Fichas/ficha5.png"));
			img_Fichas[2] = ImageIO.read(new File("src/view/Assets/Fichas/ficha10.png"));
			img_Fichas[3] = ImageIO.read(new File("src/view/Assets/Fichas/ficha20.png"));
			img_Fichas[4] = ImageIO.read(new File("src/view/Assets/Fichas/ficha50.png"));
			img_Fichas[5] = ImageIO.read(new File("src/view/Assets/Fichas/ficha100.png"));
			
			
			//Loading all cards images in the corresponding BufferedImage Array
			File cardDir = new File("src/view/Assets/Cartas");
			int count = 0;
			for(File f : cardDir.listFiles(IMAGE_FILTER))
			{
				
				img_Cartas[count] = ImageIO.read(f);
				count++;
			}
			img_Cartas[count] = ImageIO.read(new File("src/view/Assets/deck1.gif"));
			
			
		} 
		catch (IOException e) 
		{
			System.out.println("Problema ao ler imagens do disco: " + e);
		}
		
		//Loading the Font to be used.
		try 
		{
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/view/Assets/Fonts/AlegreyaSansSC-Bold.ttf"));
		     ge.registerFont(customFont);
		} 
		catch (IOException|FontFormatException e) 
		{
			System.out.println("Problema ao ler fonte do jogo no disco " + e);
		}
		
		
		btSalvar.setBounds(20,20,100,40);
		btSalvar.setFont(customFont.deriveFont(18f));
		btEncerrar.setBounds(1050,30,100,40);
		btEncerrar.setFont(customFont.deriveFont(18f));
		btRecomecar.setBounds(20,90,100,40);
		btRecomecar.setFont(customFont.deriveFont(18f));
		
		labTotal.setBounds(20,230,250,200);
		labTotal.setFont(customFont.deriveFont(30f));
		labTotal.setForeground(Color.white);
		
		btEncerrar.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frame.abrePainelInicial();
				//Needs something to reset everything ?
			}
		});
		
		
		add(btSalvar);
		add(btEncerrar);
		add(btRecomecar);
		add(labTotal);
		
		gm.addObserver(this);
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Rectangle2D rect;
		Rectangle2D rectShadow;
		Rectangle2D rectHighlight;
		//Drawing Banca Background
		g2d.drawImage(banca_BG[0],0,0, (ImageObserver)null);
		//Drawing Deck
		g2d.drawImage(img_Cartas[img_Cartas.length -1],Settings.deckStartingPosition[0],Settings.deckStartingPosition[1], (ImageObserver)null);
		//Drawing Chips
		g2d.drawImage(img_Fichas[0], 800, 120,50,48,(ImageObserver)null);
		g2d.drawImage(img_Fichas[1], 856, 120,50,48,(ImageObserver)null);
		g2d.drawImage(img_Fichas[2], 911, 120,50,48,(ImageObserver)null);
		g2d.drawImage(img_Fichas[3], 828, 164,50,48,(ImageObserver)null);
		g2d.drawImage(img_Fichas[4], 884, 164,50,48,(ImageObserver)null);
		g2d.drawImage(img_Fichas[5], 856, 208,50,48,(ImageObserver)null);
		
		
		//Creating a button without a JButton to confirm the current bet
		if(gm.canStillBet())
		{
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
	                RenderingHints.VALUE_RENDER_QUALITY);
	        
			g2d.setStroke(new BasicStroke(2.0f));
			g2d.setPaint(Color.BLACK);
			rectShadow = new Rectangle2D.Double(1048, 208, 130, 48);
			g2d.fill(rectShadow);
			g2d.setPaint(Color.WHITE);
			rectHighlight = new Rectangle2D.Double(1038, 198, 134, 52);
			g2d.fill(rectHighlight);
			rect = new Rectangle2D.Double(1040, 200, 130, 48);
			g2d.setPaint(Color.getHSBColor(0.14f,1, 1));
			g2d.fill(rect);
			g2d.setPaint(Color.WHITE);
			g2d.draw(rect);
			
			
			GlyphVector btConfirmBet = customFont.deriveFont(18f).createGlyphVector(g2d.getFontRenderContext(), "Confirm Bet");
			Shape btConfirmBetTextShape = btConfirmBet.getOutline();
				
			
			AffineTransform aT = new AffineTransform(1,0,0,1,1060,230);
			Shape customDoub = aT.createTransformedShape(btConfirmBetTextShape);
			g2d.setColor(Color.DARK_GRAY);
	        g2d.fill(customDoub);
	        
			
		}
		
		//Drawing every card of banca based on position and the value of the dealer hand.
		if(gm.getBancaCards() != null)
		{	
			for (Card card : gm.getBancaCards())
			{
				Vector2 pos = card.getPosition();
				if (pos.getX() == -1 && pos.getY() == -1)
				{
					g2d.drawImage(img_Cartas[img_Cartas.length -1], Settings.BancaHandPosition[1].getX(),Settings.BancaHandPosition[1].getY(), (ImageObserver)null);
				}
				else
				{
					if(card.getValue().ordinal() > 0 && (int)card.getValue().ordinal() != 9)
					{
					
						g2d.drawImage(img_Cartas[(card.getValue().ordinal() - 1) * 4 + (int)card.getSuit().ordinal() ], pos.getX(), pos.getY(),(ImageObserver)null);
					}
					else
					{
						int ordinal = card.getValue().ordinal();
						if (ordinal == 0)
						{
							g2d.drawImage(img_Cartas[32 + ((card.getValue().ordinal()) * 4) + (int)card.getSuit().ordinal() ], pos.getX(), pos.getY(),(ImageObserver)null);
						}
						else if (ordinal == 9)
						{
							g2d.drawImage(img_Cartas[img_Cartas.length - 5 + (int)card.getSuit().ordinal() ], pos.getX(), pos.getY(),(ImageObserver)null);
						}
					
					}
				}
			}
			labTotal.setText("Valor da Banca : " + gm.getDealerHandValueView());
		}
		
		
	}
	/**
	 * Returns a Custom-Style JButton Yellow Background with DarkGray Letters for this JPanel
	 * @param text
	 * @return
	 */
	private JButton MyButton(String text)
    {
    	JButton temp = new JButton(text);
    	temp.setForeground(Color.DARK_GRAY); 
    	temp.setBackground(Color.getHSBColor(0.14f,1, 1));
		Border border = BorderFactory.createBevelBorder(0, Color.WHITE,Color.BLACK);
		Border border2 = BorderFactory.createBevelBorder(1,Color.BLACK,Color.WHITE,Color.getHSBColor(0.14f,1, 1),Color.WHITE);
		Border compound = new CompoundBorder(border, border2); 
		temp.setBorder(compound);
		
		return temp;
    	
    }
	
	/**
	 * Adds a Listener for the Save Button
	 * @param save
	 */
	void addSaveListener(ActionListener save) 
	{
		btSalvar.addActionListener(save);
	}
	/**
	 * Adds a Listener for the Restart Button
	 * @param save
	 */
	void addRestartListener(ActionListener rt) 
	{
		btEncerrar.addActionListener(rt);
	}
	
	/**
	 * Specify what needs to be done when the Observable notifies this Observer
	 */
	@Override
	public void notify(Observable o) 
	{
		repaint();
		
	}
	

}
