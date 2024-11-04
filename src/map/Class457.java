package map;

import java.awt.Color;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import oringo.event.Class307;
import oringo.module.AutoBlazeModule;
import oringo.module.FlightModule;
import oringo.module.ModHiderModule;
import oringo.module.Module;
import oringo.module.NBTCopyModule;
import oringo.module.NamesOnlyModule;
import oringo.module.SumoFencesModule;

public abstract class Class457 extends Class348 {
   public static final ResourceLocation field1 = new ResourceLocation("oringoclient", "icons/toggle_circle.png");
   public Module field2;
   public final GuiTextField field3 = new GuiTextField(0, (FontRenderer)null, 0, 0, 190, 18);
   public boolean field4;
   public static final Color field5 = new Color(40, 40, 40);
   public static final Color field6 = new Color(40, 40, 40);
   public double field7;
   public static final ResourceLocation field8 = new ResourceLocation("oringoclient", "icons/settings2.png");
   public static final double field9 = 8.271999999999998D;
   public static final ResourceLocation field15 = new ResourceLocation("oringoclient", "icons/toggle.png");
   public static final double field16 = 29.9367619047619D;
   public double field17;
   public static final double field18 = 6.345D;
   public static final ResourceLocation field19 = new ResourceLocation("oringoclient", "icons/search.png");
   public double field20;

   public static void method0(HttpURLConnection var0, String var1, String var2) {
      var0.setRequestProperty(var1, var2);
   }

