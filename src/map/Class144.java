package map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.awt.Color;
import java.util.Iterator;
import java.util.Locale;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import oringo.module.AnimationsModule;
import oringo.module.AntibotModule;
import oringo.module.AutoIceFillModule;
import oringo.module.AutoRequeueModule;
import oringo.module.AutoSoulcryModule;
import oringo.module.AutoWaterModule;
import oringo.module.AutoWeirdosModule;
import oringo.module.DungeonMapModule;
import oringo.module.FDSwapperModule;
import oringo.module.HideSummonsModule;
import oringo.module.HitboxesModule;
import oringo.module.KuudraHelperModule;
import oringo.module.NoFallModule;
import oringo.module.NoRotateModule;
import oringo.module.ReachModule;
import oringo.module.VelocityModule;

public class Class144 extends Class283 {
   public static final ResourceLocation field2 = new ResourceLocation("oringoclient", "chest.png");
   public final DungeonMapModule aO_;
   public static double field4 = 8.0D;
   public static final int field5 = 6;
   public static final int field6 = 22;
   public static final int aP_ = 18;
   public static double aQ_ = 8.0D;

   public void method0() {
      if (field0.theWorld != null) {
         GlStateManager.enableTexture2D();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         int var1 = this.aO_.field15.method3();
         boolean var2 = field0.thePlayer != null && field0.thePlayer.getHeldItem() != null && field0.thePlayer.getHeldItem().getDisplayName().toLowerCase(Locale.ENGLISH).contains("leap");
         Iterator var3;
         double var6;
         if (this.aO_.field17.method1()) {
            var3 = field0.theWorld.playerEntities.iterator();

            while(var3.hasNext()) {
               EntityPlayer var4 = (EntityPlayer)var3.next();
               if (var4 instanceof AbstractClientPlayer && AutoIceFillModule.method0((Entity)var4)) {
                  AbstractClientPlayer var5 = (AbstractClientPlayer)var4;
                  var6 = (var5.posX - -185.0D) / 32.0D * 22.0D + 9.0D;
                  double var8 = (var5.posZ - -185.0D) / 32.0D * 22.0D + 9.0D;
                  GL11.glPushMatrix();
                  GlStateManager.color(1.0F, 1.0F, 1.0F);
                  GL11.glTranslated(var6, var8, 0.0D);
                  GL11.glRotatef(var5.rotationYaw + 180.0F, 0.0F, 0.0F, 1.0F);
                  Class469.method0((float)(-var1 / 2 - 1), (float)(-var1 / 2 - 1), (float)(var1 / 2 + 1), (float)(var1 / 2 + 1), Color.black.getRGB());
                  GlStateManager.color(1.0F, 0.9F, 0.9F);
                  AntibotModule.method0(-var1 / 2, -var1 / 2, 3.0F, 3.0F, 3, 3, var1, var1, 24.0F, 24.5F, var5);
                  if (field0.thePlayer.isWearing(EnumPlayerModelParts.HAT)) {
                     AntibotModule.method0(-var1 / 2, -var1 / 2, 15.0F, 3.0F, 3, 3, var1, var1, 24.0F, 24.5F, var5);
                  }

                  GL11.glPopMatrix();
               }
            }
         }

         var3 = Class119.field5.iterator();

         while(var3.hasNext()) {
            Class229 var11 = (Class229)var3.next();
            double var13 = (double)(var11.k_() / (float)(Class119.field4 + 4) * 22.0F) - 11.0D;
            double var7 = (double)(var11.method2() / (float)(Class119.field4 + 4) * 22.0F) - 11.0D;
            GL11.glPushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            GL11.glTranslated(var13, var7, 0.0D);
            if (var2) {
               Class311.field15.method0(var11.d_(), 0.0F, (float)(-((double)var1 / 2.0D + (double)Class311.field15.method3())), Color.white.getRGB());
            }

            GL11.glRotatef(var11.method5() + 180.0F, 0.0F, 0.0F, 1.0F);
            Class469.method0((float)(-var1 / 2 - 1), (float)(-var1 / 2 - 1), (float)(var1 / 2 + 1), (float)(var1 / 2 + 1), Color.black.getRGB());
            Class47.method0(-var1 / 2, -var1 / 2, 3.0F, 3.0F, 3, 3, var1, var1, 24.0F, 24.5F, var11.method9());
            Class47.method0(-var1 / 2, -var1 / 2, 15.0F, 3.0F, 3, 3, var1, var1, 24.0F, 24.5F, var11.method9());
            GL11.glPopMatrix();
         }

         if (field0.thePlayer != null) {
            EntityPlayerSP var10 = field0.thePlayer;
            double var12 = (var10.posX - -185.0D) / 32.0D * 22.0D + 9.0D;
            var6 = (var10.posZ - -185.0D) / 32.0D * 22.0D + 9.0D;
            GL11.glPushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            GL11.glTranslated(var12, var6, 0.0D);
            GL11.glRotatef(var10.rotationYaw + 180.0F, 0.0F, 0.0F, 1.0F);
            Class469.method0((float)(-var1 / 2 - 1), (float)(-var1 / 2 - 1), (float)(var1 / 2 + 1), (float)(var1 / 2 + 1), Color.black.getRGB());
            AntibotModule.method0(-var1 / 2, -var1 / 2, 3.0F, 3.0F, 3, 3, var1, var1, 24.0F, 24.5F, var10);
            if (field0.thePlayer.isWearing(EnumPlayerModelParts.HAT)) {
               AntibotModule.method0(-var1 / 2, -var1 / 2, 15.0F, 3.0F, 3, 3, var1, var1, 24.0F, 24.5F, var10);
            }

            GL11.glPopMatrix();
         }

         GlStateManager.disableBlend();
      }
   }

