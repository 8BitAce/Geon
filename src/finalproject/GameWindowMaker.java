package finalproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindowMaker extends JFrame {
	public GamePanel panel;
	
	public GameWindowMaker(GamePanel p)
	{
		super("Chip's Challenge");
		panel = p;
		add(panel, BorderLayout.CENTER);
	}
	
	public void update()
	{
		this.panel.repaintPanel();
	}
}

class GamePanel extends JPanel {
	JButton b1, b2;
	
	public GamePanel(KeyController kc) {
		this.addKeyListener(kc);
		this.setFocusable(true);
		b1 = new JButton("Restart");
        b1.setActionCommand("restart");
        b1.setFocusable(false);
        b2 = new JButton("Save & Quit");
        b2.setActionCommand("quit");
        b2.setFocusable(false);
        add(b1);
        add(b2);
        repaint();
	}
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        ChipTile chip = Map.getInst().getChip();
        Point cPos = chip.getPos();
        for(int i = cPos.x - 5; i <= cPos.x + 5; i++)
        {
        	for(int j = cPos.y - 5; j <= cPos.y + 5; j++)
        	{
        		MyTile bt = Map.getInst().getBaseTileAt(i, j);
        		if(bt != null)
        		{
        			g2.drawImage(bt.getImage().getImage(), (i - cPos.x + 5) * 64, (j - cPos.y + 5) * 64, null);
        		}
        		MyTile dt = Map.getInst().getDecoTileAt(i, j);
        		if(dt != null)
        		{
        			g2.drawImage(dt.getImage().getImage(), (i - cPos.x + 5) * 64, (j - cPos.y + 5) * 64, null);
        		}
        		MyTile et = Map.getInst().getEntityTileAt(i, j);
        		if(et != null)
        		{
        			g2.drawImage(et.getImage().getImage(), (i - cPos.x + 5) * 64, (j - cPos.y + 5) * 64, null);
        		}
        	}
        }
        g2.drawImage(chip.getImage().getImage(), 5 * 64, 5 * 64, null);
        
        if(Map.getInst().won)
        {
        	Image winImage = null;
			try {
				winImage = ImageIO.read(new File("resources/win.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        	g2.drawImage(winImage, 0, 0, null);
        }
        if(Map.getInst().lost)
        {
        	Image winImage = null;
			try {
				winImage = ImageIO.read(new File("resources/lost.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        	g2.drawImage(winImage, 0, 0, null);
        }
    }
    
    public void repaintPanel(){
         repaint();
    }
     
    public Dimension getPreferredSize(){
        return new Dimension(704, 704);
    }

    public Dimension getMinimumSize(){
        return getPreferredSize();
    }
}
