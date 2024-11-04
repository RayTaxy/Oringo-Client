package map;

import java.util.Iterator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import oringo.event.Class217;
import oringo.module.FrozenTreasureESPModule;
import oringo.module.Module;
import oringo.module.SumoFencesModule;
import oringo.setting.Setting;

public class Class247 extends Class422 {
   public final Module bT_;
   public double bU_;
   public double bV_;
   public boolean field54;
   public static final ResourceLocation field4 = new ResourceLocation("oringoclient", "icons/expand.png");
   public double field0;

   public Class247(Module var1) {
      this.bT_ = var1;
   }

   public static String method0(StringBuilder var0) {
      return var0.toString();
   }

   public void method1(int var1) {
      if (this.method2()) {
         if (Class217.method0(this.method6(), this.field14, 148.003D, (double)(Class311.field16.method0() + 4.0F))) {
            this.field54 = !this.field54;
         }
      }
   }

   public double method0() {
      return this.bT_.field61 == null ? 0.0D : (double)(Class311.field16.method0() + 4.0F) + (this.bV_ - (double)Class311.field16.method0() - 4.0D) * (this.bU_ + (this.field0 - this.bU_) * (double)FrozenTreasureESPModule.method5().renderPartialTicks);
   }

   public void q_() {
      this.field0 = 0.0D;
      this.field54 = false;
   }

   public void method0(int var1) {
   }

   public void method3() {
      this.bU_ = this.field0;
      this.field0 += ((double)(this.field54 ? 1 : 0) - this.field0) / 2.0D;
   }

   public boolean method2() {
      return this.bT_.field61 != null;
   }

   public void method0(double var1, float var3) {
      this.field14 = var1;
      double var4 = this.method6();
      GL11.glEnable(3089);
      Class1.method0(var4, Math.floor(this.field14), 156.2515D, Math.ceil(this.method0()));
      this.bV_ = 0.0D;
      Class311.field16.method1("Description", (float)var4, (float)(this.field14 + 2.0D), -1);
      float var6 = (Class311.field16.method0() + 4.0F) / 2.0F;
      GL11.glPushMatrix();
      GL11.glTranslated(var4 + 148.003D - (double)var6, this.field14 + (double)var6, 0.0D);
      GL11.glRotated(180.0D * (this.bU_ + (this.field0 - this.bU_) * (double)FrozenTreasureESPModule.method5().renderPartialTicks), 0.0D, 0.0D, 1.0D);
      GL11.glTranslatef(-var6, -var6, 0.0F);
      SumoFencesModule.method0(field4, 0.0F, 0.0F, var6 * 2.0F, var6 * 2.0F);
      GL11.glPopMatrix();
      this.bV_ += (double)(Class311.field16.method0() + 4.0F);
      String[] var7 = this.bT_.field61.split("\n");
      int var8 = var7.length;

      String var12;
      for(int var9 = 0; var9 < var8; ++var9) {
         String var10 = var7[var9];

         for(Iterator var11 = Class311.field7.method0(var10, 148.003F).iterator(); var11.hasNext(); this.bV_ += (double)(Class311.field7.method0() + 4.0F)) {
            var12 = (String)var11.next();
            Class311.field7.method1(var12, (float)var4, (float)(this.field14 + this.bV_ + 2.0D), -1);
         }
      }

      Iterator var15 = this.bT_.method47().iterator();

      while(true) {
         Setting var16;
         do {
            do {
               if (!var15.hasNext()) {
                  GL11.glDisable(3089);
                  return;
               }

               var16 = (Setting)var15.next();
            } while(var16.field61 == null);
         } while(var16.g_());

         this.bV_ += (double)Class311.field7.method0() * 0.75D;
         String[] var17 = ("· " + var16.cW_ + " - " + var16.field61).split("\n");
         int var18 = var17.length;

         for(int var19 = 0; var19 < var18; ++var19) {
            var12 = var17[var19];

            for(Iterator var13 = Class311.field7.method0(var12, 148.003F).iterator(); var13.hasNext(); this.bV_ += (double)(Class311.field7.method0() + 4.0F)) {
               String var14 = (String)var13.next();
               Class311.field7.method1(var14, (float)var4, (float)(this.field14 + this.bV_ + 2.0D), -1);
            }
         }
      }
   }

   public boolean method0(char var1, int var2) {
      return false;
   }
}
