package map;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class Class305 implements Class23 {
   @SerializedName("data")
   public JsonObject field0;

   public void method0(Class173 var1) {
   }

   public Class305(JsonObject var1) {
      this.field0 = var1;
   }

   public static Class34 method0(Class34 var0, Class34 var1, float var2) {
      float var3 = Class92.method0(var1.method5(), var0.method5());
      float var4 = Class92.method0(var1.method2(), var0.method2());
      float var5 = (float)Math.sqrt((double)(var3 * var3 + var4 * var4));
      if (var5 <= var2) {
         return new Class34(var0.method5() + var3, var0.method2() + var4);
      } else {
         float var6 = var3 / var5;
         float var7 = var4 / var5;
         return new Class34(var0.method5() + var6 * var2, var0.method2() + var7 * var2);
      }
   }

   public static boolean method0() {
      return Minecraft.getMinecraft().objectMouseOver != null && Minecraft.getMinecraft().objectMouseOver.typeOfHit.equals(MovingObjectType.ENTITY);
   }
}
