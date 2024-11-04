package oringo.module;

import oringo.Oringo;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class TimeChangerModule extends Module {
   public final EnumSetting field0 = new EnumSetting("Weather", "Keep", new String[]{"Keep", "Raining", "Thunder", "Sunny", "Snowing"});
   public static final TimeChangerModule cO_ = new TimeChangerModule();
   public final DoubleSetting field2 = new DoubleSetting("Time", 0.0D, 0.0D, 100.0D, 1.0D);
   public final DoubleSetting field3 = new DoubleSetting("Strength", 100.0D, 0.0D, 100.0D, 1.0D);
   public final BooleanSetting field4 = new BooleanSetting("Rotation Only", true);

   public TimeChangerModule() {
      super("Time Changer", Category.render, SubCategory.world);
      this.method0(new Setting[]{this.field2, this.field4, this.field0, this.field3});
   }

   public static void method0(int var0) {
      Oringo.field9.playerController.windowClick(Oringo.field9.thePlayer.inventoryContainer.windowId, var0, 1, 4, Oringo.field9.thePlayer);
   }
}
