package oringo.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.Chunk;

public class Class420 extends Event {
   public final Chunk field0;

   public static void method0(StringBuilder var0, int var1) {
      var0.append('\n');

      for(int var2 = 0; var2 < var1; ++var2) {
         var0.append('\t');
      }

   }

   public static void method0(EntityPainting var0, float var1, float var2) {
      int var3 = MathHelper.floor_double(var0.posX);
      int var4 = MathHelper.floor_double(var0.posY + (double)(var2 / 16.0F));
      int var5 = MathHelper.floor_double(var0.posZ);
      EnumFacing var6 = var0.facingDirection;
      if (var6 == EnumFacing.NORTH) {
         var3 = MathHelper.floor_double(var0.posX + (double)(var1 / 16.0F));
      }

      if (var6 == EnumFacing.WEST) {
         var5 = MathHelper.floor_double(var0.posZ - (double)(var1 / 16.0F));
      }

      if (var6 == EnumFacing.SOUTH) {
         var3 = MathHelper.floor_double(var0.posX - (double)(var1 / 16.0F));
      }

      if (var6 == EnumFacing.EAST) {
         var5 = MathHelper.floor_double(var0.posZ + (double)(var1 / 16.0F));
      }

      int var7 = Minecraft.getMinecraft().theWorld.getCombinedLight(new BlockPos(var3, var4, var5), 0);
      int var8 = var7 % 65536;
      int var9 = var7 / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var8, (float)var9);
      GlStateManager.color(1.0F, 1.0F, 1.0F);
   }

   public Class420(Chunk var1) {
      this.field0 = var1;
   }
}
