package oringo.event;

import java.io.File;
import map.Class136;
import map.Class362;
import map.Class7;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.shader.Framebuffer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import oringo.module.PopupAnimationModule;

@Cancelable
public class Class274 extends Event {
   public static void method0() {
   }

   public static boolean method0(GuiScreen var0) {
      return Class362.field40.method44() && (var0 instanceof Class7 && PopupAnimationModule.field4.method1() || var0 instanceof GuiInventory && PopupAnimationModule.field2.method1() || var0 instanceof GuiChest && PopupAnimationModule.field3.method1());
   }

   public static File method0(File var0, String var1, int var2, int var3, Framebuffer var4) {
      try {
         File var5 = new File(var0, "screenshots");
         var5.mkdir();
         File var6;
         if (var1 == null) {
            var6 = Class533.method0(var5);
         } else {
            var6 = new File(var5, var1);
         }

         Class255.method0(var2, var3, var4, Class136::lambda$saveScreenshot$1);
         return var6;
      } catch (Exception var7) {
         var7.printStackTrace();
         return null;
      }
   }
}
