package map;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.module.FastBreakModule;
import oringo.module.NoBreakResetModule;
import oringo.module.NoFallModule;
import oringo.module.NoRotateModule;
import oringo.module.VelocityModule;

public class Class357 {
   public int field0 = 0;
   public static String field1 = "";
   public static boolean field2 = false;
   public static String field3 = "";
   public static String field4 = "";

   public static void method0(double var0, double var2, double var4) {
      GL11.glTranslated(-(var0 - Oringo.field9.getRenderManager().viewerPosX), -(var2 - Oringo.field9.getRenderManager().viewerPosY), -(var4 - Oringo.field9.getRenderManager().viewerPosZ));
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.END) {
         if (this.field0++ % 50 == 0) {
            if (NoRotateModule.method3()) {
               NoFallModule.method3();
               field1 = "";
               field2 = false;
               return;
            }

            if (VelocityModule.method9()) {
               NoBreakResetModule.method5();
               field1 = field3;
               field2 = true;
            }

            if (!field2 || !Class496.field1) {
               return;
            }

            FastBreakModule.method5();
         }

      }
   }
}
