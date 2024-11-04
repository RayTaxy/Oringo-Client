package oringo.module;

import java.util.regex.Pattern;
import map.Class409;
import net.minecraft.util.EnumChatFormatting;
import oringo.setting.Setting;

public class PlusColorChangerModule extends Module {
   public static String D_ = null;
   public Class409 E_;
   public static PlusColorChangerModule field2 = new PlusColorChangerModule();
   public static Pattern field3 = null;

   public static void method0(String var0, Object... var1) {
      NoRenderModule2.method0(String.format(var0, var1));
   }

   public PlusColorChangerModule() {
      super("Plus Color Changer", Category.render, SubCategory.other, "Changes the color of your MVP+ or MVP++ rank\nClient sided");
      this.E_ = new Class409("Plus color", EnumChatFormatting.DARK_BLUE);
      this.method0(new Setting[]{this.E_});
   }
}
