package map;

import com.google.gson.annotations.SerializedName;
import oringo.Oringo;

public class Class474 implements Class23 {
   @SerializedName("message")
   public String field0;

   public void method0(Class173 var1) {
   }

   public String method0() {
      return this.field0;
   }

   public Class474(String var1) {
      this.field0 = var1;
   }

   public static boolean method0(float var0, float var1) {
      float var2 = Math.abs(var0 - Oringo.field9.thePlayer.rotationYaw) % 360.0F;
      if (var2 > 180.0F) {
         var2 = 360.0F - var2;
      }

      return var2 * 2.0F <= var1;
   }
}
