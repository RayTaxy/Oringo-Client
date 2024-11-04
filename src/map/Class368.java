package map;

import java.awt.Color;
import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import java.util.Iterator;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import oringo.event.Class217;
import oringo.module.NamesOnlyModule;
import oringo.setting.StringSetting;

public class Class368 extends Class122 {
   public boolean field0;

   public void method0(int var1) {
   }

   public void method1(int var1) {
      if (this.method2()) {
         this.field0 = false;
         double var2 = this.method6();
         if (Class217.method0(var2 + 74.0015D, this.field14, 74.0015D, this.method0())) {
            if (var1 == 0) {
               this.field0 = true;
            } else if (var1 == 1) {
               ((StringSetting)this.field1).method1("");
            } else if (var1 == 2) {
               ((StringSetting)this.field1).method1(((StringSetting)this.field1).field0);
            }
         }

      }
   }

   public void method3() {
   }

   public static double method0(EntityLivingBase var0) {
      try {
         return var0.getAttributeMap().getAttributeInstanceByName("generic.movementSpeed").getBaseValue();
      } catch (Exception var2) {
         return (double)var0.getMaxHealth();
      }
   }

   public static Matrix4f method2(int var0) {
      FloatBuffer var1 = BufferUtils.createFloatBuffer(16);
      GL11.glGetFloat(var0, var1);
      return (Matrix4f)(new Matrix4f()).load(var1);
   }

   public boolean method0(char var1, int var2) {
      if (this.field0 && this.method2()) {
         if (var2 != 1 && var2 != 28) {
            if (var2 == 14) {
               if (!Keyboard.isKeyDown(157) && !Keyboard.isKeyDown(29)) {
                  ((StringSetting)this.field1).method1(((StringSetting)this.field1).method1().substring(0, Math.max(0, ((StringSetting)this.field1).method1().length() - 1)));
               } else {
                  ((StringSetting)this.field1).method1(((StringSetting)this.field1).method1().substring(0, Math.max(0, ((StringSetting)this.field1).method1().lastIndexOf(" "))));
               }
            } else if (GuiScreen.isKeyComboCtrlV(var2)) {
               ((StringSetting)this.field1).method1(((StringSetting)this.field1).method1() + GuiScreen.getClipboardString());
            } else {
               ((StringSetting)this.field1).method1(((StringSetting)this.field1).method1() + var1);
            }
         } else {
            this.field0 = false;
         }

         return true;
      } else {
         return false;
      }
   }

   public Class368(StringSetting var1) {
      super(var1);
   }

   public double method0() {
      return (double)(Class311.field16.method3() * (float)Class311.field16.method0(((StringSetting)this.field1).cW_, 74.0015F).size());
   }

   public static void method0(Field var0, boolean var1) {
      var0.setAccessible(var1);
   }

   public void method0(double var1, float var3) {
      this.field14 = var1;
      double var4 = this.method6();
      double var6 = var1 + 2.0D;

      for(Iterator var8 = Class311.field16.method0(((StringSetting)this.field1).cW_, 74.0015F).iterator(); var8.hasNext(); var6 += (double)(Class311.field16.method0() + 4.0F)) {
         String var9 = (String)var8.next();
         Class311.field16.method1(var9, (float)var4, (float)var6, Color.white.getRGB());
      }

      NamesOnlyModule.method0(var4 + 74.0015D, var1 + (this.method0() - (double)(Class311.field16.method0() + 4.0F)) / 2.0D, 74.0015D, (double)(Class311.field16.method0() + 4.0F), 3.5D, Class7.field10.darker());
      Class311.field7.method1(Class311.field7.method0(((StringSetting)this.field1).method1(), 64.0F, this.field0) + (this.field0 && System.currentTimeMillis() % 1000L > 500L ? "|" : ""), (float)(var4 + 74.0015D + 5.0D), (float)(var1 + (this.method0() - (double)Class311.field7.method0()) / 2.0D), Color.white.getRGB());
   }

   public void q_() {
      this.field0 = false;
   }
}
