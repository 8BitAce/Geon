package finalproject;

import finalproject.Enums.Direction;

/** The Command for turning on the light - ConcreteCommand #1 */
public class MoveAction implements Action {
   private Direction direction;

   public MoveAction(Direction direction) {
      this.direction = direction;
   }

   @Override
   public void execute() {
      Map.getInst().getChip().move(this.direction);
   }
}
