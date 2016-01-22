package finalproject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener {
	
	GameController gameController;
	
	public KeyController(GameController gc)
	{
		gameController = gc;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyPressed = e.getKeyCode();
				
		if(keyPressed < 41 && keyPressed > 36)
		{
			switch(keyPressed)
			{
				case 37:
					gameController.directionEntered(3);
					break;
				case 38:
					gameController.directionEntered(0);
					break;
				case 39:
					gameController.directionEntered(1);
					break;
				case 40:
					gameController.directionEntered(2);
					break;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
