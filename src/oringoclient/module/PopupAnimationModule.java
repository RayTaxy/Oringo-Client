package oringo.module;

import map.Class301;
import map.Class343;
import map.Class447;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class PopupAnimationModule extends Module {
   public static final Class447 field0 = new Class447();
   public static final Class343 Q_ = new Class301();
   public static final BooleanSetting field2 = new BooleanSetting("Inventory", false);
   public static final BooleanSetting field3 = new BooleanSetting("Chests", false);
   public static final BooleanSetting field4 = new BooleanSetting("Click Gui", true);
   public static final DoubleSetting field5 = new DoubleSetting("Starting size", 0.75D, 0.0D, 1.0D, 0.05D);
   public static final DoubleSetting field6 = new DoubleSetting("Time", 200.0D, 0.0D, 1000.0D, 10.0D);

   public static void method2(Object var0) {
      PlayerESPModule.method0("OringoClient §7» §f" + var0, ClickGuiModule.field0.method17().getRGB());
   }

   @SubscribeEvent
   public void method1(GuiOpenEvent var1) {
      if (field58.currentScreen == null && field0.a_(150L)) {
         Q_.o_();
      }

   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (field58.currentScreen != null) {
         field0.o_();
      }

   }

   public PopupAnimationModule() {
      super("Popup Animation", Category.render, SubCategory.ui);
      this.method1(true);
      this.method0(new Setting[]{field4, field2, field3, field5, field6});
   }
}
