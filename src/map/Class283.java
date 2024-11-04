package map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import oringo.setting.DoubleSetting;

public class Class283 extends Class72 {
   public float field8;
   public float field7;
   public boolean field3;

   public static JsonObject method0(JsonElement var0) {
      return var0.getAsJsonObject();
   }

   public void method16() {
      this.field3 = true;
      this.field8 = this.k_() - (float)method6();
      this.field7 = this.method15() - (float)t_();
   }

   public void method3() {
   }

   public void method2() {
      if (this.field3) {
         this.method0((float)t_() + this.field7);
         this.method1((float)method6() + this.field8);
      }

   }

   public boolean method20() {
      return this.field3;
   }

   public void method0(boolean var1) {
      this.field3 = var1;
   }

   public Class283(DoubleSetting var1, DoubleSetting var2) {
      super(var1, var2);
   }

   public void method21() {
      this.field3 = false;
   }
}
