package oringo.module;

import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class InventoryHUDModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("X1234  ", 0.03D, -100000.0D, 100000.0D, 1.0E-5D, InventoryHUDModule::lambda$new$0);
   public final BooleanSetting bP_ = new BooleanSetting("Outline", true);
   public final DoubleSetting field2 = new DoubleSetting("Y1234  ", 0.15D, -100000.0D, 100000.0D, 1.0E-5D, InventoryHUDModule::lambda$new$1);
   public final BooleanSetting field3 = new BooleanSetting("Blur", true);

   public static Boolean lambda$new$1() {
      return true;
   }

   public InventoryHUDModule() {
      super("Inventory HUD", 0, Category.render, SubCategory.ui);
      this.method0(new Setting[]{this.field0, this.field2, this.field3, this.bP_});
   }

   public static Boolean lambda$new$0() {
      return true;
   }
}
