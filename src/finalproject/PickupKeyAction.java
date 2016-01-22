package finalproject;

/** The Action for picking up the key */
public class PickupKeyAction implements Action {

   public PickupKeyAction() {
   }

   @Override
   public void execute() {
      Map.getInst().getChip().getKey();
   }
}
