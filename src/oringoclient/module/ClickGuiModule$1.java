package oringo.module;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import map.Class311;
import oringo.setting.EnumSetting;

final class ClickGuiModule$1 extends EnumSetting {
   ClickGuiModule$1(String var1, String var2, String... var3) {
      super(var1, var2, var3);
      Font[] var4 = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         Font var7 = var4[var6];
         this.method3().add(var7.getName());
      }

   }

   public void d_(String var1) {
      super.d_(var1);
      Class311.method0();
   }
}
