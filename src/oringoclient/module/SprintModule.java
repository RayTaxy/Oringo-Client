package oringo.module;

import map.Class119;
import map.Class196;
import map.Class500;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class210;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class SprintModule extends Module {
   public final EnumSetting field0 = (EnumSetting)(new EnumSetting("Mode", this::lambda$new$1, "Hypixel", new String[]{"Vanilla", "Hypixel"})).method2("Omnisprint bypass mode");
   public final BooleanSetting field1 = (BooleanSetting)(new BooleanSetting("Omnidirectional", false)).method2("Allows sprinting backwards");
   public final BooleanSetting field2 = (BooleanSetting)(new BooleanSetting("Keep", true)).method2("Keep sprinting after a critical hit");

   public static void o_() {
      Class119.field1 = false;
      Class119.field5.clear();
   }

   public SprintModule() {
      super("Sprint", Category.movement, SubCategory.movement, "Toggle sprint");
      this.method0((Setting[])(new Setting[]{this.field2, this.field1, this.field0}));
      this.method0((Class500)(this::lambda$new$0));
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.field0.method0(1) && this.field1.method1()) {
         var1.method0(false);
      }

   }

   public Boolean lambda$new$1() {
      return !this.field1.method1();
   }

   public String lambda$new$0() {
      return this.field1.method1() ? "Omni" : "Keep";
   }

   public static void method8() {
      Class196.field5.method2();
   }

   public static StackTraceElement[] method0(Exception var0) {
      return var0.getStackTrace();
   }
}
