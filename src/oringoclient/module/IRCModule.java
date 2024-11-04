package oringo.module;

import map.Class395;
import map.Class409;
import net.minecraft.util.EnumChatFormatting;
import oringo.setting.ColorSetting;
import oringo.setting.Setting;

public class IRCModule extends Module {
   public static final ColorSetting field0 = new ColorSetting("Color", -5177817, false);
   public static final Class409 az_;
   public static final Class409 field2;

   static {
      field2 = new Class409("Name color", EnumChatFormatting.GRAY);
      az_ = new Class409("Message color", EnumChatFormatting.WHITE);
   }

   public static boolean method2() {
      return !Class395.field0.thePlayer.isUsingItem() && !Class395.field0.playerController.getIsHittingBlock();
   }

   public IRCModule() {
      super("IRC", Category.other, SubCategory.ui, "Talk to oringo client users");
      this.method0(new Setting[]{field0, field2, az_});
      this.method1(true);
   }
}
