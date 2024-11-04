package map;

import java.awt.Color;
import java.io.BufferedWriter;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import oringo.event.Class217;
import oringo.module.Module;

public class Class169 extends Class422 {
   public boolean field0;
   public final Module field1;

   public static void method0(BufferedWriter var0, String var1) {
      var0.write(var1);
   }

   public void method0(int var1) {
   }

   public void method0(double var1, float var3) {
      this.field14 = var1;
      String var4 = this.field0 ? "..." : (this.field1.method46() >= 256 ? "" : (this.field1.method46() < 0 ? Mouse.getButtonName(this.field1.method46() + 100) : Keyboard.getKeyName(this.field1.method46())));
      if (var4 == null) {
         var4 = "UNKNOWN";
      } else if (var4.equals("NONE")) {
         var4 = "   ";
      }

      double var5 = this.method6();
      Class311.field16.method1("Keybind", (float)var5, (float)(var1 + (this.method0() - (double)Class311.field16.method0()) / 2.0D), Color.white.getRGB());
      String var7 = "[" + var4 + "]";
      double var8 = (double)Class311.field16.method0(var7);
      Class311.field16.method1(var7, (float)(var5 + 148.003D - var8), (float)(var1 + (this.method0() - (double)Class311.field16.method0()) / 2.0D), Color.white.getRGB());
   }

   public void method1(int var1) {
      double var2 = this.method6();
      if (this.field0) {
         this.field1.method3(var1 - 100);
         this.field0 = false;
      } else {
         if (Class217.method0(var2, this.field14, 148.003D, this.method0())) {
            if (var1 == 1) {
               this.field1.method3(0);
            } else {
               this.field0 = true;
            }
         }

      }
   }

   public Class169(Module var1) {
      this.field1 = var1;
   }

   public void q_() {
      this.field0 = false;
   }

   public void method3() {
   }

   public boolean method0(char var1, int var2) {
      if (!this.field0) {
         return false;
      } else {
         this.field1.method3(var2 != 1 && var2 != Class362.field7.method46() ? var2 : 0);
         this.field0 = false;
         return true;
      }
   }

   public boolean method2() {
      return true;
   }

   public double method0() {
      return (double)Class311.field16.method3();
   }
}
