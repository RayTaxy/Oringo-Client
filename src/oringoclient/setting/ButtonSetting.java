package oringo.setting;

import map.Class1;
import map.Class12;
import map.Class157;
import map.Class208;
import map.Class500;
import net.minecraft.util.BlockPos;
import oringo.module.FragHelperModule;
import oringo.module.LividFinderModule;
import oringo.module.PestESPModule;

public class ButtonSetting extends Setting {
   public final Runnable field0;

   public static int method0(BlockPos var0) {
      Class12 var1 = FragHelperModule.method0(var0.getX(), var0.getZ());
      Class208 var2 = LividFinderModule.method0(var1.method1(), var1.method0());
      Class1 var3 = Class157.method0(var0, var1, var2);
      if (var3 == null) {
         return -1;
      } else {
         var3.a_ = !var3.a_;
         PestESPModule.n_();
         return var3.a_ ? 1 : 0;
      }
   }

   public ButtonSetting(String var1, Runnable var2, Class500 var3) {
      super(var1, var3);
      this.field0 = var2;
   }

   public void method0() {
      this.field0.run();
   }

   public ButtonSetting(String var1, Runnable var2) {
      super(var1);
      this.field0 = var2;
   }
}
