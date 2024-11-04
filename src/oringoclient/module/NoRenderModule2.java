package oringo.module;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;
import oringo.Oringo;
import oringo.event.Class437;

public class NoRenderModule2 extends Module {
   public NoRenderModule2() {
      super("NoRender", Category.render, SubCategory.ui);
   }

   @SubscribeEvent
   public void method0(Class437 var1) {
      if (this.method44()) {
         field58.skipRenderWorld = true;
      }

   }

   public static String lambda$drawGui$2(ScaledResolution var0) throws Exception {
      return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", var0.getScaledWidth(), var0.getScaledHeight(), field58.displayWidth, field58.displayHeight, var0.getScaleFactor());
   }

   public static String lambda$drawGui$1(int var0, int var1) throws Exception {
      return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", var0, var1, Mouse.getX(), Mouse.getY());
   }

   public static String lambda$drawGui$0() throws Exception {
      return field58.currentScreen.getClass().getCanonicalName();
   }

   public static void method0(String var0) {
      if (Oringo.field9.thePlayer != null) {
         Oringo.field9.thePlayer.addChatMessage(new ChatComponentText(var0));
      }

   }

   public void method4() {
      field58.skipRenderWorld = true;
   }

   public void b_() {
      field58.skipRenderWorld = false;
   }
}
