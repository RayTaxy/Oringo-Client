package oringo.module;

import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class GiantsModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Scale", 1.0D, 0.1D, 5.0D, 0.1D);
   public final BooleanSetting cn_ = new BooleanSetting("Mobs", true);
   public final BooleanSetting field2 = new BooleanSetting("ArmorStands", false);
   public final BooleanSetting field3 = new BooleanSetting("Players", true);

   public GiantsModule() {
      super("Giants", Category.render, SubCategory.world);
      this.method0(new Setting[]{this.field0, this.field3, this.cn_, this.field2});
   }
}
