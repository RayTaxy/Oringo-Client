package oringo.module;

import oringo.setting.DoubleSetting;

class AimAssistModule$2 extends DoubleSetting {
   final AimAssistModule field0;

   AimAssistModule$2(AimAssistModule var1, String var2, double var3, double var5, double var7, double var9) {
      super(var2, var3, var5, var7, var9);
      this.field0 = var1;
   }

   public void method0(double var1) {
      super.method0(var1);
      if (this.method0() > this.field0.field3.method0()) {
         this.method0(this.field0.field3.method0());
      }

   }
}
