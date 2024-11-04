package oringo.module;

import map.Class496;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;

public class AutoCloseModule extends Module {
   public static void method5() {
      GlStateManager.color(1.0F, 1.0F, 1.0F);
   }

   public static String method6() {
      ScoreObjective var0 = Class496.field26.thePlayer.getWorldScoreboard().getObjectiveInDisplaySlot(1);
      return var0 == null ? "" : var0.getDisplayName();
   }

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (Class496.field5) {
         if (var1.field0 instanceof S2DPacketOpenWindow) {
            S2DPacketOpenWindow var2 = (S2DPacketOpenWindow)var1.field0;
            String var3 = var2.getWindowTitle().getUnformattedText();
            if (var3.equals("Chest") || var3.equals("Large Chest")) {
               var1.method9();
               method3(new C0DPacketCloseWindow(var2.getWindowId()));
            }
         }

      }
   }

   public AutoCloseModule() {
      super("Auto Close", Category.dungeon, SubCategory.main, "Closes dungeon chests");
   }
}
