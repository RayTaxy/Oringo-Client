package map;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import oringo.event.Class274;
import oringo.event.Class307;
import oringo.event.Class533;
import oringo.module.AutoBlazeModule;
import oringo.module.AutoSoulcryModule;
import oringo.module.ClickGuiModule;
import oringo.module.DisablerModule;
import oringo.module.EvidenceForgerModule;
import oringo.module.FDSwapperModule;
import oringo.module.FlightModule;
import oringo.module.GiftESPModule;
import oringo.module.HClipModule;
import oringo.module.HideSummonsModule;
import oringo.module.HitboxesModule;
import oringo.module.KuudraHelperModule;
import oringo.module.NBTCopyModule;
import oringo.module.SubCategory;

public class Class7 extends GuiScreen {
   public static final Color h_ = new Color(31, 31, 35, 3);
   public static final Minecraft field1;
   public static ScaledResolution field2;
   public static double i_;
   public static double j_;
   public static final List field5;
   public static final Color field6 = (new Color(37, 37, 37)).darker();
   public static double field7;
   public static final double k_ = 470.0D;
   public static Class348 field9;
   public static final Color field10 = (new Color(30, 31, 35)).darker();
   public static Class348 field11;
   public static double field12;
   public static final double bV_ = 313.3333333333333D;
   public boolean field3;
   public static final Color field15 = new Color(220, 220, 220);

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void mouseReleased(int var1, int var2, int var3) {
      this.field3 = false;
      field11.method0(var3);
   }

   public void mouseClicked(int var1, int var2, int var3) {
      if (var3 == 0 && GiftESPModule.method0(j_, i_, 470.0D, 37.599999999999994D) && !GiftESPModule.method0(j_ + 94.0D + 10.575000000000001D, i_ + 9.399999999999999D, 190.35D, 18.799999999999997D)) {
         this.startDragging();
      }

      double var4 = i_ + 37.599999999999994D + 4.0D;
      double var6 = 5.013333333333334D;
      Class442 var8 = null;

      for(Iterator var9 = field5.iterator(); var9.hasNext(); var4 += (double)Class311.field16.method3()) {
         Class348 var10 = (Class348)var9.next();
         if (var10.method5() != var8) {
            if (var8 != null) {
               var4 += var6 * 1.5D;
            }

            var4 += (double)Class311.field7.method3();
            var8 = var10.method5();
         }

         if (GiftESPModule.method0(j_, var4, 94.0D, (double)Class311.field16.method3())) {
            HClipModule.method0(var10);
            break;
         }
      }

      field11.method1(var3);
   }

   public void onGuiClosed() {
      Class348 var2;
      for(Iterator var1 = field5.iterator(); var1.hasNext(); var2.field12 = 0.0F) {
         var2 = (Class348)var1.next();
         var2.field10 = var2.field11 = field11 == var2 ? 1.0F : 0.0F;
         var2.field14 = 0.0F;
      }

      Keyboard.enableRepeatEvents(false);
   }

