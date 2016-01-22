package finalproject;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

import tiled.core.Tile;

public abstract class MyTile implements Serializable {

	private static final long serialVersionUID = 1L;
	ImageIcon image;
	Boolean solid;
	
	public MyTile(Image i)
	{
		this.image = new ImageIcon(i);
		this.solid = false;
	}
	
	public ImageIcon getImage()
	{
		return this.image;
	}
	
	public Boolean isSolid()
	{
		return solid;
	}
}
