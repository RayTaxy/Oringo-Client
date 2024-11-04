package map;

import com.google.gson.annotations.SerializedName;
import oringo.module.RenderBarriersModule;

public class Class328 implements Class23 {
   @SerializedName("volume")
   public float field0 = 1.0F;
   @SerializedName("sound_code")
   public String field1;
   @SerializedName("pitch")
   public float bF_ = 1.0F;

   public float x_() {
      return this.bF_;
   }

   public void method0(Class173 var1) {
      var1.method0(this);
   }

   public String method1() {
      return this.field1;
   }

   public static void method0(int var0) {
      RenderBarriersModule.method0(var0);
   }

   public float method2() {
      return this.field0;
   }
}
