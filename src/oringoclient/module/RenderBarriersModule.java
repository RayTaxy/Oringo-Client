package oringo.module;

import map.Class253;
import map.Class426;
import net.minecraft.event.ClickEvent;
import oringo.Oringo;

public class RenderBarriersModule extends Module {
   public static RenderBarriersModule field0 = new RenderBarriersModule();

   public static void method0(int var0) {
      Oringo.field9.thePlayer.inventory.currentItem = var0;
      Class426.method10();
   }

   public static void method1(Object var0, ClickEvent var1) {
      if (Oringo.field9.thePlayer != null) {
         Class253.Class0 var2 = new Class253.Class0("OringoClient §7» §f" + var0, ClickGuiModule.field0.method17().getRGB());
         var2.getChatStyle().setChatClickEvent(var1);
         Oringo.field9.thePlayer.addChatMessage(var2);
      }

   }

   public RenderBarriersModule() {
      super("Render Barriers", Category.render, SubCategory.world, "Enables barrier rendering");
   }

   public void e_() {
      try {
         field58.renderGlobal.loadRenderers();
      } catch (Exception var2) {
      }

      super.e_();
   }
}
