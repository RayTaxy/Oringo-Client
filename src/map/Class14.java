package map;

import com.google.gson.annotations.SerializedName;

public class Class14 implements Class23 {
   @SerializedName("arguments")
   public String[] field0;

   public void method0(Class173 var1) {
   }

   public String[] getArgs() {
      return this.field0;
   }

   public static Class34 method0(Class34 var0, Class34 var1, float var2) {
      return new Class34(var0.method5() + Class92.method0(var1.method5(), var0.method5()) / var2, var0.method2() + Class92.method0(var1.method2(), var0.method2()) / var2);
   }

   public Class14(String[] var1) {
      this.field0 = var1;
   }
}
