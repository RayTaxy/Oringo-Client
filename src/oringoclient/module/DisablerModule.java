package oringo.module;

import map.Class7;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;
import oringo.event.Class332;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class DisablerModule extends Module {
   public final EnumSetting field0 = new EnumSetting("Mode", "Hypixel", new String[]{"Hypixel"});

   public DisablerModule() {
      super("Disabler", Category.other, SubCategory.other, "Disables some anti cheat checks");
      this.method0(new Setting[]{this.field0});
      EnumSetting var10001 = this.field0;
      this.method0(var10001::method4);
   }

   @SubscribeEvent(
      priority = EventPriority.HIGH
   )
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C0EPacketClickWindow) {
         C0EPacketClickWindow var2 = (C0EPacketClickWindow)var1.field0;
         if (var2.getWindowId() == field58.thePlayer.inventoryContainer.windowId && var2.getMode() != 0 && field58.thePlayer.inventory.getItemStack() == null) {
            method2(new C0DPacketCloseWindow(var2.getWindowId()));
         }
      }

   }

   public static int method5() {
      return Mouse.getX() * Class7.field2.getScaledWidth() / Class7.field1.displayWidth;
   }
}
