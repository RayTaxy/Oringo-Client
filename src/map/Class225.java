package map;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Class225 {
   public final Object field0;
   public final Object field1;

   public static void method0(Class322.Class0 var0) {
      String var1 = var0.cy_;
      double var2 = var0.j_;
      double var4 = var0.i_;
      double var6 = var0.field4;
      FontRenderer var8 = Class322.field3.fontRendererObj;
      float var9 = 1.6F;
      float var10 = 0.016666668F * var9;
      GlStateManager.translate(var2, var4 + 0.5D, var6);
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-Class322.field3.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(Class322.field3.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(-var10, -var10, var10);
      int var11 = var8.getStringWidth(var1);
      var0.field2 = var11;
      var0.field1 = Class518.method2(2982);
      int var12 = var11 / 2;
      Class322.field0.pos((double)(-var12 - 1), -1.0D, 0.0D).endVertex();
      Class322.field0.pos((double)(-var12 - 1), 8.0D, 0.0D).endVertex();
      Class322.field0.pos((double)(var12 + 1), 8.0D, 0.0D).endVertex();
      Class322.field0.pos((double)(var12 + 1), -1.0D, 0.0D).endVertex();
   }

   public Class225(Object var1, Object var2) {
      this.field1 = var1;
      this.field0 = var2;
   }
}
