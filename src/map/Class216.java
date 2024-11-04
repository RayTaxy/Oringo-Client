package map;

import java.awt.Color;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.util.Iterator;
import net.minecraft.entity.item.EntityArmorStand;
import org.lwjgl.opengl.GL11;
import oringo.event.Class217;
import oringo.module.AutoFishModule;
import oringo.module.FrozenTreasureESPModule;
import oringo.module.HitboxesModule;
import oringo.setting.EnumSetting;

public class Class216 extends Class122 {
   public boolean field54;
   public double bU_;
   public double field0;

   public static Transferable method0(Clipboard var0, Object var1) {
      return var0.getContents(var1);
   }

   public void method3() {
      this.bU_ = this.field0;
      this.field0 += ((double)(this.field54 ? 1 : 0) - this.field0) / 2.0D;
   }

   public Class216(EnumSetting var1) {
      super(var1);
   }

   public double method0(float var1) {
      return (double)((float)(((EnumSetting)this.field1).method3().size() - 1) * (Class311.field11.method0() + 6.0F)) * (this.bU_ + (this.field0 - this.bU_) * (double)var1) + (double)Class311.field16.method3();
   }

   public boolean method0(char var1, int var2) {
      return false;
   }

   public static void f_() {
      AutoFishModule.field2 = true;
   }

   public void q_() {
      this.field54 = false;
      this.field0 = 0.0D;
   }

   public static void method0(EntityArmorStand var0, boolean var1) {
      var0.getDataWatcher().updateObject(10, var1 ? 1 : 0);
   }

   public double method0() {
      return this.method0(FrozenTreasureESPModule.method5().renderPartialTicks);
   }

   public void method1(int var1) {
      if (this.method2()) {
         double var2 = this.method6();
         double var4 = this.method5() + 10.0D;
         if (Class217.method0(var2 + 148.003D - var4, this.field14, var4, (double)(Class311.field11.method0() + 6.0F))) {
            if (var1 == 2) {
               ((EnumSetting)this.field1).d_(((EnumSetting)this.field1).field2);
            } else {
               this.field54 = !this.field54;
            }
         } else {
            if (this.field54) {
               double var6 = this.field14 + 2.0D;
               Iterator var8 = ((EnumSetting)this.field1).method3().iterator();

               while(var8.hasNext()) {
                  String var9 = (String)var8.next();
                  if (!var9.equals(((EnumSetting)this.field1).method4()) && Class217.method0(var2 + 148.003D - var4, var6 += (double)(6.0F + Class311.field11.method0()), var4, (double)(Class311.field11.method0() + 6.0F))) {
                     this.field54 = false;
                     ((EnumSetting)this.field1).d_(var9);
                     break;
                  }
               }
            }

         }
      }
   }

   public double method5() {
      double var1 = 0.0D;
      Iterator var3 = ((EnumSetting)this.field1).method3().iterator();

      while(var3.hasNext()) {
         String var4 = (String)var3.next();
         double var5 = (double)Class311.field16.method0(var4);
         if (var5 > var1) {
            var1 = var5;
         }
      }

      return var1;
   }

   public void method0(double var1, float var3) {
      this.field14 = var1;
      double var4 = this.method6();
      double var6 = this.method5() + 10.0D;
      Class311.field16.method1(((EnumSetting)this.field1).cW_, (float)var4, (float)(var1 + 3.0D), Color.white.getRGB());
      GL11.glEnable(3089);
      Class1.method0(var4, Math.floor(var1), 150.003D, Math.ceil(this.method0(var3) + 3.0D));
      HitboxesModule.method0(var4 + 148.003D - var6, var1, var6, this.method0(var3) + 2.0D, 7.0D, Class7.field10.getRGB());
      double var8 = var1 + 3.0D;
      Class311.field16.method2(((EnumSetting)this.field1).method4(), (float)(var4 + 148.003D - var6 / 2.0D), (float)var8, Color.white.getRGB());
      if (this.field0 != 0.0D || this.bU_ != 0.0D) {
         Iterator var10 = ((EnumSetting)this.field1).method3().iterator();

         while(var10.hasNext()) {
            String var11 = (String)var10.next();
            if (!var11.equals(((EnumSetting)this.field1).method4())) {
               Class311.field16.method2(var11, (float)(var4 + 148.003D - var6 / 2.0D), (float)(var8 += (double)(6.0F + Class311.field11.method0())), Color.white.getRGB());
            }
         }
      }

      GL11.glDisable(3089);
   }

   public void method0(int var1) {
   }
}
