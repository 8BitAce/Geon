package finalproject;

import java.awt.Point;

public class MonsterController {
	GameController gc;
	
	public MonsterController(GameController gc)
	{
		this.gc = gc;
	}
	
	public void tryMoveMonster()
	{
		ChipTile chip = Map.getInst().getChip();
		MonsterTile monster = Map.getInst().getMonster();
		if(monster.movedLastTurn)
		{
			monster.movedLastTurn = false;
			return;
		}
		monster.movedLastTurn = true;
		if(monster.getPos().x > chip.getPos().x)
		{
			MyTile targetBaseTile = Map.getInst().getBaseTileAt(monster.getPos().x - 1, monster.getPos().y);
			MyTile targetEntityTile = Map.getInst().getEntityTileAt(monster.getPos().x - 1, monster.getPos().y);
			if(!targetBaseTile.isSolid() && targetEntityTile == null)
			{
				Map.getInst().moveMonster(new Point(monster.getPos().x - 1, monster.getPos().y));
				if(chip.getPos().equals(new Point(monster.getPos().x, monster.getPos().y)))
				{
					gc.gameLost();
				}
			}
		} else if(monster.getPos().x < chip.getPos().x) {
			MyTile targetBaseTile = Map.getInst().getBaseTileAt(monster.getPos().x + 1, monster.getPos().y);
			MyTile targetEntityTile = Map.getInst().getEntityTileAt(monster.getPos().x + 1, monster.getPos().y);
			if(!targetBaseTile.isSolid() && targetEntityTile == null)
			{
				Map.getInst().moveMonster(new Point(monster.getPos().x + 1, monster.getPos().y));
				if(chip.getPos().equals(new Point(monster.getPos().x, monster.getPos().y)))
				{
					gc.gameLost();
				}
			}
		}
		
		if(monster.getPos().y > chip.getPos().y)
		{
			MyTile targetBaseTile = Map.getInst().getBaseTileAt(monster.getPos().x, monster.getPos().y - 1);
			MyTile targetEntityTile = Map.getInst().getEntityTileAt(monster.getPos().x, monster.getPos().y - 1);
			if(!targetBaseTile.isSolid() && targetEntityTile == null)
			{
				Map.getInst().moveMonster(new Point(monster.getPos().x, monster.getPos().y - 1));
				if(chip.getPos().equals(new Point(monster.getPos().x, monster.getPos().y )))
				{
					gc.gameLost();
				}
			}
		} else if(monster.getPos().y < chip.getPos().y) {
			MyTile targetBaseTile = Map.getInst().getBaseTileAt(monster.getPos().x, monster.getPos().y + 1);
			MyTile targetEntityTile = Map.getInst().getEntityTileAt(monster.getPos().x, monster.getPos().y + 1);
			if(!targetBaseTile.isSolid() && targetEntityTile == null)
			{
				Map.getInst().moveMonster(new Point(monster.getPos().x, monster.getPos().y + 1));
				if(chip.getPos().equals(new Point(monster.getPos().x, monster.getPos().y)))
				{
					gc.gameLost();
				}
			}
		}
	}
}
