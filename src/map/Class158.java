package map;

import oringo.module.AutoFarmModule;
import oringo.module.HitboxesModule;

public class Class158 implements Class138 {
   public AutoFarmModule field0 = Class229.method1();

   public long method0(Class532 var1, long var2, long var4) {
      long var6 = this.method0(var2, var4);
      var1.method0("Replenish loss/d: §6" + var1.method0(var6));
      return var6;
   }

   public boolean method0() {
      Class258 var1 = this.field0.method13();
      return var1 != Class258.field5 && var1 != Class258.field1;
   }

   public static void method0(Exception var0) {
      var0.printStackTrace();
   }

   public String method1(long var1, long var3) {
      long var5 = this.method0(var1, var3);
      return "Replenish/d: §6" + HitboxesModule.method0(var5);
   }

   public long method0(long var1, long var3) {
      return -var3 * (long)this.field0.method13().method4();
   }
}
