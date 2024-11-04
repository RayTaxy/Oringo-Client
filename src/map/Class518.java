package map;

import java.awt.Color;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import oringo.event.Class217;
import oringo.module.HitboxesModule;
import oringo.setting.ButtonSetting;

public class Class518 extends Class122 {
   public Class518(ButtonSetting var1) {
      super(var1);
   }

   public void method1(int var1) {
      if (Class217.method0(this.method6(), this.field14, 148.003D, this.method0())) {
         ((ButtonSetting)this.field1).method0();
      }

   }

   public static FloatBuffer method2(int var0) {
      FloatBuffer var1 = BufferUtils.createFloatBuffer(16);
      GL11.glGetFloat(var0, var1);
      return var1;
   }

   public static float method0(EntityLivingBase var0) {
      try {
         return (float)var0.getAttributeMap().getAttributeInstanceByName("generic.maxHealth").getBaseValue();
      } catch (Exception var2) {
         return var0.getMaxHealth();
      }
   }

   public boolean method0(char var1, int var2) {
      return false;
   }

   public static Field method0(Class var0, String var1) {
      return var0.getDeclaredField(var1);
   }

   public double method0() {
      return (double)Class311.field16.method3();
   }

   public void q_() {
   }

   public void method3() {
   }

   public void method0(double var1, float var3) {
      this.field14 = var1;
      double var4 = this.method6();
      double var6 = (double)Class311.field16.method0(((ButtonSetting)this.field1).cW_);
      HitboxesModule.method0(var4 + (148.003D - var6) / 2.0D - 2.5D, var1, var6 + 5.0D, this.method0(), 7.0D, Class7.field10.getRGB());
      Class311.field16.method2(((ButtonSetting)this.field1).cW_, (float)(var4 + 74.0015D), (float)(var1 + (this.method0() - (double)Class311.field16.method0()) / 2.0D), Color.WHITE.getRGB());
   }

   public void method0(int var1) {
   }
}
