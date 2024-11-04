package oringo.module;

import map.Class479;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BlockHitModule extends Module {
   public static String method0(ItemStack var0) {
      if (var0 == null) {
         return null;
      } else {
         NBTTagCompound var1 = TargetStrafeModule.method0(var0);
         return var1 != null && var1.hasKey("id", 8) ? var1.getString("id") : null;
      }
   }

   public BlockHitModule() {
      super("Block Hit", Category.combat, SubCategory.combat, "Allows you to hit while blocking");
   }

   public static void method0(float var0, float var1, float var2, float var3, int var4, int var5) {
      float var6;
      if (var0 < var2) {
         var6 = var0;
         var0 = var2;
         var2 = var6;
      }

      if (var1 < var3) {
         var6 = var1;
         var1 = var3;
         var3 = var6;
      }

      var6 = (float)(var4 >> 24 & 255) / 255.0F;
      float var7 = (float)(var4 >> 16 & 255) / 255.0F;
      float var8 = (float)(var4 >> 8 & 255) / 255.0F;
      float var9 = (float)(var4 & 255) / 255.0F;
      float var10 = (float)(var5 >> 24 & 255) / 255.0F;
      float var11 = (float)(var5 >> 16 & 255) / 255.0F;
      float var12 = (float)(var5 >> 8 & 255) / 255.0F;
      float var13 = (float)(var5 & 255) / 255.0F;
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.shadeModel(7425);
      GlStateManager.color(var7, var8, var9, var6);
      Class479.field3.begin(7, DefaultVertexFormats.POSITION_COLOR);
      Class479.field3.pos((double)var0, (double)var3, 0.0D).color(var11, var12, var13, var10).endVertex();
      Class479.field3.pos((double)var2, (double)var3, 0.0D).color(var7, var8, var9, var6).endVertex();
      Class479.field3.pos((double)var2, (double)var1, 0.0D).color(var7, var8, var9, var6).endVertex();
      Class479.field3.pos((double)var0, (double)var1, 0.0D).color(var11, var12, var13, var10).endVertex();
      Class479.field1.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }
}
