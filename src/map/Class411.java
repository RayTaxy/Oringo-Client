package map;

import java.awt.Color;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import oringo.event.Class148;
import oringo.event.Class217;
import oringo.module.AutoBlazeModule;
import oringo.module.AutoFishModule;
import oringo.module.DerpModule;
import oringo.module.FlightModule;
import oringo.module.NamesOnlyModule;
import oringo.module.SumoFencesModule;
import oringo.setting.DoubleSetting;

public class Class411 extends Class122 {
   public boolean field0;
   public float field2;
   public boolean field3;
   public String cy_;
   public double field5;
   public double field6;
   public float field7;

   public void method0(int var1) {
      this.field3 = false;
   }

   public boolean method0(char var1, int var2) {
      if (!this.method2()) {
         return false;
      } else {
         double var3 = this.method6();
         if (!Class217.method0(var3, this.field14 + (double)Class311.field16.method0() + 4.0D, 148.003D, 10.0D) || var2 != 203 && var2 != 205) {
            if (this.field0) {
               if (var2 == 1) {
                  this.field0 = false;
               } else if (var2 == 28) {
                  this.field0 = false;
                  String var5 = this.cy_.toLowerCase().replaceAll(",", ".").replaceAll(" ", "");

                  try {
                     ((DoubleSetting)this.field1).method0(Double.parseDouble(var5));
                  } catch (NumberFormatException var7) {
                  }
               } else if (var2 == 14) {
                  this.cy_ = this.cy_.substring(0, Math.max(0, this.cy_.length() - 1));
               } else if (GuiScreen.isKeyComboCtrlV(var2)) {
                  this.cy_ = this.cy_ + GuiScreen.getClipboardString();
               } else {
                  this.cy_ = this.cy_ + var1;
               }

               return true;
            } else {
               return false;
            }
         } else {
            ((DoubleSetting)this.field1).method0(((DoubleSetting)this.field1).method0() + (var2 == 203 ? -((DoubleSetting)this.field1).method4() : ((DoubleSetting)this.field1).method4()));
            return true;
         }
      }
   }

   public static void method4() {
      AutoFishModule.method3(new C08PacketPlayerBlockPlacement(AutoFishModule.field58.thePlayer.getHeldItem()));
      AutoFishModule.field58.thePlayer.swingItem();
   }

   public void method3() {
      this.field5 = this.field6;
      this.field6 += (((DoubleSetting)this.field1).method0() - this.field6) / 2.0D;
      this.field7 = this.field2;
      this.field2 += ((float)(this.field3 ? 1 : 0) - this.field2) / 2.0F;
   }

   public static boolean method0(String var0) {
      return var0.isEmpty();
   }

   public void method1(int var1) {
      if (this.method2()) {
         double var2 = this.method6();
         float var4 = 30.0F;
         if (Class217.method0(var2, this.field14 + (double)Class311.field16.method0() + 4.0D, 148.003D, 10.0D)) {
            if (var1 == 2) {
               ((DoubleSetting)this.field1).method0(((DoubleSetting)this.field1).field3);
               return;
            }

            double var5 = Math.min(1.0D, Math.max(0.0D, ((double)Class148.method6() - var2) / 148.003D));
            ((DoubleSetting)this.field1).method0(var5 * (((DoubleSetting)this.field1).method2() - ((DoubleSetting)this.field1).method5()) + ((DoubleSetting)this.field1).method5());
            this.field3 = true;
         }

         String var7 = ((DoubleSetting)this.field1).method0() + ((DoubleSetting)this.field1).method6();
         float var6 = Class311.field16.method0(var7);
         if (Class217.method0(var2 + 148.003D - (double)Class311.field16.method0(var7), this.field14 - 2.0D, (double)var6, (double)(Class311.field16.method0() + 4.0F))) {
            this.field0 = true;
            this.cy_ = String.valueOf(((DoubleSetting)this.field1).method0());
         } else {
            this.field0 = false;
         }
      }
   }

   public void method0(double var1, float var3) {
      String var4 = String.valueOf(((DoubleSetting)this.field1).method0());
      if (((DoubleSetting)this.field1).method0() % 1.0D == 0.0D) {
         var4 = String.valueOf(((DoubleSetting)this.field1).method3());
      }

      this.field14 = var1;
      double var5 = this.method6();
      float var7 = 30.0F - 10.0F * (this.field7 + (this.field2 - this.field7));
      Class311.field16.method1(((DoubleSetting)this.field1).cW_, (float)var5, (float)(var1 + 2.0D), Color.white.getRGB());
      String var8;
      if (!this.field0) {
         var8 = var4 + ((DoubleSetting)this.field1).method6();
         Class311.field16.method1(var8, (float)(var5 + 148.003D - (double)Class311.field16.method0(var8)), (float)(var1 + 2.0D), Color.white.getRGB());
      } else {
         var8 = this.cy_;
         float var9 = Class311.field16.method0(var8 + ((DoubleSetting)this.field1).method6());
         NamesOnlyModule.method0(var5 + 148.003D - (double)Class311.field16.method0(var8) - 2.0D, var1 - 1.0D, (double)(var9 + 4.0F), (double)(Class311.field16.method7() + 4.0F), 3.5D, Class7.field10.darker());
         Class311.field16.method1(((DoubleSetting)this.field1).method6(), Class311.field16.method1("|", Class311.field16.method1(var8, (float)(var5 + 148.003D - (double)var9), (float)(var1 + 2.0D), -1), (float)(var1 + 2.0D), System.currentTimeMillis() % 1000L > 500L ? -1 : 0), (float)(var1 + 2.0D), -1);
      }

      Class178.method0(var5, var1 + (double)Class311.field16.method0() + 4.0D + 5.5D, var5 + 148.003D, var1 + (double)Class311.field16.method0() + 4.0D + 6.5D, DerpModule.method2().getRGB());
      double var12 = this.field5 + (this.field6 - this.field5) * (double)var3;
      AutoBlazeModule.method0(FlightModule.method7());
      if (this.field3) {
         double var10 = Math.min(1.0D, Math.max(0.0D, ((double)Class148.method6() - var5) / 148.003D));
         ((DoubleSetting)this.field1).method0(var10 * (((DoubleSetting)this.field1).method2() - ((DoubleSetting)this.field1).method5()) + ((DoubleSetting)this.field1).method5());
      }

      SumoFencesModule.method0(Class124.field1, (float)(var5 - (double)(var7 / 2.0F + var7 / 4.0F) + (var12 - ((DoubleSetting)this.field1).method5()) / (((DoubleSetting)this.field1).method2() - ((DoubleSetting)this.field1).method5()) * 148.003D), (float)(var1 + (double)Class311.field16.method0() + 4.0D + 6.0D - (double)(var7 / 2.0F)), var7, var7);
   }

   public void q_() {
      this.field3 = false;
      this.field6 = this.field5 = ((DoubleSetting)this.field1).method0();
      this.field2 = this.field7 = 0.0F;
   }

   public double method0() {
      return (double)(Class311.field16.method0() + 9.0F);
   }

   public Class411(DoubleSetting var1) {
      super(var1);
   }
}
