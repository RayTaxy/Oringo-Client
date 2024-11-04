package map;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.lwjgl.opengl.GL11;
import oringo.command.BlockCommand;

public class Class407 implements Class317 {
   public final int field0;
   public final float field1;
   public Font field2;
   public FontMetrics field3;
   public Graphics2D field4;
   public final boolean field5;
   public final Class407.Class0[] field6 = new Class407.Class0['\ufffe'];
   public int field7;
   public static final Minecraft field8 = Minecraft.getMinecraft();
   public final boolean field9;

   public void method0(Font var1, Graphics2D var2, int var3, int var4) {
      BlockCommand.method0(var1, var2, var3, var4, this.field5, this.field9);
   }

   public float method0(int var1, float var2, float var3) {
      var3 -= (float)this.field3.getDescent() * 1.55F;
      Class407.Class0 var4 = this.method3(var1);
      if (!var4.field0) {
         return 0.0F;
      } else {
         GlStateManager.bindTexture(var4.field1.getGlTextureId());
         GL11.glBegin(4);
         GL11.glTexCoord2f(1.0F, 0.0F);
         GL11.glVertex2f(var2 + (float)var4.field2, var3);
         GL11.glTexCoord2f(0.0F, 0.0F);
         GL11.glVertex2f(var2, var3);
         GL11.glTexCoord2f(0.0F, 1.0F);
         GL11.glVertex2f(var2, var3 + (float)var4.cX_);
         GL11.glTexCoord2f(0.0F, 1.0F);
         GL11.glVertex2f(var2, var3 + (float)var4.cX_);
         GL11.glTexCoord2f(1.0F, 1.0F);
         GL11.glVertex2f(var2 + (float)var4.field2, var3 + (float)var4.cX_);
         GL11.glTexCoord2f(1.0F, 0.0F);
         GL11.glVertex2f(var2 + (float)var4.field2, var3);
         GL11.glEnd();
         return (float)var4.field2 - this.field1;
      }
   }

   public Rectangle method2(int var1) {
      return this.field3.getStringBounds(String.valueOf((char)var1), this.field4).getBounds();
   }

   public void p_() {
      for(int var1 = 0; var1 < this.field6.length; ++var1) {
         Class407.Class0 var2 = this.field6[var1];
         if (var2 != null && var2.field0) {
            var2.field1.deleteGlTexture();
            this.field6[var1] = null;
         }
      }

   }

   public void a_(int var1) {
      if (this.field7 != var1) {
         this.p_();
         this.field7 = var1;
         this.field2 = this.field2.deriveFont(0, (float)(this.field0 * var1));
         BufferedImage var2 = new BufferedImage(50, 50, 2);
         this.field4 = var2.createGraphics();
         this.method0(this.field2, this.field4, 50, 50);
         this.field3 = this.field4.getFontMetrics();
      }
   }

   public float method0(int var1) {
      Class407.Class0 var2 = this.method3(var1);
      return !var2.field0 ? 0.0F : (float)var2.field2 - this.field1;
   }

   public static void method5() {
      Iterator var0 = (new ArrayList(Class170.field0)).iterator();

      while(var0.hasNext()) {
         AtomicInteger var1 = (AtomicInteger)var0.next();
         Class176.method0(var1);
      }

   }

   public Class407(Font var1, boolean var2, boolean var3, float var4) {
      this.field2 = var1;
      this.field0 = var1.getSize();
      this.field5 = var2;
      this.field9 = var3;
      this.field1 = var4;
      this.a_((new ScaledResolution(field8)).getScaleFactor());
   }

   public Class407.Class0 method3(int var1) {
      Class407.Class0 var2;
      if (var1 >= this.field6.length) {
         var2 = new Class407.Class0((Class407$1)null);
         var2.field0 = false;
         return var2;
      } else {
         var2 = this.field6[var1];
         if (var2 == null) {
            if (!this.field2.canDisplay(var1)) {
               var2 = new Class407.Class0((Class407$1)null);
               var2.field0 = false;
               this.field6[var1] = var2;
               return var2;
            }

            Rectangle var3 = this.method2(var1);
            BufferedImage var4 = new BufferedImage(var3.width, var3.height, 2);
            Graphics2D var5 = (Graphics2D)var4.getGraphics();
            this.method0(this.field2, var5, var3.width, var3.height);
            var5.drawString(String.valueOf((char)var1), 0, this.field3.getAscent());
            var2 = new Class407.Class0((Class407$1)null);
            var2.field2 = var4.getWidth();
            var2.cX_ = var4.getHeight();
            var2.field1 = new DynamicTexture(var4);
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(var2.field1.getGlTextureId());
            GL11.glTexParameteri(3553, 10241, 9987);
            GlStateManager.bindTexture(0);
            this.field6[var1] = var2;
         }

         return var2;
      }
   }

   public static class Class0 {
      public boolean field0;
      DynamicTexture field1;
      public int field2;
      public int cX_;

      private Class0() {
         this.field0 = true;
      }

      Class0(Class407$1 var1) {
         this();
      }
   }
}
