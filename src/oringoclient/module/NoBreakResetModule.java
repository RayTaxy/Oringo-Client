package oringo.module;

import map.Class256;
import map.Class357;
import net.minecraft.client.Minecraft;
import oringo.Oringo;

public class NoBreakResetModule extends Module {
   public NoBreakResetModule() {
      super("No Break Reset", Category.player, SubCategory.mining, "Prevents breaking from resetting when held item is updated e.g. drills with fuel");
   }

   public static void method5() {
      Oringo.field4.method0(new Class256(Class357.field3, Minecraft.getMinecraft().theWorld.getTotalWorldTime()));
   }
}
