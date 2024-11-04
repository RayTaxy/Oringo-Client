package map;

import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.item.EntityArmorStand;
import org.lwjgl.opengl.GL11;
import oringo.event.Class148;
import oringo.event.Class210;
import oringo.event.Class217;
import oringo.module.AutoFishModule;
import oringo.module.AutoRogueModule;
import oringo.module.FrozenTreasureESPModule;
import oringo.module.TerminatorAuraModule;
import oringo.setting.ColorSetting;

public class Class259 extends Class122 {
   public double field0;
   public int field2 = -1;
   public boolean field54;
   public double bU_;

   public static List method4() {
      return AutoFishModule.field58.theWorld.getEntitiesWithinAABB(EntityArmorStand.class, AutoFishModule.field58.thePlayer.getEntityBoundingBox().expand(5.0D, 5.0D, 5.0D), AutoFishModule::lambda$getEntitiesToKill$8);
   }

   public boolean method0(char var1, int var2) {
      return false;
   }

   public void q_() {
      this.field54 = false;
      this.field2 = -1;
      this.field0 = this.bU_ = 0.0D;
   }

   public void method1(int var1) {
      if (this.method2()) {
         double var2 = this.method6();
         if (Class217.method0(var2 + 148.003D - (double)(Class311.field16.method0() + 4.0F), this.field14, (double)(Class311.field16.method0() + 4.0F), (double)(Class311.field16.method0() + 4.0F))) {
            if (var1 == 2) {
               ((ColorSetting)this.field1).method0(((ColorSetting)this.field1).field5);
            } else {
               this.field54 = !this.field54;
               this.field2 = -1;
            }
         } else {
            if (this.field54) {
               if (Class217.method0(var2, this.field14 + (double)Class311.field16.method0() + 4.0D + 8.271999999999998D, 148.003D, 55.0D)) {
                  this.field2 = 0;
               } else if (Class217.method0(var2, this.field14 + (double)Class311.field16.method0() + 4.0D + 16.543999999999997D + 55.0D, 148.003D, 10.0D)) {
                  this.field2 = 1;
               } else if (((ColorSetting)this.field1).method9() && Class217.method0(var2, this.field14 + (double)Class311.field16.method0() + 4.0D + 24.815999999999995D + 65.0D, 148.003D, 10.0D)) {
                  this.field2 = 2;
               }
            }

         }
      }
   }

   public static void method0(Exception var0) {
      var0.printStackTrace();
   }

   public double method0() {
      double var1 = 99.816D;
      if (!((ColorSetting)this.field1).method9()) {
         var1 -= 18.272D;
      }

      return var1 * (this.bU_ + (this.field0 - this.bU_) * (double)FrozenTreasureESPModule.method5().renderPartialTicks) + (double)Class311.field16.method3();
   }

   public void method3() {
      this.bU_ = this.field0;
      this.field0 += ((double)(this.field54 ? 1 : 0) - this.field0) / 2.0D;
   }

   public void method0(double var1, float var3) {
      this.field14 = var1;
      double var4 = this.method6();
      GL11.glEnable(3089);
      Class1.method0(var4, var1, 148.003D, Math.ceil(this.method0()));
      Class311.field16.method1(((ColorSetting)this.field1).cW_, (float)var4, (float)(var1 + 2.0D), Color.white.getRGB());
      Class178.method0(var4 + 148.003D - (double)(Class311.field16.method0() + 4.0F), var1, var4 + 148.003D, var1 + (double)(Class311.field16.method0() + 4.0F), ((ColorSetting)this.field1).method8());
      var1 += (double)(Class311.field16.method0() + 4.0F) + 8.271999999999998D;
      int var6 = Class210.t_();
      int var7 = Class148.method6();
      float var8;
      if (this.field2 == 0) {
         var8 = Class163.method0((float)(1.0D - ((double)var6 - var1) / 55.0D), 1.0F, 0.0F);
         float var9 = Class163.method0((float)(((double)var7 - var4) / 148.003D), 1.0F, 0.0F);
         ((ColorSetting)this.field1).method0(((ColorSetting)this.field1).method5(), var9, var8);
      }

      Class218.method0((float)var4, (float)var1, 148.003F, 55.0F, ((ColorSetting)this.field1).method5());
      var1 += 63.272D;
      if (this.field2 == 1) {
         var8 = Class163.method0((float)(((double)var7 - var4) / 148.003D), 1.0F, 0.0F);
         ((ColorSetting)this.field1).method0(var8, ((ColorSetting)this.field1).method0(), ((ColorSetting)this.field1).method1());
      }

      TerminatorAuraModule.method0((float)var4, (float)var1, 148.003F, 10.0F);
      if (((ColorSetting)this.field1).method9()) {
         if (this.field2 == 2) {
            var8 = Class163.method0((float)(((double)var7 - var4) / 148.003D), 1.0F, 0.0F);
            ((ColorSetting)this.field1).method0(((ColorSetting)this.field1).method5(), ((ColorSetting)this.field1).method0(), ((ColorSetting)this.field1).method1(), (int)(var8 * 255.0F));
         }

         GlStateManager.disableAlpha();
         Class515.method0((float)var4, (float)(var1 + 10.0D + 8.271999999999998D), 148.003F, 10.0F);
         GlStateManager.enableAlpha();
         AutoRogueModule.method0(var4 + 148.003D * (double)((ColorSetting)this.field1).method4() / 255.0D - 2.5D, var1 + 8.271999999999998D + 10.199999809265137D, 5.0D, 10.0D, 5.0D, 1.0D, (new Color(255 - ((ColorSetting)this.field1).method4(), 255 - ((ColorSetting)this.field1).method4(), 255 - ((ColorSetting)this.field1).method4())).getRGB());
      }

      AutoRogueModule.method0(var4 + 148.003D * (double)((ColorSetting)this.field1).method5() - 2.5D, var1 + 0.20000000298023224D, 5.0D, 10.0D, 5.0D, 1.0D, -1);
      var1 -= 63.272D;
      AutoRogueModule.method0(var4 + 148.003D * (double)((ColorSetting)this.field1).method0() - 2.5D, var1 + (double)(55.0F * (1.0F - ((ColorSetting)this.field1).method1())) - 2.5D, 5.0D, 5.0D, 5.0D, 1.0D, -1);
      GL11.glDisable(3089);
   }

   public void method0(int var1) {
      this.field2 = -1;
   }

   public Class259(ColorSetting var1) {
      super(var1);
   }
}
