package oringo.module;

import java.awt.Color;
import map.Class362;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class ChinaHatModule extends Module {
   public final DoubleSetting field0 = new DoubleSetting("Green", 80.0D, 0.0D, 255.0D, 1.0D, this::lambda$new$1);
   public final DoubleSetting bk_ = new DoubleSetting("Rotation", 5.0D, 0.0D, 5.0D, 0.1D);
   public final DoubleSetting field2 = new DoubleSetting("Red 2", 90.0D, 0.0D, 255.0D, 1.0D, this::lambda$new$6);
   public final DoubleSetting field3 = new DoubleSetting("Blue 1", 255.0D, 0.0D, 255.0D, 1.0D, this::lambda$new$5);
   public final DoubleSetting field4 = new DoubleSetting("Green 1", 0.0D, 0.0D, 255.0D, 1.0D, this::lambda$new$4);
   public final DoubleSetting field5 = new DoubleSetting("Blue", 255.0D, 0.0D, 255.0D, 1.0D, this::lambda$new$2);
   public final DoubleSetting field6 = new DoubleSetting("Angles", 8.0D, 4.0D, 90.0D, 1.0D);
   public final DoubleSetting field7 = new DoubleSetting("Red 1", 255.0D, 0.0D, 255.0D, 1.0D, this::lambda$new$3);
   public final DoubleSetting field8 = new DoubleSetting("Height", 0.3D, 0.10000000149011612D, 1.0D, 0.01D);
   public final EnumSetting field9 = new EnumSetting("Color mode", "Rainbow", new String[]{"Rainbow", "Gradient", "Single", "Theme"});
   public final DoubleSetting field10 = new DoubleSetting("Radius", 0.7D, 0.1D, 1.0D, 0.01D);
   public final DoubleSetting field11 = new DoubleSetting("Green 2", 10.0D, 0.0D, 255.0D, 1.0D, this::lambda$new$7);
   public final BooleanSetting field12 = new BooleanSetting("Show in first person", false);
   public final EnumSetting field13 = new EnumSetting("Mode", "Crystal", new String[]{"Crystal", "Hat"});
   public final BooleanSetting field14 = new BooleanSetting("Shade", true);
   public final DoubleSetting field15 = new DoubleSetting("Blue 2", 255.0D, 0.0D, 255.0D, 1.0D, this::lambda$new$8);
   public final DoubleSetting field16 = new DoubleSetting("Position", 0.1D, -0.5D, 1.0D, 0.01D);
   public final DoubleSetting field17 = new DoubleSetting("Red", 0.0D, 0.0D, 255.0D, 1.0D, this::lambda$new$0);

   public Boolean lambda$new$2() {
      return !this.field9.method0(2);
   }

   public void method0(EntityLivingBase var1, float var2) {
      GL11.glPushMatrix();
      GlStateManager.enableBlend();
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      if (this.field14.method1()) {
         GL11.glShadeModel(7425);
      }

      GlStateManager.disableTexture2D();
      GlStateManager.disableCull();
      GlStateManager.disableLighting();
      GL11.glTranslated(var1.lastTickPosX + (var1.posX - var1.lastTickPosX) * (double)var2 - field58.getRenderManager().viewerPosX, var1.lastTickPosY + (var1.posY - var1.lastTickPosY) * (double)var2 - field58.getRenderManager().viewerPosY + (double)var1.height + (var1.isSneaking() ? this.field16.method0() - 0.23000000417232513D : this.field16.method0()), var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) * (double)var2 - field58.getRenderManager().viewerPosZ);
      GL11.glRotatef((float)((double)((float)var1.ticksExisted + var2) * this.bk_.method0()) - 90.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-(field58.thePlayer.prevRotationYawHead + (field58.thePlayer.rotationYawHead - field58.thePlayer.prevRotationYawHead) * var2), 0.0F, 1.0F, 0.0F);
      double var3 = this.field10.method0();
      if (this.field13.method0(0)) {
         this.method1(var3);
      } else {
         this.method0(var3);
      }

      GL11.glShadeModel(7424);
      GlStateManager.enableCull();
      GlStateManager.resetColor();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
      GL11.glDisable(2848);
      GL11.glPopMatrix();
   }

   public Boolean lambda$new$4() {
      return !this.field9.method0(1);
   }

   public Boolean lambda$new$1() {
      return !this.field9.method0(2);
   }

   public Boolean lambda$new$0() {
      return !this.field9.method0(2);
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (this.method44() && (field58.gameSettings.thirdPersonView != 0 || this.field12.method1())) {
         this.method0(field58.thePlayer, var1.partialTicks);
      }

   }

   public ChinaHatModule() {
      super("China Hat", 0, Category.render, SubCategory.ui);
      this.method0(new Setting[]{this.field13, this.field10, this.field8, this.field12, this.bk_, this.field16, this.field6, this.field14, this.field9, this.field17, this.field0, this.field5, this.field7, this.field4, this.field3, this.field2, this.field11, this.field15});
   }

   public Boolean lambda$new$8() {
      return !this.field9.method0(1);
   }

   public void method0(double var1) {
      GL11.glLineWidth(2.0F);
      GL11.glBegin(2);

      for(int var3 = 0; (double)var3 <= this.field6.method0(); ++var3) {
         Color var4 = this.method0((double)var3, (double)((int)this.field6.method0()), false);
         GlStateManager.color((float)var4.getRed() / 255.0F, (float)var4.getGreen() / 255.0F, (float)var4.getBlue() / 255.0F, 0.5F);
         GL11.glVertex3d(Math.cos((double)var3 * 3.141592653589793D / (this.field6.method0() / 2.0D)) * var1, 0.0D, Math.sin((double)var3 * 3.141592653589793D / (this.field6.method0() / 2.0D)) * var1);
      }

      GL11.glEnd();
      GL11.glBegin(6);
      Color var6 = this.method0(0.0D, this.field6.method0(), true);
      GlStateManager.color((float)var6.getRed() / 255.0F, (float)var6.getGreen() / 255.0F, (float)var6.getBlue() / 255.0F, 0.8F);
      GL11.glVertex3d(0.0D, this.field8.method0(), 0.0D);

      for(int var7 = 0; (double)var7 <= this.field6.method0(); ++var7) {
         Color var5 = this.method0((double)var7, (double)((int)this.field6.method0()), false);
         GlStateManager.color((float)var5.getRed() / 255.0F, (float)var5.getGreen() / 255.0F, (float)var5.getBlue() / 255.0F, 0.3F);
         GL11.glVertex3d(Math.cos((double)var7 * 3.141592653589793D / (this.field6.method0() / 2.0D)) * var1, 0.0D, Math.sin((double)var7 * 3.141592653589793D / (this.field6.method0() / 2.0D)) * var1);
      }

      GL11.glVertex3d(0.0D, this.field8.method0(), 0.0D);
      GL11.glEnd();
   }

   public Color method0(double var1, double var3, boolean var5) {
      String var6 = this.field9.method4();
      byte var7 = -1;
      switch(var6.hashCode()) {
      case -1656737386:
         if (var6.equals("Rainbow")) {
            var7 = 0;
         }
         break;
      case 80774569:
         if (var6.equals("Theme")) {
            var7 = 2;
         }
         break;
      case 154295120:
         if (var6.equals("Gradient")) {
            var7 = 1;
         }
      }

      switch(var7) {
      case 0:
         return Color.getHSBColor((float)(var1 / var3), 0.65F, 1.0F);
      case 1:
         if (var5) {
            return new Color((int)this.field7.method0(), (int)this.field4.method0(), (int)this.field3.method0());
         }

         return new Color((int)this.field2.method0(), (int)this.field11.method0(), (int)this.field15.method0());
      case 2:
         double var8 = var1 / var3 * 10.0D;
         if (var1 > var3 / 2.0D) {
            var8 = 10.0D - var8;
         }

         return Class362.field7.method0((float)((int)var8));
      default:
         return var5 ? (new Color((int)this.field17.method0(), (int)this.field0.method0(), (int)this.field5.method0())).darker().darker() : new Color((int)this.field17.method0(), (int)this.field0.method0(), (int)this.field5.method0());
      }
   }

   public void method1(double var1) {
      GL11.glBegin(6);
      Color var3 = this.method0(0.0D, (double)((int)this.field6.method0()), false);
      GlStateManager.color((float)var3.getRed() / 255.0F, (float)var3.getGreen() / 255.0F, (float)var3.getBlue() / 255.0F, 1.0F);
      GL11.glVertex3d(0.0D, this.field8.method0() / 2.0D, 0.0D);

      int var4;
      for(var4 = 0; (double)var4 <= this.field6.method0(); ++var4) {
         var3 = this.method0((double)var4, (double)((int)this.field6.method0()), false);
         GlStateManager.color((float)var3.getRed() / 255.0F, (float)var3.getGreen() / 255.0F, (float)var3.getBlue() / 255.0F, 1.0F);
         GL11.glVertex3d(Math.cos((double)var4 * 3.141592653589793D / (this.field6.method0() / 2.0D)) * var1, 0.0D, Math.sin((double)var4 * 3.141592653589793D / (this.field6.method0() / 2.0D)) * var1);
      }

      GL11.glVertex3d(0.0D, this.field8.method0() / 2.0D, 0.0D);
      GL11.glEnd();
      GL11.glBegin(6);
      var3 = this.method0(0.0D, (double)((int)this.field6.method0()), true);
      GlStateManager.color((float)var3.getRed() / 255.0F, (float)var3.getGreen() / 255.0F, (float)var3.getBlue() / 255.0F, 1.0F);
      GL11.glVertex3d(0.0D, -this.field8.method0() / 2.0D, 0.0D);

      for(var4 = 0; (double)var4 <= this.field6.method0(); ++var4) {
         var3 = this.method0((double)var4, (double)((int)this.field6.method0()), false);
         GlStateManager.color((float)var3.getRed() / 255.0F, (float)var3.getGreen() / 255.0F, (float)var3.getBlue() / 255.0F, 1.0F);
         GL11.glVertex3d(Math.cos((double)var4 * 3.141592653589793D / (this.field6.method0() / 2.0D)) * var1, 0.0D, Math.sin((double)var4 * 3.141592653589793D / (this.field6.method0() / 2.0D)) * var1);
      }

      GL11.glVertex3d(0.0D, -this.field8.method0() / 2.0D, 0.0D);
      GL11.glEnd();
   }

   public Boolean lambda$new$7() {
      return !this.field9.method0(1);
   }

   public Boolean lambda$new$5() {
      return !this.field9.method0(1);
   }

   public Boolean lambda$new$6() {
      return !this.field9.method0(1);
   }

   public static void method0(WorldRenderer var0, float var1, float var2, float var3, float var4) {
      float var5 = var1 / (float)Oringo.field9.displayWidth;
      float var6 = ((float)Oringo.field9.displayHeight - var2) / (float)Oringo.field9.displayHeight;
      float var7 = var3 / (float)Oringo.field9.displayWidth;
      float var8 = ((float)Oringo.field9.displayHeight - var4) / (float)Oringo.field9.displayHeight;
      var0.pos((double)var1, (double)var2, 0.0D).tex((double)var5, (double)var6).endVertex();
      var0.pos((double)var1, (double)var4, 0.0D).tex((double)var5, (double)var8).endVertex();
      var0.pos((double)var3, (double)var4, 0.0D).tex((double)var7, (double)var8).endVertex();
      var0.pos((double)var3, (double)var2, 0.0D).tex((double)var7, (double)var6).endVertex();
   }

   public Boolean lambda$new$3() {
      return !this.field9.method0(1);
   }
}
