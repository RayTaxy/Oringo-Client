package oringo.module;

import oringo.setting.Setting;
import oringo.setting.StringSetting;

public class NickHiderModule extends Module {
   public static final StringSetting field0 = new StringSetting("Username");
   public static boolean field1 = false;

   public boolean F_() {
      return field0.F_();
   }

   public NickHiderModule() {
      super("Nick Hider", Category.render, SubCategory.ui);
      this.method0(new Setting[]{field0});
   }
}