   public void drawScreen(int var1, int var2, float var3) {
      GL11.glPushMatrix();
      field2 = new ScaledResolution(field1);
      this.drawGradientRect(0, 0, field2.getScaledWidth(), field2.getScaledHeight(), 1377968674, 1646404130);
      if (Class274.method0(this)) {
         Class533.method0();
      }

      for(int var4 = 1; var4 <= 14; ++var4) {
         GlStateManager.disableAlpha();
         double var5 = (double)var4 * 1.5D;
         HitboxesModule.method0(j_ - var5, i_ - var5, 470.0D + var5 * 2.0D, 313.3333333333333D + var5 * 2.0D, 12.0D + var5 * 2.0D, h_.getRGB());
         GlStateManager.enableAlpha();
      }

      if (this.field3) {
         j_ = (double)DisablerModule.method5() + field7;
         i_ = (double)EvidenceForgerModule.method5() + field12;
         ClickGuiModule.field22.method4((j_ + 235.0D) / (double)field2.getScaledWidth());
         ClickGuiModule.field16.method4((i_ + 156.66666666666666D) / (double)field2.getScaledHeight());
      }

      AutoSoulcryModule.method3();
      FDSwapperModule.method5();
      HitboxesModule.method0(j_, i_, 470.0D, 313.3333333333333D, 12.0D, field10.getRGB());
      KuudraHelperModule.method0(1);
      HitboxesModule.method0(j_, i_, 470.0D, 313.3333333333333D, 12.0D, field10.getRGB());
      Class178.method0(j_, i_ + 37.599999999999994D, j_ + 94.0D, i_ + 313.3333333333333D, field6.getRGB());
      double var18 = 6.580000000000001D;
      double var6 = 5.013333333333334D;
      double var8 = i_ + 37.599999999999994D + var6;
      Class442 var10 = null;
      field9 = null;

      for(Iterator var11 = field5.iterator(); var11.hasNext(); var8 += (double)Class311.field16.method3()) {
         Class348 var12 = (Class348)var11.next();
         if (var12.method5() != var10) {
            if (var10 != null) {
               this.drawHorizontalLine((int)(j_ + var18), (int)(j_ + 94.0D - var18), (int)(var8 + var6 * 0.75D - (double)Class311.field16.method3() + (double)Class311.field16.method7()), (new Color(60, 60, 60)).getRGB());
               var8 += var6 * 1.5D;
            }

            Class311.field7.method1(var12.method5().method0(), (float)(j_ + var18 - 2.0D), (float)var8 - 1.0F, (new Color(180, 180, 180)).getRGB());
            var8 += (double)Class311.field7.method3();
            var10 = var12.method5();
         }

         double var13 = (double)(Class311.field16.method0() / 2.0F);
         float var15 = Class311.field16.method0(var12.method45());
         float var16 = 3.0F;
         if (GiftESPModule.method0(j_ + var18 + (double)var16 * 1.3D, var8, (double)var15, (double)Class311.field16.method3())) {
            field9 = var12;
         }

         Color var17 = Class307.method0(var12.method2(), FlightModule.method7(), var12.field11);
         NBTCopyModule.method0((float)(j_ + var18), (float)(var8 + var13 - (double)(var16 / 2.0F)), var16, var16, var16 - 0.75F, var17);
         AutoBlazeModule.method0(var17);
         Class311.field16.method1(var12.method45(), (float)(j_ + var18 + (double)var16 * 1.3D + 1.0D), (float)var8, var17.getRGB());
      }

      field11.method0(var3);
      HideSummonsModule.method3();
      GL11.glPopMatrix();
   }

   public void startDragging() {
      this.field3 = true;
      field7 = j_ - (double)DisablerModule.method5();
      field12 = i_ - (double)EvidenceForgerModule.method5();
   }

   public void updateScreen() {
      field11.method3();

      Class348 var2;
      for(Iterator var1 = field5.iterator(); var1.hasNext(); var2.field12 += ((float)(field9 == var2 ? 1 : 0) - var2.field12) / 3.5F) {
         var2 = (Class348)var1.next();
         var2.field10 = var2.field11;
         var2.field11 += ((float)(field11 == var2 ? 1 : 0) - var2.field11) / 3.5F;
         var2.field14 = var2.field12;
      }

   }

   public static int lambda$static$0(Class348 var0) {
      return var0.method5().ordinal();
   }

   static {
      ArrayList var0 = new ArrayList();
      SubCategory[] var1 = SubCategory.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SubCategory var4 = var1[var3];
         var0.add(new Class453(var4));
      }

      var0.add(new Class185());
      var0.sort(Comparator.comparingInt(Class7::lambda$static$0));
      field5 = var0;
      field11 = (Class348)field5.get(0);
      field1 = Minecraft.getMinecraft();
      field2 = new ScaledResolution(field1);
   }

   public void keyTyped(char var1, int var2) {
      if (!field11.method0(var1, var2)) {
         if (var2 == 1 || var2 == Class362.field7.method46()) {
            field1.displayGuiScreen((GuiScreen)null);
            if (field1.currentScreen == null) {
               field1.setIngameFocus();
            }
         }

      }
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      field11.q_();
      this.field3 = false;
      field2 = new ScaledResolution(field1);
      j_ = ClickGuiModule.field22.method0() * (double)field2.getScaledWidth() - 235.0D;
      i_ = ClickGuiModule.field16.method0() * (double)field2.getScaledHeight() - 156.66666666666666D;
   }

   public static StringBuilder method0(StringBuilder var0, String var1) {
      return var0.append(var1);
   }
}
