package finalproject;

import java.awt.Image;
import java.awt.Point;

import finalproject.Enums.Direction;

public class ChipTile extends MovingTile {
	Point pos;
	Boolean hasKey;
	
	public ChipTile(Image i) {
		super(i);
		pos = new Point(31, 32);
		this.hasKey = false;
	}
	
	public Point getPos()
	{
		return pos;
	}
	
	private void setPos(Point p)
	{
		pos = p;
	}
	
	public void move(Direction d)
	{
		switch(d)
		{
		case NORTH:
			setPos(new Point(pos.x, pos.y-1));
			break;
		case SOUTH:
			setPos(new Point(pos.x, pos.y+1));
			break;
		case WEST:
			setPos(new Point(pos.x-1, pos.y));
			break;
		case EAST:
			setPos(new Point(pos.x+1, pos.y));
			break;
		}
	}
	
	public Boolean hasKey()
	{
		return this.hasKey;
	}
	
	public void getKey()
	{
		this.hasKey = true;
	}
}
