package map;

import com.google.gson.JsonElement;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import oringo.module.BlockHitModule;
import oringo.module.HitboxesModule;

public class Class223 implements Class138 {
   public int field0;

   public int method1() {
      this.field0 = 0;
      ItemStack[] var1 = Minecraft.getMinecraft().thePlayer.inventory.armorInventory;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         ItemStack var4 = var1[var3];
         String var5 = BlockHitModule.method0(var4);
         if (var5 != null && (var5.startsWith("FERMENTO") || var5.startsWith("SQUASH"))) {
            ++this.field0;
         }
      }

      return this.field0;
   }

   public long method0(Class532 var1, long var2, long var4) {
      long var6 = this.method0(var2, var4);
      var1.method0("Profit from fermento/d: §6" + var1.method0(var6));
      return var6;
   }

   public boolean method0() {
      return this.method1() > 1;
   }

   public long method0(long var1, long var3) {
      float var5 = 0.0F;
      switch(this.field0) {
      case 2:
         var5 = 20000.0F;
         break;
      case 3:
         var5 = 16666.0F;
         break;
      case 4:
         var5 = 14285.0F;
      }

      return (long)((float)var3 / var5 * 250000.0F);
   }

   public static String method0(JsonElement var0) {
      return var0.getAsString();
   }

   public String method1(long var1, long var3) {
      long var5 = this.method0(var1, var3);
      return "Fermento/d: §6" + HitboxesModule.method0(var5);
   }
}
