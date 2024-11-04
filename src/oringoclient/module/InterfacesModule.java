package oringo.module;

import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class InterfacesModule extends Module {
   public final BooleanSetting field0 = new BooleanSetting("Blur", true);
   public final BooleanSetting cv_ = new BooleanSetting("Custom chat font", false, this::lambda$new$0);
   public final BooleanSetting field2 = new BooleanSetting("Chat in gui", false);
   public final BooleanSetting field3 = new BooleanSetting("No chat background", false);
   public final BooleanSetting field4 = new BooleanSetting("Custom chat", true);
   public final BooleanSetting field5 = new BooleanSetting("Shadow", false);

   public InterfacesModule() {
      super("Interfaces", Category.render, SubCategory.ui);
      this.method0(new Setting[]{this.field4, this.field2, this.field3, this.cv_, this.field0, this.field5});
      this.method1(true);
   }

   public Boolean lambda$new$0() {
      return !this.field4.method1();
   }
}
