package oringo.module;

import map.Class357;
import map.Class496;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import oringo.setting.BooleanSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class NoRotateModule extends Module {
   public final EnumSetting field0 = new EnumSetting("Mode", "Hypixel", new String[]{"Hypixel"});
   public final BooleanSetting bd_ = new BooleanSetting("Keep motion", false);
   public final BooleanSetting field2 = (BooleanSetting)(new BooleanSetting("Ignore 0 pitch", true)).method2("Disables if the pitch is equal to 0. Useful for teleport maze");

   public static boolean method0(MovingObjectPosition var0, ScaffoldModule.Class0 var1) {
      if (var0 == null) {
         return false;
      } else {
         return var0.sideHit == var1.field3 && var1.field0.equals(var0.getBlockPos());
      }
   }

   public NoRotateModule() {
      super("No Rotate", 0, Category.player, SubCategory.player, "Prevents the server from setting your rotation when teleporting");
      this.method0(new Setting[]{this.field0, this.field2, this.bd_});
      EnumSetting var10001 = this.field0;
      this.method0(var10001::method4);
   }

   public static boolean method3() {
      return Class357.field2 && (!Class496.field1 || Minecraft.getMinecraft().theWorld == null);
   }

   public static String method0(int var0, int var1, int var2) {
      return String.format("§%s%s§r", var0 > var2 ? (var0 > var1 ? "c" : "e") : "a", var0);
   }
}
