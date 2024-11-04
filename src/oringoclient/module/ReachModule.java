package oringo.module;

import java.util.concurrent.TimeUnit;
import map.Class479;
import map.Class83;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.lang3.time.DurationFormatUtils;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class ReachModule extends Module {
   public final DoubleSetting field0 = new ReachModule$2(this, "Max Range", 3.0D, 2.0D, 6.0D, 0.01D);
   public final DoubleSetting x_ = (DoubleSetting)(new DoubleSetting("Block Range", 4.5D, 2.0D, 6.0D, 0.01D)).method2("Range for right click");
   public double field2 = 3.0D;
   public final DoubleSetting field3 = new ReachModule$1(this, "Min Range", 3.0D, 2.0D, 6.0D, 0.01D);

   public static void method0(double var0, double var2, double var4, double var6, double var8, int var10) {
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      float var11 = (float)(var10 >> 24 & 255) / 255.0F;
      float var12 = (float)(var10 >> 16 & 255) / 255.0F;
      float var13 = (float)(var10 >> 8 & 255) / 255.0F;
      float var14 = (float)(var10 & 255) / 255.0F;
      var8 /= 2.0D;
      GlStateManager.color(var12, var13, var14, var11);
      Class479.field3.begin(6, DefaultVertexFormats.POSITION);
      byte var15 = 15;

      int var16;
      for(var16 = 0; var16 <= 90; var16 += var15) {
         Class479.field3.pos(var0 + var8 + Math.sin((double)var16 * 3.141592653589793D / 180.0D) * var8 * -1.0D, var2 + var8 + Math.cos((double)var16 * 3.141592653589793D / 180.0D) * var8 * -1.0D, 0.0D).endVertex();
      }

      for(var16 = 90; var16 <= 180; var16 += var15) {
         Class479.field3.pos(var0 + var8 + Math.sin((double)var16 * 3.141592653589793D / 180.0D) * var8 * -1.0D, var6 - var8 + Math.cos((double)var16 * 3.141592653589793D / 180.0D) * var8 * -1.0D, 0.0D).endVertex();
      }

      for(var16 = 0; var16 <= 90; var16 += var15) {
         Class479.field3.pos(var4 - var8 + Math.sin((double)var16 * 3.141592653589793D / 180.0D) * var8, var6 - var8 + Math.cos((double)var16 * 3.141592653589793D / 180.0D) * var8, 0.0D).endVertex();
      }

      for(var16 = 90; var16 <= 180; var16 += var15) {
         Class479.field3.pos(var4 - var8 + Math.sin((double)var16 * 3.141592653589793D / 180.0D) * var8, var2 + var8 + Math.cos((double)var16 * 3.141592653589793D / 180.0D) * var8, 0.0D).endVertex();
      }

      Class479.field1.draw();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static int method0(ItemStack var0) {
      if (var0 == null) {
         return -1;
      } else {
         NBTTagCompound var1 = TargetStrafeModule.method0(var0);
         return var1 != null && var1.hasKey("baseStatBoostPercentage") ? var1.getInteger("baseStatBoostPercentage") : -1;
      }
   }

   public void method1() {
      this.field2 = Class83.method0(this.field3.method0(), this.field0.method0());
   }

   public ReachModule() {
      super("Reach", Category.combat, SubCategory.combat, "Allows you to reach further");
      this.method0(new Setting[]{this.field3, this.field0, this.x_});
   }

   public static String method0(long var0, TimeUnit var2) {
      long var3 = var2.toMillis(var0);
      String var5 = DurationFormatUtils.formatDuration(var3, "d' days 'H'h 'm'm 's's'", true);
      if (var5.startsWith("0 days ")) {
         var5 = var5.substring(7);
      } else if (var5.startsWith("1 days ")) {
         var5 = "1 day" + var5.substring(6);
      }

      return var5;
   }
}
