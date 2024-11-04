package map;

import java.awt.Color;
import java.io.BufferedWriter;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C0BPacketEntityAction.Action;
import net.minecraft.util.ResourceLocation;
import oringo.event.Class217;
import oringo.event.Class307;
import oringo.mixin.EntityPlayerSPAccessor;
import oringo.mixin.MinecraftAccessor;
import oringo.module.AutoBlazeModule;
import oringo.module.DerpModule;
import oringo.module.FlightModule;
import oringo.module.StepModule;
import oringo.module.SumoFencesModule;
import oringo.setting.BooleanSetting;

public class Class492 extends Class122 {
   public float field0;
   public float field2;

   public void method1(int var1) {
      if (this.method2()) {
         double var2 = (double)((float)(this.method0() * 1.4D));
         if (Class217.method0(this.method6() + 148.003D - var2, this.field14 + (this.method0() - var2) / 2.0D, var2, var2)) {
            if (var1 == 2) {
               ((BooleanSetting)this.field1).method0(((BooleanSetting)this.field1).field1);
               return;
            }

            ((BooleanSetting)this.field1).J_();
         }

      }
   }

   public void method0(int var1) {
   }

   public void q_() {
      this.field2 = this.field0 = ((BooleanSetting)this.field1).method1() ? 1.0F : 0.0F;
   }

   public Class492(BooleanSetting var1) {
      super(var1);
   }

   public boolean method0(char var1, int var2) {
      return false;
   }

   public static void method0(double var0) {
      double[] var2 = new double[0];
      if (StepModule.field2.method4().equals("NCP")) {
         if (var0 > 1.0D) {
            var2 = new double[]{0.41999998688698D, 0.7531999805212D, 1.00133597911215D, 1.06083597911215D, 0.9824359775862711D};
         } else if (var0 > 0.87D) {
            EntityPlayerSP var10000 = StepModule.field58.thePlayer;
            var10000.motionX *= 0.5D;
            var10000 = StepModule.field58.thePlayer;
            var10000.motionZ *= 0.5D;
            var2 = new double[]{0.41999998688698D, 0.7531999805212024D, 1.0D};
         } else if (var0 > 0.7D) {
            var2 = new double[]{0.38999998569488525D};
         }

         if (var0 > 0.7D) {
            double[] var3 = var2;
            int var4 = var2.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               double var6 = var3[var5];
               StepModule.method3(new C04PacketPlayerPosition(StepModule.field58.thePlayer.posX, StepModule.field58.thePlayer.posY + var6, StepModule.field58.thePlayer.posZ, false));
            }

            if (((EntityPlayerSPAccessor)StepModule.field58.thePlayer).getServerSneakState()) {
               StepModule.method3(new C0BPacketEntityAction(StepModule.field58.thePlayer, Action.STOP_SNEAKING));
               ((EntityPlayerSPAccessor)StepModule.field58.thePlayer).setServerSneakState(false);
            }

            if (StepModule.cA_.method0() != 1.0D) {
               ((MinecraftAccessor)StepModule.field58).getTimer().timerSpeed = (float)StepModule.cA_.method0();
               StepModule.field3 = true;
            }
         }
      }

   }

   public static void method0(BufferedWriter var0) {
      var0.close();
   }

   public void method0(double var1, float var3) {
      this.field14 = var1;
      double var4 = this.method6();
      Class311.field16.method1(((BooleanSetting)this.field1).cW_, (float)var4, (float)(var1 + (this.method0() - (double)Class311.field16.method0()) / 2.0D), Color.white.getRGB());
      float var6 = this.field2 + (this.field0 - this.field2) * var3;
      ResourceLocation var7 = Class124.field15;
      ResourceLocation var8 = Class124.field1;
      float var9 = (float)(this.method0() * 1.4D);
      AutoBlazeModule.method0(Class307.method0(Color.WHITE, FlightModule.method7(), var6));
      SumoFencesModule.method0(var7, (float)(var4 + 148.003D - (double)var9), (float)(var1 + (this.method0() - (double)var9) / 2.0D), var9, var9);
      AutoBlazeModule.method0(Class307.method0(Color.WHITE, DerpModule.method2(), var6));
      SumoFencesModule.method0(var8, (float)(var4 + 148.003D - (double)var9 - ((double)var9 / 2.0D - 1.0D) * (double)(1.0F - var6)), (float)(var1 + (this.method0() - (double)var9) / 2.0D), var9, var9);
   }

   public void method3() {
      this.field2 = this.field0;
      this.field0 += ((float)(((BooleanSetting)this.field1).method1() ? 1 : 0) - this.field0) / 2.0F;
   }

   public double method0() {
      return this.method2() ? (double)Class311.field16.method3() : 0.0D;
   }
}