   public void method4() {
      GL11.glScalef(this.method10(), this.method10(), this.method10());
   }

   public void method2() {
      Class311.field15.method0(this.aO_.field9.method1());
      if (this.method20()) {
         this.method0((float)t_() / this.method10() + this.field7);
         this.method1((float)method6() / this.method10() + this.field8);
      }

      ScaledResolution var1 = i_();
      this.method1(this.method7(), this.aO_.field8.method1() ? 155.0F : 140.0F);
      GL11.glPushMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F);
      String var2 = this.aO_.field1.method4();
      byte var3 = -1;
      switch(var2.hashCode()) {
      case -1776693134:
         if (var2.equals("Classic")) {
            var3 = 1;
         }
         break;
      case 2433880:
         if (var2.equals("None")) {
            var3 = 2;
         }
         break;
      case 65193513:
         if (var2.equals("Clean")) {
            var3 = 0;
         }
      }

      switch(var3) {
      case 0:
         this.method4();
         if (this.aO_.field7.method1()) {
            for(float var4 = 0.0F; var4 < 3.0F; var4 += 0.5F) {
               float var5 = (double)(this.k_() + this.method7() / 2.0F) > (double)i_().getScaledWidth() / 2.0D ? var4 : -var4;
               HitboxesModule.method0((double)(this.k_() - var5), (double)(this.method15() + var4), (double)this.method7(), (double)this.method13(), 10.0D, (new Color(20, 20, 20, 20)).getRGB());
            }
         }

         AutoSoulcryModule.method3();
         FDSwapperModule.method5();
         ReachModule.method0((double)this.k_(), (double)this.method15(), (double)(this.k_() + this.method7()), (double)(this.method15() + this.method13()), 10.0D, Color.black.getRGB());
         KuudraHelperModule.method0(1);
         this.method19();
         if (this.aO_.field7.method1()) {
            AutoWaterModule.method8();
         }

         this.method4();
         GL11.glTranslated((double)this.k_(), (double)this.method15(), 0.0D);
         if (this.j_() && field0.currentScreen instanceof GuiChat) {
            AutoRequeueModule.method0(0.0F, 0.0F, this.method7(), this.method13(), 10.0F, 5.0F, (new Color(50, 50, 50, 30)).getRGB(), Color.white.getRGB());
         } else {
            ReachModule.method0(0.0D, 0.0D, (double)this.method7(), (double)this.method13(), 10.0D, (new Color(50, 50, 50, 30)).getRGB());
         }
         break;
      case 1:
         if (this.aO_.field7.method1()) {
            AutoWeirdosModule.method0((float)((int)(this.k_() * this.method10())), (float)((int)(this.method15() * this.method10())), (float)((int)(this.method7() * this.method10())), (float)((int)(this.method13() * this.method10())));
         }

         this.method4();
         Class208.method0((float)((int)this.k_()), (float)((int)this.method15()), (float)((int)(this.k_() + this.method7())), (float)((int)(this.method15() + this.method13())), 1.5F, (new Color(30, 30, 30, 150)).getRGB(), this.j_() && field0.currentScreen instanceof GuiChat ? Color.white.getRGB() : (this.aO_.field16.method0(0) ? Class362.field7.method17().getRGB() : (this.aO_.field16.method0(1) ? Color.HSBtoRGB((float)((double)System.currentTimeMillis() % 2500.0D / 2500.0D), 0.65F, 1.0F) : this.aO_.field5.method8())));
         GL11.glTranslated((double)this.k_(), (double)this.method15(), 0.0D);
         break;
      case 2:
         if (this.aO_.field7.method1()) {
            AutoWeirdosModule.method0((float)((int)(this.k_() * this.method10())), (float)((int)(this.method15() * this.method10())), (float)((int)(this.method7() * this.method10())), (float)((int)(this.method13() * this.method10())));
         }

         this.method4();
         GL11.glTranslated((double)this.k_(), (double)this.method15(), 0.0D);
      }

