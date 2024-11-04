package oringo.module;

import java.awt.Color;
import java.awt.TrayIcon.MessageType;
import map.Class355;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;

public class HostageESPModule extends Module {
   public Entity field0 = null;

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (field58.theWorld != null && field58.theWorld.getSkylightSubtracted() == 11) {
         if (var1.field0 instanceof S13PacketDestroyEntities) {
            int[] var2 = ((S13PacketDestroyEntities)var1.field0).getEntityIDs();
            if (var2.length == 1 && var2[0] == 140) {
               var1.method9();
               this.field0 = field58.theWorld.getEntityByID(140);
            }
         }

      }
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (field58.theWorld.getSkylightSubtracted() == 11) {
         if (this.field0 != null && !this.field0.isDead) {
            AutoIceFillModule.method0(this.field0, var1.partialTicks, Color.GREEN);
         }
      }
   }

   public static void method0(String var0, MessageType var1) {
      if (Class355.field0) {
         Class355.field1.displayMessage("Oringo Client", var0, var1);
      }
   }

   public HostageESPModule() {
      super("Hostage ESP", Category.render, SubCategory.visual, "ESP for hostages in crimson island");
   }
}
