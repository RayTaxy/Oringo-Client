package oringo.module;

import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import map.Class1;
import map.Class163;
import map.Class311;
import map.Class362;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Post;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class ModuleListModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Margin", 5.0D, 0.0D, 10.0D, 1.0D);
   public static final Predicate field1 = ModuleListModule::field2;
   public final BooleanSetting field2 = new BooleanSetting("Blur", true);
   public final BooleanSetting field3 = new BooleanSetting("Lowercase", false);
   public final List field4 = Lists.newArrayList();
   public final BooleanSetting field5 = new BooleanSetting("Line", true);
   public final BooleanSetting field6 = new BooleanSetting("Shadow", true);

   public void method8() {
      ScaledResolution var1 = new ScaledResolution(field58);
      GlStateManager.scale(1.0F / (float)var1.getScaleFactor(), 1.0F / (float)var1.getScaleFactor(), 1.0F);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.enableTexture2D();
      GlStateManager.color(1.0F, 1.0F, 1.0F);
      TicTacToeModule.method5();
      Tessellator var2 = Tessellator.getInstance();
      WorldRenderer var3 = var2.getWorldRenderer();
      var3.begin(7, DefaultVertexFormats.POSITION_TEX);
      Iterator var4 = this.field4.iterator();

      while(var4.hasNext()) {
         float[] var5 = (float[])var4.next();
         ChinaHatModule.method0(var3, var5[0] * (float)var1.getScaleFactor(), var5[1] * (float)var1.getScaleFactor(), var5[2] * (float)var1.getScaleFactor(), var5[3] * (float)var1.getScaleFactor());
      }

      var2.draw();
      GlStateManager.disableBlend();
      GlStateManager.scale((float)var1.getScaleFactor(), (float)var1.getScaleFactor(), 1.0F);
   }

   public float a_(Module var1) {
      float var2 = var1.field60.a_(250L) ? (var1.method44() ? 1.0F : 0.0F) : (float)Math.sin(1.5707963267948966D * (double)var1.field60.method0() / 250.0D + (var1.method44() ? 0.0D : 1.5707963267948966D));
      return Class163.method0(1.0F - var2, 1.0F, 0.0F);
   }

   @SubscribeEvent
   public void method0(Post var1) {
      if (var1.type == ElementType.TEXT) {
         ScaledResolution var2 = new ScaledResolution(field58);
         List var3 = this.method0();
         GL11.glPushMatrix();
         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         float var4 = (float)var2.getScaledWidth() - this.field0.method1();
         float var5 = this.field0.method1();
         float var6 = Class311.field10.method3() + 1.0F;
         if (this.field6.method1()) {
            this.method0(3.0F, 15);
         }

         if (this.field2.method1()) {
            this.method8();
         }

         this.method0(0.0F, 30);
         this.field4.clear();
         float var7 = 2000.0F;
         Class311.field10.method5();

         float var12;
         for(Iterator var8 = var3.iterator(); var8.hasNext(); var5 += var6 * (1.0F - var12)) {
            Module var9 = (Module)var8.next();
            String var10 = var9.method2(this.field3.method1());
            float var11 = Class311.field10.method0(var10) + 5.0F;
            var12 = this.a_(var9);
            float var13 = var4 - var11 + var12 * var11;
            if (var12 != 0.0F) {
               GL11.glEnable(3089);
               Class1.method0((double)var13, (double)var5, (double)(var4 - var13), (double)(var6 * (1.0F - var12)));
            }

            Class311.field10.method3(var10, var13 + 2.5F, var5 + (var6 - Class311.field10.method7()) / 2.0F, Class362.field7.method0(var7 -= 1.0F - var12).getRGB());
            if (var12 != 0.0F) {
               GL11.glDisable(3089);
            }

            float[] var14 = new float[]{var13, var5, Math.min(var13 + var11, var4), var5 + var6 * (1.0F - var12)};
            if (this.field4.isEmpty()) {
               var14[1] -= 2.0F;
            }

            this.field4.add(var14);
         }

         Class311.field10.method6();
         if (this.field5.method1()) {
            this.method0(var3, var4);
         }

         GlStateManager.disableBlend();
         GlStateManager.enableAlpha();
         GL11.glPopMatrix();
      }
   }

   public void method0(float var1, int var2) {
      Tessellator var3 = Tessellator.getInstance();
      WorldRenderer var4 = var3.getWorldRenderer();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.disableTexture2D();
      GlStateManager.disableAlpha();
      GlStateManager.color(0.1F, 0.1F, 0.1F, (float)var2 / 255.0F);

      for(float var5 = 0.0F; var5 <= var1; var5 += 0.5F) {
         var4.begin(7, DefaultVertexFormats.POSITION);
         Iterator var6 = this.field4.iterator();

         while(var6.hasNext()) {
            float[] var7 = (float[])var6.next();
            var4.pos((double)var7[2], (double)(var7[3] + var5), 0.0D).endVertex();
            var4.pos((double)var7[2], (double)(var7[1] + var5), 0.0D).endVertex();
            var4.pos((double)(var7[0] - var5), (double)(var7[1] + var5), 0.0D).endVertex();
            var4.pos((double)(var7[0] - var5), (double)(var7[3] + var5), 0.0D).endVertex();
         }

         var3.draw();
      }

      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public List method0() {
      List var1 = Class362.method0(field1, true);
      var1.sort(Comparator.comparingDouble(this::field0));
      return var1;
   }

   public double field0(Module var1) {
      return (double)(-Class311.field10.method0(var1.method2(this.field3.method1())));
   }

   public void method0(List var1, float var2) {
      Tessellator var3 = Tessellator.getInstance();
      WorldRenderer var4 = var3.getWorldRenderer();
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(770, 771);
      GlStateManager.disableTexture2D();
      GlStateManager.disableAlpha();
      GlStateManager.shadeModel(7425);
      GL11.glLineWidth(2.0F);
      GlStateManager.color(0.1F, 0.1F, 0.1F, 0.07F);
      var4.begin(1, DefaultVertexFormats.POSITION_COLOR);
      float var5 = this.field0.method1();
      float var6 = Class311.field10.method3() + 1.0F;
      float var7 = 2000.0F;

      for(int var8 = var1.size() - 1; var8 >= 0; --var8) {
         float var9 = 1.0F - this.a_((Module)var1.get(var8));
         Color var10 = Class362.field7.method0(var7);
         Color var11 = Class362.field7.method0(var7 -= var9);
         var4.pos((double)var2, (double)(var5 - (float)(var1.size() - 1 == var8 ? 2 : 0)), 0.0D).color((float)var10.getRed() / 255.0F, (float)var10.getGreen() / 255.0F, (float)var10.getBlue() / 255.0F, 1.0F).endVertex();
         var4.pos((double)var2, (double)(var5 + var6 * var9), 0.0D).color((float)var11.getRed() / 255.0F, (float)var11.getGreen() / 255.0F, (float)var11.getBlue() / 255.0F, 1.0F).endVertex();
         var5 += var6 * var9;
      }

      var3.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public ModuleListModule() {
      super("Module List", Category.render, SubCategory.ui, "List of enabled modules");
      this.method0(new Setting[]{this.field6, this.field2, this.field5, this.field3, this.field0});
      this.method1(true);
   }

   public static boolean field2(Module var0) {
      return !var0.field59.method1() && (var0.method44() || var0.field60.method0() <= 250L);
   }
}