      this.method17();
      GL11.glPushMatrix();
      GL11.glTranslated(field4, aQ_, 0.0D);
      GL11.glEnable(3089);
      Class1.method0((double)this.k_() * this.aO_.field11.method0(), ((double)this.method15() + aQ_) * this.aO_.field11.method0(), (double)this.method7() * this.aO_.field11.method0(), this.aO_.field11.method0() * ((double)this.method7() - aQ_ * 2.0D));
      if (field0.thePlayer != null && this.aO_.field0.method1()) {
         EntityPlayerSP var7 = field0.thePlayer;
         double var8 = (var7.posX - -185.0D) / 32.0D * 22.0D + 9.0D;
         double var9 = (var7.posZ - -185.0D) / 32.0D * 22.0D + 9.0D;
         GL11.glTranslated((double)this.method7() / 2.0D - var8 - field4, (double)this.method7() / this.aO_.field2.method0() - var9 - aQ_, 0.0D);
         if (this.aO_.field3.method1()) {
            GL11.glTranslated(var8, var9, 0.0D);
            GL11.glRotatef(-field0.thePlayer.rotationYaw - 180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslated(-var8, -var9, 0.0D);
         }
      }

      VelocityModule.method5();
      this.method18();
      this.method0();
      GL11.glDisable(3089);
      GL11.glPopMatrix();
      HideSummonsModule.method3();
      if (this.aO_.field8.method1()) {
         Class311.field2.method0(String.format("Score: %s Crypts: %s%s", NoFallModule.method0(Class198.field0.method3(), 300, 270), NoFallModule.method0(Class198.field0.method10(), 5, 3), Class198.field0.method5() < 6 ? "" : " Mimic: " + (Class198.field0.method13() ? "§aDead" : "§cAlive") + "§r"), (float)((double)this.method7() / 2.0D), (float)(140.0D - aQ_ / 2.0D), Color.white.getRGB());
         Class311.field2.method0(String.format("Deaths: %s Secrets: %s", NoRotateModule.method0(Class198.field0.method7(), 2, 0), String.format("§%s%s/%s§r", Class198.field0.method11() < Class198.field0.method4() ? "c" : "a", Math.round((double)Class198.field0.method12() * Class198.field0.method11()), Class198.field0.method12())), (float)((double)this.method7() / 2.0D), (float)(140.0D - aQ_ / 2.0D + (double)Class311.field2.method3()), Color.white.getRGB());
      }

      GL11.glPopMatrix();
   }

   public boolean method0(int var1, int var2) {
      return (float)var1 > this.k_() * this.method10() && (float)var1 < (this.k_() + this.method7()) * this.method10() && (float)var2 > this.method15() * this.method10() && (float)var2 < (this.method15() + this.method13()) * this.method10();
   }

   public void method16() {
      this.method0(true);
      this.field8 = this.k_() - (float)method6() / this.method10();
      this.field7 = this.method15() - (float)t_() / this.method10();
   }

   public float method10() {
      return this.aO_.field11.method1();
   }

