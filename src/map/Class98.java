package map;

import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;

public class Class98 {
   public static String method0(ItemStack var0) {
      if (var0.getMetadata() == 3 && var0.hasTagCompound() && var0.getTagCompound().hasKey("SkullOwner")) {
         NBTTagCompound var1 = var0.getTagCompound().getCompoundTag("SkullOwner");
         if (var1.hasKey("Properties")) {
            NBTTagCompound var2 = var1.getCompoundTag("Properties");
            if (var2.hasKey("textures")) {
               NBTTagList var3 = var2.getTagList("textures", 10);
               if (var3.tagCount() != 0) {
                  return var3.getCompoundTagAt(0).getString("Value");
               }
            }
         }
      }

      return null;
   }

   public static void method0(Vec3 var0, double var1, Color var3, Color var4) {
      if (var1 != 0.0D) {
         GL11.glPushMatrix();
         GlStateManager.translate(var0.xCoord - Oringo.field9.getRenderManager().viewerPosX, var0.yCoord - Oringo.field9.getRenderManager().viewerPosY, var0.zCoord - Oringo.field9.getRenderManager().viewerPosZ);
         GlStateManager.disableTexture2D();
         GlStateManager.disableLighting();
         GlStateManager.disableCull();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         GL11.glLineWidth(3.0F);
         Class479.field3.begin(2, DefaultVertexFormats.POSITION_COLOR);

         for(int var5 = 0; var5 <= 120; ++var5) {
            Color var6 = var5 / 6 % 4 == 0 ? var4 : var3;
            double var7 = Math.cos((double)var5 * 3.141592653589793D / 60.0D) * var1;
            double var9 = Math.sin((double)var5 * 3.141592653589793D / 60.0D) * var1;
            Class479.field3.pos(var7, 0.01D, var9).color((float)var6.getRed() / 255.0F, (float)var6.getGreen() / 255.0F, (float)var6.getBlue() / 255.0F, 1.0F).endVertex();
         }

         Class479.field1.draw();
         GL11.glLineWidth(1.0F);
         GlStateManager.enableTexture2D();
         GlStateManager.enableCull();
         GL11.glPopMatrix();
      }
   }
}
