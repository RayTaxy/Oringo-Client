package map;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class Class245 {
   public static int field0;
   public static final Minecraft field1 = Minecraft.getMinecraft();

   @SubscribeEvent
   public void method0(RenderTickEvent var1) {
      if (var1.phase == Phase.START) {
         field0 = 0;
      }

   }
}
