package map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;
import oringo.module.TerminalESPModule;
import oringo.setting.BooleanSetting;

public class Class507 {
   @Expose
   @SerializedName("offset")
   public int field0;
   @Expose
   @SerializedName("blocks")
   public ConcurrentHashMap field1 = new ConcurrentHashMap();
   @Expose
   @SerializedName("core")
   public int field2;
   @Expose
   @SerializedName("block_count")
   public int field3 = 10;

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Class507 var2 = (Class507)var1;
         return this.field2 == var2.field2;
      } else {
         return false;
      }
   }

   public static void method0(float var0, float var1, int var2, int var3, float var4, int var5) {
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      float var6 = (float)(var5 >> 24 & 255) / 255.0F;
      float var7 = (float)(var5 >> 16 & 255) / 255.0F;
      float var8 = (float)(var5 >> 8 & 255) / 255.0F;
      float var9 = (float)(var5 & 255) / 255.0F;
      GlStateManager.color(var7, var8, var9, var6);
      GL11.glBegin(9);

      for(int var10 = var2; var10 <= var3; ++var10) {
         double var11 = Math.sin((double)var10 * 3.141592653589793D / 180.0D) * (double)var4;
         double var13 = Math.cos((double)var10 * 3.141592653589793D / 180.0D) * (double)var4;
         GL11.glVertex3d((double)var0 + var11, (double)var1 + var13, 0.0D);
      }

      GL11.glEnd();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public Class507(int var1, int var2, int var3) {
      this.field2 = var1;
      this.field0 = var2;
      this.field3 = var3;
   }

   public int hashCode() {
      return this.field2;
   }

   public static Class507 method0(int var0, int var1, int var2) {
      int var3 = 12;

      int var5;
      label57:
      while(true) {
         if (var3 <= 0) {
            return null;
         }

         int[] var4 = new int[4];
         var5 = 0;

         int var6;
         for(var6 = 0; var6 < EnumFacing.HORIZONTALS.length; ++var6) {
            EnumFacing var7 = EnumFacing.HORIZONTALS[var6];
            int var8 = var0 + var7.getFrontOffsetX() * var3;
            int var9 = var1 + var7.getFrontOffsetZ() * var3;
            if (!TerminalESPModule.method0(var8, var9)) {
               return null;
            }

            int var10 = Class475.method0(var8, var9);
            if (var10 == -318865360) {
               return null;
            }

            if (var7 == EnumFacing.NORTH) {
               var5 = var10;
            }

            var4[var6] = var10;
         }

         if (var3 == 1) {
            break;
         }

         var6 = 0;

         while(true) {
            if (var6 >= 4) {
               break label57;
            }

            for(int var12 = 0; var12 < 4; ++var12) {
               if (var12 != var6 && var4[var6] == var4[var12]) {
                  --var3;
                  continue label57;
               }
            }

            ++var6;
         }
      }

      Class507 var11 = new Class507(var2, var5, var3);
      BooleanSetting.method0(var11);
      return var11;
   }
}
