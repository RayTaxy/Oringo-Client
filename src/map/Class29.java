package map;

import com.google.gson.annotations.SerializedName;
import net.minecraft.util.MathHelper;

public class Class29 implements Class23 {
   @SerializedName("message")
   public String field0;

   public String method0() {
      return this.field0;
   }

   public static Class34 method0(Class34 var0, Class34 var1, float var2) {
      return new Class34(var0.method5() + MathHelper.clamp_float(Class92.method0(var1.method5(), var0.method5()), -var2, var2), var0.method2() + MathHelper.clamp_float(Class92.method0(var1.method2(), var0.method2()), -var2, var2));
   }

   public Class29(String var1) {
      this.field0 = var1;
   }

   public void method0(Class173 var1) {
   }
}
