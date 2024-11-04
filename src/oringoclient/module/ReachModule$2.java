package oringo.module;

import oringo.setting.DoubleSetting;

class ReachModule$2 extends DoubleSetting {
   final ReachModule field0;

   public void method0(double var1) {
      if (this.field0.field3.method0() > var1) {
         this.field0.field3.method0(var1);
      }

      super.method0(var1);
   }

   ReachModule$2(ReachModule var1, String var2, double var3, double var5, double var7, double var9) {
      super(var2, var3, var5, var7, var9);
      this.field0 = var1;
   }
}
