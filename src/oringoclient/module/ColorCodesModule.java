package oringo.module;

import java.util.Iterator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.command.ArmorStandsCommand;
import oringo.event.Class270;
import oringo.mixin.FontRendererAccessor;
import oringo.setting.ButtonSetting;
import oringo.setting.ColorSetting;
import oringo.setting.Setting;

public class ColorCodesModule extends Module {
   public int method0(int var1, boolean var2) {
      ColorSetting var3 = (ColorSetting)this.method47().get(var1 % 16 + 1);
      int var4 = var3.method7();
      int var5 = var3.method2();
      int var6 = var3.method3();
      if (var1 >= 16) {
         var4 /= 4;
         var5 /= 4;
         var6 /= 4;
      }

      return (var2 ? -16777216 : 0) | (var4 & 255) << 16 | (var5 & 255) << 8 | var6 & 255;
   }

   public void lambda$new$0() {
      int var1 = 0;
      Iterator var2 = this.method47().iterator();

      while(var2.hasNext()) {
         Setting var3 = (Setting)var2.next();
         if (var3 instanceof ColorSetting) {
            ((ColorSetting)var3).method0(FastBreakModule.method0(var1++, false));
         }
      }

      if (this.method44()) {
         this.method5();
      }

   }

   public ColorCodesModule() {
      super("Color Codes", Category.render, SubCategory.ui, "Replaces minecraft color codes");

      for(int var1 = 0; var1 < 16; ++var1) {
         this.method0(new ColorCodesModule$1(this, Integer.toHexString(var1) + " color", FastBreakModule.method0(var1, false), false));
      }

      this.method0(new ButtonSetting("Reset colors", this::lambda$new$0));
   }

   public void method5() {
      if (field58.fontRendererObj != null) {
         int[] var1 = ((FontRendererAccessor)field58.fontRendererObj).getColorCodes();
         ArmorStandsCommand.method2();

         for(int var2 = 0; var2 < 32; ++var2) {
            var1[var2] = this.method0(var2, false);
         }

      }
   }

   public void b_() {
      this.method6();
   }

   public void method6() {
      if (field58.fontRendererObj != null) {
         int[] var1 = ((FontRendererAccessor)field58.fontRendererObj).getColorCodes();
         ArmorStandsCommand.method2();

         for(int var2 = 0; var2 < 32; ++var2) {
            var1[var2] = FastBreakModule.method0(var2, false);
         }

      }
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      this.method5();
   }

   public void method4() {
      this.method5();
   }
}
