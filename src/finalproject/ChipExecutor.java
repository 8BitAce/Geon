package finalproject;

import java.util.List;
import java.util.ArrayList;

/** The Invoker class */
public class ChipExecutor {
   private List<Action> history = new ArrayList<Action>();

   public void storeAndExecute(Action cmd) {
      this.history.add(cmd); 
      cmd.execute();
   }
}