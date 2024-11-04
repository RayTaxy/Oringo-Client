package oringo.module;

import java.util.UUID;
import map.Class469;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class HitboxesModule extends Module {
   public final DoubleSetting field0 = (DoubleSetting)(new DoubleSetting("Expand", 0.5D, 0.1D, 1.0D, 0.1D)).method2("The amount to expand");
   public final BooleanSetting cp_ = new BooleanSetting("Only players", false);

   public static void method0(double var0, double var2, double var4, double var6, double var8, int var10) {
      ReachModule.method0(var0, var2, var0 + var4, var2 + var6, var8, var10);
   }

   public HitboxesModule() {
      super("Hitboxes", Category.combat, SubCategory.combat, "Expands player hitboxes");
      this.method0(new Setting[]{this.field0});
   }

   public static UUID method0(ItemStack var0) {
      if (var0 == null) {
         return null;
      } else {
         NBTTagCompound var1 = TargetStrafeModule.method0(var0);
         return var1 != null && var1.hasKey("uuid", 8) ? UUID.fromString(var1.getString("uuid")) : null;
      }
   }

   public void method4() {
      super.method4();
      if (field58.theWorld != null && Class469.method0()) {
         method0("§c[WARNING] Hitboxes module increases your opponent's hitbox size, which is highly bannable on hypixel!");
      }

   }

   public static String method0(long var0) {
      long var2 = Math.abs(var0);
      if ((double)var2 >= 1.0E9D) {
         return ((double)var2 >= 1.0E10D ? (double)((long)((double)var0 / 1.0E9D)) : (double)((long)((double)var0 / 1.0E8D)) / 10.0D) + "B";
      } else if ((double)var2 >= 1000000.0D) {
         return (double)var2 >= 1.0E7D ? (long)((double)var0 / 1000000.0D) + "M" : (double)((long)((double)var0 / 100000.0D)) / 10.0D + "M";
      } else if ((double)var2 >= 1000.0D) {
         return (double)var2 >= 10000.0D ? (long)((double)var0 / 1000.0D) + "k" : (double)((long)((double)var0 / 100.0D)) / 10.0D + "k";
      } else {
         return String.valueOf(var0);
      }
   }
}
