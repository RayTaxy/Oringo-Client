package oringo.module;

import oringo.setting.DoubleSetting;

final class KillAuraModule$3 extends DoubleSetting {
   KillAuraModule$3(String var1, double var2, double var4, double var6, double var8) {
      super(var1, var2, var4, var6, var8);
   }

   public void method0(double var1) {
      super.method0(var1);
      if (KillAuraModule.field8.method0() > this.method0()) {
         this.method0(KillAuraModule.field8.method0());
      }

   }

   public boolean g_() {
      return !KillAuraModule.field20.method0(0);
   }
}
