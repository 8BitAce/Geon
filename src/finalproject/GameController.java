package finalproject;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import finalproject.Enums.Direction;

public class GameController implements ActionListener {
	
	GameWindowMaker gwm;
	public static int windowWidth = 704;
    public static int windowHeight = 704;
    
    Map map;
    ChipExecutor exec;
    
    MonsterController mc;
    ChipController cc;
		
	public GameController()
	{   

    	map = Map.getInst();
		try {
			map.readInTMX();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        KeyController keyController = new KeyController(this);
		GamePanel panel = new GamePanel(keyController);
		panel.b1.addActionListener(this);
		panel.b2.addActionListener(this);
        panel.setBackground(Color.WHITE);
        gwm = new GameWindowMaker(panel);
        panel.setVisible(true);
        gwm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gwm.setSize(windowWidth, windowHeight);
        gwm.pack();
		gwm.update();
        gwm.setVisible(true);

		
		exec = new ChipExecutor();
		mc = new MonsterController(this);
		cc = new ChipController(this);
	}
		
	public void directionEntered(int dir)
	{
		if(!Map.getInst().won && !Map.getInst().lost)
		{
			ChipTile chip = Map.getInst().getChip();
			if(dir == 0)
			{
				Point target = new Point(chip.getPos().x, chip.getPos().y - 1);
				cc.tryMoveChip(chip, Direction.NORTH, target);
			} else if(dir == 1) {
				Point target = new Point(chip.getPos().x + 1, chip.getPos().y);
				cc.tryMoveChip(chip, Direction.EAST, target);
			} else if(dir == 2) {
				Point target = new Point(chip.getPos().x, chip.getPos().y + 1);
				cc.tryMoveChip(chip, Direction.SOUTH, target);
			} else if(dir == 3) {
				Point target = new Point(chip.getPos().x - 1, chip.getPos().y);
				cc.tryMoveChip(chip, Direction.WEST, target);
			}
			
			mc.tryMoveMonster();
			gwm.update();
		}
	}
	
	public void gameWin()
	{
		Map.getInst().gameWon();
		gwm.update();
	}
	
	public void gameLost()
	{
		Map.getInst().gameLost();
		gwm.update();
	}
	
	public void restart()
	{
    	Map.getInst().reset();
		try {
			map.readInTMX();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		gwm.update();
	}
	
	public void quit()
	{
		Map.getInst().saveState();
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("restart".equals(e.getActionCommand()))
		{
			restart();
		} else {
			quit();
		}
	}
	
}
