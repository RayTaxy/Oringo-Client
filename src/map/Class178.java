package map;

import java.awt.Color;
import oringo.module.VanquisherESPModule;

public class Class178 {
   public final Class424 field0;

   public Class178(Class424 var1) {
      this.field0 = var1;
   }

   public static void method0(double var0, double var2, double var4, double var6, int var8) {
      Class469.method0((float)var0, (float)var2, (float)var4, (float)var6, var8);
   }

   public Color method0(boolean var1) {
      return var1 ? this.field0.method0() : this.field0.method0().darker();
   }

   public static Class208 method0(int var0, int var1) {
      int var2 = Class475.method0(var0, var1);
      if (var2 == -318865360) {
         return null;
      } else {
         Class374 var3 = (Class374)Class528.field0.get(var2);
         if (var3 == null) {
            return null;
         } else {
            Class507 var4 = VanquisherESPModule.method0(var2);
            Class208 var5 = new Class208(var3, var2, var4, var0, var1);
            Class119.method0(var0, var1, var5);
            return var5;
         }
      }
   }
}
