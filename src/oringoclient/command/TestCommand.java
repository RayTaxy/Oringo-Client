package oringo.command;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;

public class TestCommand extends Command {
   public boolean a_() {
      return true;
   }

   public String method1() {
      return null;
   }

   public TestCommand() {
      super("test");
   }

   public void method0(String[] var1) {
      Iterator var2 = field9.theWorld.loadedEntityList.iterator();

      while(var2.hasNext()) {
         Entity var3 = (Entity)var2.next();
         if (var3 instanceof EntityArmorStand) {
            field9.theWorld.removeEntity(var3);
         }
      }

   }
}
