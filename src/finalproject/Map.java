package finalproject;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import tiled.core.Tile;
import tiled.core.TileLayer;
import tiled.io.TMXMapReader;

public class Map implements Serializable {

	private static final long serialVersionUID = 1L;

	public static MapRepo mr = new MapRepo();
	static Map inst;
	
	private ArrayList<ArrayList<MyTile>> baseMap;
	private ArrayList<ArrayList<MyTile>> decoMap;
	private ArrayList<ArrayList<MyTile>> entityMap;
	
	ChipTile chip;
	MonsterTile monster;
	KeyTile key;
	DoorTile doorLeftTop;
	DoorTile doorLeftBottom;
	DoorTile doorRightTop;
	DoorTile doorRightBottom;
	EndTile end;
	
	public Boolean won;
	public Boolean lost;
	
	private Map()
	{
		this.baseMap = new ArrayList<ArrayList<MyTile>>();
		this.decoMap = new ArrayList<ArrayList<MyTile>>();
		this.entityMap = new ArrayList<ArrayList<MyTile>>();
		for(int i = 0; i <= 38; i++)
		{
			this.baseMap.add(new ArrayList<MyTile>(38));
			this.decoMap.add(new ArrayList<MyTile>(38));
			this.entityMap.add(new ArrayList<MyTile>(38));
			for(int j = 0; j <= 38; j++)
			{
				this.baseMap.get(i).add(null);
				this.decoMap.get(i).add(null);
				this.entityMap.get(i).add(null);
			}
		}
		
		Image chipImage = null;
		try {
			chipImage = ImageIO.read(new File("resources/Chip.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.chip = new ChipTile(chipImage);
		
		won = false;
		lost = false;
	}
	
	public static Map getInst()
	{
		if(inst != null)
		{
			return inst;
		} else if(mr.getState() != null){
			inst = mr.getState().map;
			return inst;
		} else {
			inst = new Map();
			mr.setState(new MapMomento(inst));
			return inst;
		}
	}
	
	public void saveState()
	{
		mr.saveState();
	}
	
	public void reset()
	{
		this.baseMap = new ArrayList<ArrayList<MyTile>>();
		this.decoMap = new ArrayList<ArrayList<MyTile>>();
		this.entityMap = new ArrayList<ArrayList<MyTile>>();
		for(int i = 0; i <= 38; i++)
		{
			this.baseMap.add(new ArrayList<MyTile>(38));
			this.decoMap.add(new ArrayList<MyTile>(38));
			this.entityMap.add(new ArrayList<MyTile>(38));
			for(int j = 0; j <= 38; j++)
			{
				this.baseMap.get(i).add(null);
				this.decoMap.get(i).add(null);
				this.entityMap.get(i).add(null);
			}
		}
		
		Image chipImage = null;
		try {
			chipImage = ImageIO.read(new File("resources/Chip.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.chip = new ChipTile(chipImage);
		
		this.won = false;
		this.lost = false;
	}
	
	public void readInTMX() throws Exception
	{
		File mapFile = new File("resources/map.tmx");

		tiled.core.Map reader = new TMXMapReader().readMap("resources/map.tmx");
		for(int i = 0; i <= 38; i++)
		{
			for(int j = 0; j <= 38; j++)
			{
				Tile bt = ((TileLayer) reader.getLayer(0)).getTileAt(i, j);
				if(bt != null)
				{
					if(bt.getId() <= 6)
					{
						FloorTile ft = new FloorTile(bt.getImage());
						baseMap.get(j).add(i, ft);
					} else {
						WallTile wt = new WallTile(bt.getImage());
						baseMap.get(j).add(i, wt);
					}
				}
				
				Tile dt = ((TileLayer) reader.getLayer(1)).getTileAt(i, j);
				if(dt != null)
				{
					DecoTile decoTile = new DecoTile(dt.getImage());
					decoMap.get(j).set(i, decoTile);
				}
				
				Tile et = ((TileLayer) reader.getLayer(2)).getTileAt(i, j);
				if(et != null)
				{
					if(et.getId() == 118)
					{
						KeyTile kt = new KeyTile(et.getImage());
						entityMap.get(j).set(i, kt);
						key = kt;
					} else if(et.getId() == 34) {
						DoorTile doort = new DoorTile(et.getImage());
						entityMap.get(j).set(i, doort);
						doorLeftTop = doort;
					}  else if(et.getId() == 44) {
						DoorTile doort = new DoorTile(et.getImage());
						entityMap.get(j).set(i, doort);
						doorLeftBottom = doort;
					} else if(et.getId() == 35) {
						DoorTile doort = new DoorTile(et.getImage());
						entityMap.get(j).set(i, doort);
						doorRightTop = doort;
					} else if(et.getId() == 45) {
						DoorTile doort = new DoorTile(et.getImage());
						entityMap.get(j).set(i, doort);
						doorRightBottom = doort;
					} else if(et.getId() == 115 || et.getId() == 109 || et.getId() == 77 || et.getId() == 87 || et.getId() == 100) {
						ImmovableTile bbtile = new ImmovableTile(et.getImage());
						entityMap.get(j).set(i, bbtile);
					}
				}
			}
		}
		Image endTileImage = ImageIO.read(new File("resources/End.png"));
		EndTile endt = new EndTile(endTileImage);
		entityMap.get(7).set(8, endt);
		end = endt;
		
		int randomX;
		int randomY;
		do
		{
			randomX = ThreadLocalRandom.current().nextInt(0, 30 + 1);
			randomY = ThreadLocalRandom.current().nextInt(0, 30 + 1);
		}while(this.getBaseTileAt(randomX, randomY).solid == true || this.getBaseTileAt(randomX, randomY).solid == true);
		Image monsterImage = ImageIO.read(new File("resources/monster.png"));
		MonsterTile monstert = new MonsterTile(monsterImage, new Point(randomX, randomY));
		entityMap.get(randomY).set(randomX, monstert);
		monster = monstert;

	}
	
	public MyTile getBaseTileAt(int x, int y)
	{
		return baseMap.get(y).get(x);
	}
	
	public MyTile getDecoTileAt(int x, int y)
	{
		return decoMap.get(y).get(x);
	}
	
	public MyTile getEntityTileAt(int x, int y)
	{
		return entityMap.get(y).get(x);
	}
	
	public ChipTile getChip()
	{
		return this.chip;
	}
	
	public MonsterTile getMonster()
	{
		return this.monster;
	}
	
	public void removeKey()
	{
		for(int i = 0; i <= 38; i++)
		{
			for(int j = 0; j <= 38; j++)
			{
				if(entityMap.get(i).get(j) == key)
				{
					entityMap.get(i).set(j, null);
				}
			}
		}
	}
	
	public void openDoor()
	{
		for(int i = 0; i <= 38; i++)
		{
			for(int j = 0; j <= 38; j++)
			{
				if(entityMap.get(i).get(j) == doorLeftTop || entityMap.get(i).get(j) == doorLeftBottom || entityMap.get(i).get(j) == doorRightTop || entityMap.get(i).get(j) == doorRightBottom)
				{
					entityMap.get(i).set(j, null);
				}
			}
		}
	}
	
	public void gameWon()
	{
		this.won = true;
	}
	
	public void gameLost()
	{
		this.lost = true;
	}
	
	public void moveMonster(Point pos)
	{
		for(int i = 0; i <= 38; i++)
		{
			for(int j = 0; j <= 38; j++)
			{
				if(entityMap.get(i).get(j) == monster)
				{
					entityMap.get(i).set(j, null);
				}
			}
		}
		entityMap.get(pos.y).set(pos.x, monster);
		monster.setPos(pos);
	}
	
}
