package oringo.module;

import java.awt.Color;
import map.Class462;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class210;

public class DerpModule extends Module {
   public float t_;

   public DerpModule() {
      super("Derp", Category.other, SubCategory.other, (String)null);
   }

   public static Color method2() {
      return FlightModule.method7().darker();
   }

   @SubscribeEvent(
      priority = EventPriority.LOWEST
   )
   public void method0(Class210.Class1 var1) {
      if (this.method44()) {
         var1.t_ = MathHelper.wrapAngleTo180_float(this.t_ += 45.0F);
         var1.bF_ = 90.0F;
      }
   }

   public static boolean method0(Gui var0) {
      if (var0 instanceof GuiChest && ((GuiChest)var0).inventorySlots instanceof ContainerChest) {
         ContainerChest var1 = (ContainerChest)((GuiChest)var0).inventorySlots;
         return Class462.method0(var1.getLowerChestInventory().getName());
      } else {
         return false;
      }
   }
}