   public Class72 method1(float var1) {
      this.aO_.field6.method4(Class207.method0((double)(var1 / (float)i_().getScaledWidth()), 1.0D / (double)this.method10(), 0.0D));
      return this;
   }

   public Class72 method0(float var1) {
      this.aO_.field12.method4(Class207.method0((double)(var1 / (float)i_().getScaledHeight()), 1.0D / (double)this.method10(), 0.0D));
      return this;
   }

   public void method17() {
      int var1 = 0;
      int var2 = 1000;
      Class208[][] var3 = Class198.field1;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Class208[] var6 = var3[var5];
         int var7 = 0;
         Class208[] var8 = var6;
         int var9 = var6.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            Class208 var11 = var8[var10];
            if (var11 == null) {
               ++var7;
            } else {
               var7 = 0;
            }
         }

         if (var2 > var7) {
            var2 = var7;
         }

         if (var7 == var6.length) {
            ++var1;
         } else {
            var1 = 0;
         }
      }

      int var12 = Class198.field1.length - var2;
      field4 = (double)(140 - (var12 * 18 + (var12 - 1) * 4)) / 2.0D;
      var12 = Class198.field1.length - var1;
      aQ_ = (double)(140 - (var12 * 18 + (var12 - 1) * 4)) / 2.0D;
   }

   public void method18() {
      for(int var1 = 0; var1 < Class198.field1.length; ++var1) {
         Class208[] var2 = Class198.field1[var1];

         for(int var3 = 0; var3 < var2.length; ++var3) {
            Class208 var4 = var2[var3];
            if (var4 != null && var4.method5() != Class46.field4 && var4.method5() != Class46.field6 && var4.method5() != Class46.field8 && var4.bA_ && (var4.field6 != Class432.field0 || !this.aO_.field13.method1())) {
               float var5 = 0.0F;
               float var6 = 0.0F;
               Class208 var7 = var4;

               for(int var8 = 1; AnimationsModule.method0(var7, var3 + var8, var1); ++var8) {
                  var5 += 11.0F;
                  var7 = Class25.method0(var3 + var8, var1);
                  if (var7 == null) {
                     break;
                  }
               }

               if (AnimationsModule.method0(var4, var3 + 1, var1) && AnimationsModule.method0(var4, var3 + 1, var1 + 1) && AnimationsModule.method0(var4, var3, var1 + 1)) {
                  var6 += 11.0F;
               }

               StringBuilder var9 = new StringBuilder();
               if (this.aO_.field4.method1() || var4.method5() == Class46.field3 || var4.method5() == Class46.field1 || var4.method5() == Class46.field5) {
                  var9.append(var4.method45());
               }

               if (this.aO_.field14.method1() && var4.method0() != 0) {
                  if (var9.length() != 0) {
                     var9.append(" ");
                  }

                  var9.append(var4.method0());
               }

               if (var9.length() != 0) {
                  String[] var10 = var9.toString().split(" ");
                  float var11 = (float)(var3 * 22) + 9.0F;
                  float var12 = (float)(var1 * 22) + (18.0F - Class311.field15.method3() * (float)var10.length) / 2.0F;
                  GL11.glTranslatef(var11, var12, 0.0F);
                  int var13 = 0;
                  String[] var14 = var10;
                  int var15 = var10.length;

                  for(int var16 = 0; var16 < var15; ++var16) {
                     String var17 = var14[var16];
                     Class311.field15.method0(var17, var5, (float)(var13++) * Class311.field15.method3() + var6, var4.field6 == Class432.field0 ? (new Color(75, 245, 66)).getRGB() : Color.white.getRGB());
                  }

                  GL11.glTranslatef(-var11, -var12, 0.0F);
               }
            }
         }
      }

   }

   public static JsonElement method0(JsonObject var0, String var1) {
      return var0.get(var1);
   }

   public void method19() {
      GL11.glScalef(1.0F / this.method10(), 1.0F / this.method10(), 1.0F / this.method10());
   }

   public boolean method1() {
      return this.aO_.method44() && Class496.field5 && (!Class496.field13 || !this.aO_.field10.method1());
   }

   public Class144(DungeonMapModule var1) {
      super(var1.field6, var1.field12);
      this.method1(140.0F, 140.0F);
      this.aO_ = var1;
   }
}
