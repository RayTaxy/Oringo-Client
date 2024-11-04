package oringo.module;

import oringo.setting.DoubleSetting;

class AimAssistModule$1 extends DoubleSetting {
   final AimAssistModule field0;

   public void method0(double var1) {
      super.method0(var1);
      if (var1 < this.field0.field9.method0()) {
         this.method0(this.field0.field9.method0());
      }

   }

   AimAssistModule$1(AimAssistModule var1, String var2, double var3, double var5, double var7, double var9) {
      super(var2, var3, var5, var7, var9);
      this.field0 = var1;
   }
}
