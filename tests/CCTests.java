import static org.junit.Assert.*;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.Test;

import finalproject.*;
import finalproject.Enums.Direction;

public class CCTests {

	@Test
	public void testMapCreation()
	{
		GameController gc = new GameController();
		assertNotNull(Map.getInst());
	}
	
	@Test
	public void testBaseMapValidity()
	{
		GameController gc = new GameController();
		for(int i = 0; i <= 38; i++)
		{
			for(int j = 0; j <= 38; j++)
			{
				assertNotNull(Map.getInst().getBaseTileAt(i, j));
			}
		}
	}
	
	@Test
	public void testDecoMapValidity()
	{
		GameController gc = new GameController();
		boolean decoExists = false;
		for(int i = 0; i <= 38; i++)
		{
			for(int j = 0; j <= 38; j++)
			{
				if(Map.getInst().getDecoTileAt(i, j) != null)
				{
					decoExists = true;
				}
			}
		}
		assertTrue(decoExists);
	}
	
	@Test
	public void testEntityMapValidity()
	{
		GameController gc = new GameController();
		boolean entExists = false;
		for(int i = 0; i <= 38; i++)
		{
			for(int j = 0; j <= 38; j++)
			{
				if(Map.getInst().getEntityTileAt(i, j) != null)
				{
					entExists = true;
				}
			}
		}
		assertTrue(entExists);
	}
	
	@Test
	public void testDragonCreation()
	{
		GameController gc = new GameController();
		assertTrue(Map.getInst().getMonster().getPos().x <= 38);
		assertTrue(Map.getInst().getMonster().getPos().y <= 38);
	}
	
	@Test
	public void testChipCreation()
	{
		GameController gc = new GameController();
		assertEquals(Map.getInst().getChip().getPos().x, 31);
		assertEquals(Map.getInst().getChip().getPos().y, 32);
	}
	
	@Test
	public void testOpenDoor()
	{
		GameController gc = new GameController();
		Map.getInst().openDoor();
		for(int i = 0; i <= 38; i++)
		{
			for(int j = 0; j <= 38; j++)
			{
				assertTrue(Map.getInst().getEntityTileAt(i, j).getClass() != DoorTile.class);
			}
		}
	}
	
	@Test
	public void testRemoveKey()
	{
		GameController gc = new GameController();
		Map.getInst().openDoor();
		for(int i = 0; i <= 38; i++)
		{
			for(int j = 0; j <= 38; j++)
			{
				assertTrue(Map.getInst().getEntityTileAt(i, j).getClass() != KeyTile.class);
			}
		}
	}
	
	@Test
	public void testMoveMonster()
	{
		GameController gc = new GameController();
		Map.getInst().moveMonster(new Point(30,32));
		assertTrue(Map.getInst().getEntityTileAt(30, 32).getClass() == MonsterTile.class);
	}
	
	@Test
	public void testMoveChip()
	{
		GameController gc = new GameController();
		Map.getInst().getChip().move(Direction.WEST);
		assertTrue(Map.getInst().getEntityTileAt(30, 32).getClass() == ChipTile.class);
	}
	
	@Test
	public void testChipGetKey()
	{
		GameController gc = new GameController();
		Map.getInst().getChip().getKey();
		assertTrue(Map.getInst().getChip().hasKey());
	}
	
	@Test
	public void testGameWon()
	{
		GameController gc = new GameController();
		gc.gameWin();
		assertTrue(Map.getInst().won);
	}
	
	@Test
	public void testGameLost()
	{
		GameController gc = new GameController();
		gc.gameLost();
		assertTrue(Map.getInst().lost);
	}
	
	@Test
	public void testMomentoSave()
	{
		GameController gc = new GameController();
		Map.getInst().saveState();
	    try {
	    	FileInputStream fileIn = new FileInputStream("map.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testMapRepo()
	{
		GameController gc = new GameController();
		assertNotNull(Map.mr);
	}
	
}
