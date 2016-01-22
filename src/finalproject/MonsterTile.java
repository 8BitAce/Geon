package finalproject;

import java.awt.Image;
import java.awt.Point;

public class MonsterTile extends MovingTile {
	Point pos;
	boolean movedLastTurn;
	
	public MonsterTile(Image i, Point pos) {
		super(i);
		this.solid = true;
		this.pos = pos;
	}
	
	public Point getPos()
	{
		return pos;
	}
	
	public void setPos(Point p)
	{
		pos = p;
	}

}
