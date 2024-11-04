package oringo.module;

import net.minecraft.network.play.client.C0EPacketClickWindow;
import oringo.setting.BooleanSetting;
import oringo.setting.Setting;

public class AntiSpectateModule extends Module {
   public static final BooleanSetting field0 = (BooleanSetting)(new BooleanSetting("Reveal name", true)).method2("Attempts to reveal the staff's name");

   public static double[] method0(double var0) {
      if (!AutoReconnectModule.method1()) {
         return new double[]{0.0D, 0.0D};
      } else {
         double var2 = DadudzeModule.method3();
         double var4 = -Math.sin(var2) * var0;
         double var6 = Math.cos(var2) * var0;
         return new double[]{var4, var6};
      }
   }

   public AntiSpectateModule() {
      super("Anti Spectate", Category.other, SubCategory.other, "Shows when staff is spectating you");
      this.method1(true);
      this.method0(new Setting[]{field0});
   }

   public static int method0(C0EPacketClickWindow var0) {
      return var0.getWindowId();
   }
}
