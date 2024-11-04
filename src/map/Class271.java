package map;

import com.google.gson.annotations.SerializedName;
import java.net.InetAddress;
import java.net.Socket;
import net.minecraft.util.MathHelper;

public class Class271 implements Class23 {
   @SerializedName("name")
   public String cW_;

   public void method0(Class173 var1) {
   }

   public Class271(String var1) {
      this.cW_ = var1;
   }

   public String method0() {
      return this.cW_;
   }

   public static Class34 method0(double var0, double var2, double var4) {
      double var6 = (double)MathHelper.sqrt_double(var0 * var0 + var4 * var4);
      float var8 = (float)(Math.atan2(var4, var0) * 180.0D / 3.141592653589793D) - 90.0F;
      float var9 = (float)(-(Math.atan2(var2, var6) * 180.0D / 3.141592653589793D));
      return new Class34(var8, var9);
   }

   public static Socket method1() {
      try {
         return new Socket(InetAddress.getByName("localhost"), 21370);
      } catch (Exception var1) {
         var1.printStackTrace();
         return null;
      }
   }
}
