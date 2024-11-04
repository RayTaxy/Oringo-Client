package map;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.nbt.NBTBase;
import org.lwjgl.opengl.GL11;
import oringo.event.Class420;

public class Class168 {
   public static final WorldRenderer field0;
   public static final Tessellator field1 = Tessellator.getInstance();

   static {
      field0 = field1.getWorldRenderer();
   }

   public static String method0(NBTBase var0) {
      if (var0 == null) {
         return "null";
      } else {
         String var1 = var0.toString();
         StringBuilder var2 = new StringBuilder();
         int var3 = 0;
         boolean var4 = false;
         boolean var5 = false;
         char[] var6 = var1.toCharArray();
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            char var9 = var6[var8];
            if (!var4) {
               if (var9 == '"') {
                  var5 = !var5;
               }

               if (!var5) {
                  switch(var9) {
                  case ',':
                     var2.append(var9);
                     Class420.method0(var2, var3);
                     continue;
                  case ':':
                     var2.append(var9);
                     var2.append(' ');
                     continue;
                  case '[':
                  case '{':
                     var2.append(var9);
                     ++var3;
                     Class420.method0(var2, var3);
                     continue;
                  case ']':
                  case '}':
                     --var3;
                     Class420.method0(var2, var3);
                     var2.append(var9);
                     continue;
                  }
               }
            }

            var2.append(var9);
            var4 = var9 == '\\';
         }

         return var2.toString();
      }
   }

   public static void method0(EntityPainting var0, int var1, int var2, int var3, int var4) {
      int var5 = var1 / 2;
      int var6 = var2 / 2;
      float var7 = (float)var3 / 256.0F;
      float var8 = (float)(var3 + var1) / 256.0F;
      float var9 = (float)var4 / 256.0F;
      float var10 = (float)(var4 + var2) / 256.0F;
      Class420.method0(var0, 0.0F, 0.0F);
      Tessellator var11 = Tessellator.getInstance();
      WorldRenderer var12 = var11.getWorldRenderer();
      var12.begin(7, DefaultVertexFormats.POSITION_TEX);
      var12.pos((double)(-var5), (double)(-var6), 0.0D).tex((double)var8, (double)var10).endVertex();
      var12.pos((double)(-var5), (double)var6, 0.0D).tex((double)var8, (double)var9).endVertex();
      var12.pos((double)var5, (double)var6, 0.0D).tex((double)var7, (double)var9).endVertex();
      var12.pos((double)var5, (double)(-var6), 0.0D).tex((double)var7, (double)var10).endVertex();
      GL11.glNormal3f(0.0F, 0.0F, 1.0F);
      var11.draw();
   }
}
