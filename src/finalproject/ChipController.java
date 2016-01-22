package finalproject;

import java.awt.Point;

import finalproject.Enums.Direction;

public class ChipController {
	GameController gc;
	
	public ChipController(GameController gc)
	{
		this.gc = gc;
	}
	
	public void tryMoveChip(ChipTile chip, Direction d, Point target)
	{
		MyTile targetBaseTile = Map.getInst().getBaseTileAt(target.x, target.y);
		MyTile targetEntityTile = Map.getInst().getEntityTileAt(target.x, target.y);
		if(targetBaseTile.isSolid())
		{
			return;
		} else if(targetEntityTile != null)
		{
			if(targetEntityTile.getClass() == DoorTile.class)
			{
				if(chip.hasKey())
				{
					Map.getInst().openDoor();
				} else {
					return;
				}
			} else if(targetEntityTile.getClass() == KeyTile.class) {
				PickupKeyAction act = new PickupKeyAction();
				gc.exec.storeAndExecute(act);
				Map.getInst().removeKey();
			} else if(targetEntityTile.getClass() == EndTile.class) {
				gc.gameWin();
			} else if(targetEntityTile.getClass() == ImmovableTile.class) {
				return;
			}
		}
		MoveAction act = new MoveAction(d);
		gc.exec.storeAndExecute(act);
	}
}