   public boolean method0(char var1, int var2) {
      if (this.field3.isFocused()) {
         if (var2 != 1 && var2 != 28) {
            this.field3.textboxKeyTyped(var1, var2);
            return true;
         } else {
            this.field3.setFocused(false);
            return false;
         }
      } else {
         if (this.field2 != null) {
            int var3 = t_();
            if ((double)var3 > Class7.i_ + 313.3333333333333D || (double)var3 < Class7.i_) {
               return false;
            }

            Iterator var4 = this.field2.method41().iterator();

            while(var4.hasNext()) {
               Class422 var5 = (Class422)var4.next();
               if (var5.method0(var1, var2)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public void method1(int var1) {
      this.field4 = false;
      float var2 = Class311.field8.method0() * 2.0F;
      if (method0(Class7.j_ + 305.5D - 5.0D, Class7.i_ + 37.599999999999994D, 6.0D, 275.7333333333333D)) {
         this.field4 = true;
      }

      int var3;
      if (method0((double)this.field3.xPosition, (double)this.field3.yPosition, (double)this.field3.width, (double)this.field3.height)) {
         var3 = this.field3.xPosition + 7;
         this.field3.setCursorPosition(Class311.field11.method2(this.field3.getText(), (float)(method6() - var3)).length());
         this.field3.setFocused(true);
      } else {
         this.field3.setFocused(false);
      }

      if (method0(Class7.j_ + 94.0D, Class7.i_ + 37.599999999999994D, 211.5D, 275.7333333333333D)) {
         double var13 = Class7.i_ + 37.599999999999994D + this.field7 + 8.271999999999998D;
         double var5 = Class7.j_ + 94.0D + 6.345D;
         double var7 = 198.81D;
         double var9 = 17.96205714285714D;
         Iterator var11 = this.method0().iterator();

         while(var11.hasNext()) {
            Module var12 = (Module)var11.next();
            if (var13 + 29.9367619047619D > Class7.i_ + 37.599999999999994D) {
               if (method0(var5 + var7 - var9 - 5.0D, var13 + (29.9367619047619D - var9) / 2.0D, var9, var9)) {
                  this.field2(var12);
                  break;
               }

               if (method0(var5 + var7 - 5.0D - var9 * 2.0D, var13 + (29.9367619047619D - var9) / 2.0D, var9, var9)) {
                  var12.method40();
                  break;
               }
            }

            var13 += 38.2087619047619D;
            if (var13 > Class7.i_ + 313.3333333333333D) {
               break;
            }
         }
      }

      if (this.field2 != null) {
         var3 = t_();
         if ((double)var3 > Class7.i_ + 313.3333333333333D || (double)var3 < Class7.i_) {
            return;
         }

         Iterator var4 = this.field2.method41().iterator();

         while(var4.hasNext()) {
            Class422 var14 = (Class422)var4.next();
            var14.method1(var1);
         }
      }

   }

   public void method0(float var1) {
      double var2 = this.field17 + (this.field7 - this.field17) * (double)var1;
      double var4 = Class7.i_ + 37.599999999999994D + var2 + 8.271999999999998D;
      double var6 = Class7.j_ + 94.0D + 6.345D;
      double var8 = 198.81D;
      double var10 = 17.96205714285714D;
      this.field3.xPosition = (int)(Class7.j_ + 94.0D + 10.575000000000001D);
      this.field3.yPosition = (int)(Class7.i_ + 9.399999999999999D);
      Iterator var12 = this.method0().iterator();

      while(var12.hasNext()) {
         Module var13 = (Module)var12.next();
         if (var4 + 29.9367619047619D > Class7.i_ + 37.599999999999994D) {
            boolean var14 = method0(var6 + var8 - var10 - 5.0D, var4 + (29.9367619047619D - var10) / 2.0D, var10, var10);
            Color var15 = Class307.method0(Color.WHITE, FlightModule.method7(), var13.method36().method2());
            boolean var16 = method0(var6, var4, var8, 29.9367619047619D);
            if (!var16) {
               NamesOnlyModule.method0(var6, var4, var8, 29.9367619047619D, 7.5D, field6);
            } else {
               NamesOnlyModule.method0(var6 - 1.0D, var4 - 1.0D, var8 + 2.0D, 31.9367619047619D, 8.0D, new Color(45, 45, 45));
            }

            double var10000 = (double)Class311.field11.method0(var13.d_());
            Class311.field11.method1(var13.d_(), (float)(var6 + 8.271999999999998D), (float)(var4 + (29.9367619047619D - (double)Class311.field11.method0()) / 2.0D), var15.getRGB());
            var13.method36().field5 = method0(var6 + var8 - 5.0D - var10 * 2.0D, var4 + (29.9367619047619D - var10) / 2.0D, var10, var10);
            Color var19 = Class307.method0(Color.WHITE, Color.LIGHT_GRAY, var13.method36().method0());
            var15 = Class307.method0(var19, FlightModule.method7(), var13.method36().method2());
            AutoBlazeModule.method0(this.field2 == var13 ? Color.GRAY : (var14 ? Color.LIGHT_GRAY : Color.white));
            SumoFencesModule.method0(field8, (float)(var6 + var8 - var10 - 5.0D), (float)(var4 + (29.9367619047619D - var10) / 2.0D), (float)var10, (float)var10);
            AutoBlazeModule.method0(var15);
            SumoFencesModule.method0(field15, (float)(var6 + var8 - 5.0D - var10 * 2.0D), (float)(var4 + (29.9367619047619D - var10) / 2.0D), (float)var10, (float)var10);
            AutoBlazeModule.method0(var15);
            SumoFencesModule.method0(field1, (float)(var6 + var8 - 5.0D - var10 * 2.0D - (var10 / 2.0D - 1.0D) * (double)(1.0F - var13.method36().method2())), (float)(var4 + (29.9367619047619D - var10) / 2.0D), (float)var10, (float)var10);
         }

         var4 += 38.2087619047619D;
         if (var4 > Class7.i_ + 313.3333333333333D) {
            break;
         }
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F);
      Class178.method0(Class7.j_ + 305.5D - 2.0D, Class7.i_, Class7.j_ + 305.5D, Class7.i_ + 313.3333333333333D, field6.getRGB());
      double var24 = Math.max(0.0D, (double)this.method0().size() + 0.3D) * 38.2087619047619D;
      double var25;
      double var26;
      if (this.field4) {
         var25 = Class207.method0((double)t_() - (Class7.i_ + 37.599999999999994D), 275.7333333333333D, 0.0D);
         var26 = Math.min(0.0D, -((double)this.method0().size() - 5.7D) * 38.2087619047619D);
         this.field20 = var25 / 275.7333333333333D * var26;
      }

      var25 = (var24 == 0.0D ? 1.0D : -var2 / var24) * 313.3333333333333D * 0.88D;
      var26 = Class207.method0(var24 == 0.0D ? 1.0D : 275.7333333333333D / var24, 1.0D, 0.0D) * 313.3333333333333D * 0.88D;
      Class178.method0(Class7.j_ + 305.5D - 2.0D, Class7.i_ + 37.599999999999994D + var25, Class7.j_ + 305.5D, Class7.i_ + 37.599999999999994D + var25 + var26, Color.LIGHT_GRAY.getRGB());
      Class178.method0(Class7.j_ + 305.5D, Class7.i_, Class7.j_ + 470.0D, Class7.i_ + 313.3333333333333D, Class7.field6.getRGB());
      if (this.field2 != null) {
         double var18 = this.field2.method36().method1();
         var4 = Class7.i_ + 37.599999999999994D + var18 + 8.271999999999998D;
         Iterator var20 = this.field2.method41().iterator();

         while(var20.hasNext()) {
            Class422 var21 = (Class422)var20.next();
            if (var21.method2()) {
               if (var4 + var21.method0() >= Class7.i_) {
                  var21.method0(var4, var1);
               }

               var4 += var21.method0() + Class422.method7();
               if (var4 > Class7.i_ + 313.3333333333333D) {
                  break;
               }
            }
         }
      }

      Class178.method0(Class7.j_, Class7.i_, Class7.j_ + 470.0D, Class7.i_ + 37.599999999999994D, Class7.field6.getRGB());
      Class311.field18.method1("ORINGO", (float)(Class7.j_ + 9.4D), (float)(Class7.i_ + (37.599999999999994D - (double)Class311.field8.method0()) / 2.0D - (double)Class311.field7.method0() / 2.0D - 1.0D), -1);
      Class311.field7.method1("v2.8.0", (float)(Class7.j_ + 9.4D), (float)(Class7.i_ + (37.599999999999994D - (double)Class311.field8.method0()) / 2.0D - (double)Class311.field7.method0() / 2.0D + 11.0D), -2236963);
      if (this.field2 != null) {
         Class311.field6.method1(this.field2.d_(), (float)(Class7.j_ + 305.5D + 6.345D), (float)(Class7.i_ + (37.599999999999994D - (double)Class311.field8.method0()) / 2.0D), -1);
      }

      Class178.method0(Class7.j_, Class7.i_ + 37.599999999999994D - 1.0D, Class7.j_ + 470.0D, Class7.i_ + 37.599999999999994D, Color.DARK_GRAY.darker().getRGB());
      boolean var27 = method0(Class7.j_ + 94.0D + 10.575000000000001D, Class7.i_ + 9.399999999999999D, 190.35D, 18.799999999999997D);
      if (var27) {
         ModHiderModule.method0((float)(Class7.j_ + 94.0D + 10.575000000000001D), (float)(Class7.i_ + 9.399999999999999D), 190.35F, 18.8F, 8.0F, 1.0F, new Color(45, 45, 45), Color.WHITE.darker());
      } else {
         NBTCopyModule.method0((float)(Class7.j_ + 94.0D + 10.575000000000001D), (float)(Class7.i_ + 9.399999999999999D), 190.35F, 18.8F, 7.5F, field5);
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      SumoFencesModule.method0(field19, (float)(Class7.j_ + 305.5D - 30.784999999999997D), (float)(Class7.i_ + 9.399999999999999D + 1.4099999999999997D), 15.98F, 15.98F);
      double var28 = Class7.i_ + 9.399999999999999D + (18.799999999999997D - (double)Class311.field11.method0()) / 2.0D;
      if (!this.field3.isFocused() && this.field3.getText().isEmpty()) {
         Class311.field11.method1("Search...", (float)(Class7.j_ + 94.0D + 10.575000000000001D + 7.0D), (float)var28, Color.white.getRGB());
      } else if (!this.field3.isFocused()) {
         Class311.field11.method1(this.field3.getText(), (float)(Class7.j_ + 94.0D + 10.575000000000001D + 7.0D), (float)var28, Color.white.getRGB());
      } else {
         String var29 = this.field3.getText();
         String var22 = var29.substring(0, this.field3.getCursorPosition());
         String var23 = var29.substring(this.field3.getCursorPosition());
         if (this.field3.getSelectionEnd() == 0 && this.field3.getCursorPosition() != 0) {
            Class178.method0((double)((float)(Class7.j_ + 94.0D + 10.575000000000001D + 7.0D)), var28 - 1.0D, Class7.j_ + 94.0D + 10.575000000000001D + 7.0D + (double)Class311.field11.method0(var22), var28 + (double)Class311.field11.method7(), (new Color(0, 103, 211)).getRGB());
         }

         Class311.field11.method1(var23, Class311.field11.method1("|", Class311.field11.method1(var22, (float)(Class7.j_ + 94.0D + 10.575000000000001D + 7.0D), (float)var28, -1), (float)var28, (new Color(255, 255, 255, System.currentTimeMillis() % 1000L > 500L ? 0 : 255)).getRGB()), (float)var28, -1);
      }

   }

   public void method3() {
      this.method10();

      Iterator var1;
      Module var2;
      for(var1 = Class362.method0().iterator(); var1.hasNext(); var2.method36().field1 += ((float)(var2.method36().field5 ? 1 : 0) - var2.method36().field1) / 5.0F) {
         var2 = (Module)var1.next();
         var2.method36().field2 = var2.method36().field0;
         var2.method36().field0 += ((float)(var2.method44() ? 1 : 0) - var2.method36().field0) / 2.0F;
         var2.method36().f_ = var2.method36().field1;
      }

      if (this.field2 != null) {
         var1 = this.field2.method41().iterator();

         while(var1.hasNext()) {
            Class422 var3 = (Class422)var1.next();
            var3.method3();
         }
      }

   }

   public void q_() {
      this.field7 = 0.0D;
      this.field17 = 0.0D;
      this.field20 = 0.0D;
      this.field3.setFocused(false);
      this.field3.setText("");
      this.field4 = false;
      Iterator var1 = Class362.method0().iterator();

      while(var1.hasNext()) {
         Module var2 = (Module)var1.next();
         var2.method36().field0 = var2.method36().field2 = var2.method44() ? 1.0F : 0.0F;
         Iterator var3 = var2.method41().iterator();

         while(var3.hasNext()) {
            Class422 var4 = (Class422)var3.next();
            var4.q_();
         }
      }

   }

   public void method10() {
      double var1 = (double)Mouse.getDWheel() / 5.0D;
      if (method0(Class7.j_ + 94.0D, Class7.i_ + 37.599999999999994D, 211.5D, 275.7333333333333D)) {
         this.field20 += var1;
      }

      double var3 = Math.min(0.0D, -((double)this.method0().size() - 6.7D) * 38.2087619047619D);
      if (this.field20 <= 0.0D && var3 <= 0.0D) {
         if (this.field20 < var3) {
            this.field20 = var3;
         }
      } else {
         this.field20 = 0.0D;
      }

      this.field17 = this.field7;
      this.field7 += (this.field20 - this.field7) / 1.5D;
      if (this.field2 != null) {
         Class6 var5 = this.field2.method36();
         if (method0(Class7.j_ + 305.5D, Class7.i_ + 37.599999999999994D, 164.5D, 275.7333333333333D)) {
            var5.field4 += var1;
         }

         var3 = 8.271999999999998D;
         Iterator var6 = this.field2.method41().iterator();

         while(var6.hasNext()) {
            Class422 var7 = (Class422)var6.next();
            if (var7.method2()) {
               var3 += var7.method0() + Class422.method7();
            }
         }

         if (var5.field4 <= 0.0D && var3 >= 275.7333333333333D) {
            if (var5.field4 < -var3 + 275.7333333333333D) {
               var5.field4 = -var3 + 275.7333333333333D;
            }
         } else {
            var5.field4 = 0.0D;
         }

         var5.field7 = var5.g_;
         var5.g_ += (var5.field4 - var5.g_) / 1.5D;
      }

   }

   public void field2(Module var1) {
      if (var1 != this.field2) {
         this.field2 = var1;
         Iterator var2 = this.field2.method41().iterator();

         while(var2.hasNext()) {
            Class422 var3 = (Class422)var2.next();
            var3.q_();
         }

         Class6 var4 = this.field2.method36();
         var4.field4 = var4.g_ = var4.field7 = 0.0D;
      }
   }

   public static int[] method0(Block[] var0) {
      int[] var1 = new int[var0.length];

      for(int var2 = 0; var2 < var0.length; ++var2) {
         var1[var2] = Block.BLOCK_STATE_IDS.get(var0[var2].getDefaultState()) >> 4;
      }

      return var1;
   }

   public abstract List method0();

   public void method0(int var1) {
      this.field4 = false;
      if (this.field2 != null) {
         Iterator var2 = this.field2.method41().iterator();

         while(var2.hasNext()) {
            Class422 var3 = (Class422)var2.next();
            var3.method0(var1);
         }
      }

   }

   public void method1() {
   }

   public Class457(String var1, ResourceLocation var2, Class442 var3) {
      super(var1, var2, var3);
   }
}
