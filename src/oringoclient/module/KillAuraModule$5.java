package oringo.module;

import oringo.setting.DoubleSetting;

final class KillAuraModule$5 extends DoubleSetting {
   KillAuraModule$5(String var1, double var2, double var4, double var6, double var8) {
      super(var1, var2, var4, var6, var8);
   }

   public void method0(double var1) {
      super.method0(var1);
      if (KillAuraModule.field23.method0() > this.method0()) {
         this.method0(KillAuraModule.field23.method0());
      }

   }
}
