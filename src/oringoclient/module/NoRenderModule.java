package oringo.module;

import java.util.ArrayList;
import java.util.List;
import map.Class203;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.lwjgl.opengl.Display;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class NoRenderModule extends Module {
   public static long field0;
   public static boolean aK_;
   public static final BooleanSetting field2 = new BooleanSetting("Only Hidden", true);
   public static final BooleanSetting field3;
   public static boolean field4;
   public static boolean field5 = true;
   public static List field6 = new ArrayList();

   public NoRenderModule() {
      super("No Render", Category.render, SubCategory.ui);
      this.method0(new Setting[]{field2, field3});
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.END) {
         field4 = Display.isActive();
         aK_ = Display.isVisible();
      }
   }

   public static ChatLine method0(GuiNewChat var0, int var1, int var2) {
      return ((Class203)var0).method0(var1, var2);
   }

   static {
      BooleanSetting var10004 = field2;
      var10004.getClass();
      field3 = new BooleanSetting("1 fps", false, var10004::method1);
      field0 = 0L;
      field4 = true;
      aK_ = true;
   }
}
