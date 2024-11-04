package oringo.module;

import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class NoJumpBoostModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Only in Dungeon", true);

   public static StringBuilder method0(StringBuilder var0, String var1) {
      return var0.append(var1);
   }

   public NoJumpBoostModule() {
      super("No Jump Boost", Category.movement, SubCategory.movement, "Disables jump boost");
      this.method0(new Setting[]{this.field0});
   }
}
