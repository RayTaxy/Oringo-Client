package oringo.module;

import oringo.setting.DoubleSetting;

class ReachModule$1 extends DoubleSetting {
   final ReachModule field0;

   ReachModule$1(ReachModule var1, String var2, double var3, double var5, double var7, double var9) {
      super(var2, var3, var5, var7, var9);
      this.field0 = var1;
   }

   public void method0(double var1) {
      if (this.field0.field0.method0() < var1) {
         this.field0.field0.method0(var1);
      }

      super.method0(var1);
   }
}
