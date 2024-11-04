package map;

import com.google.gson.annotations.SerializedName;
import java.io.DataInputStream;
import java.io.IOException;
import net.minecraft.util.MathHelper;
import oringo.Oringo;

public class Class352 implements Class23 {
   @SerializedName("file")
   public String field0;
   @SerializedName("text")
   public String cy_;
   @SerializedName("name")
   public String cW_;

   public String D_() {
      return this.cy_;
   }

   public String method1() {
      return this.field0;
   }

   public String method0() {
      return this.cW_;
   }

   public void method0(Class173 var1) {
   }

   public static Class34 method0(double var0, double var2, double var4) {
      double var6 = var0 - Oringo.field9.thePlayer.posX;
      double var8 = var2 - (Oringo.field9.thePlayer.posY + (double)Oringo.field9.thePlayer.getEyeHeight());
      double var10 = var4 - Oringo.field9.thePlayer.posZ;
      double var12 = (double)MathHelper.sqrt_double(var6 * var6 + var10 * var10);
      float var14 = (float)(Math.atan2(var10, var6) * 180.0D / 3.141592653589793D) - 90.0F;
      float var15 = (float)(-(Math.atan2(var8, var12) * 180.0D / 3.141592653589793D));
      return new Class34(var14, var15);
   }

   public Class352(String var1, String var2, String var3) {
      this.cW_ = var1;
      this.cy_ = var2;
      this.field0 = var3;
   }

   public static byte[] method0(DataInputStream var0) {
      try {
         int var1 = var0.readUnsignedByte();
         int var2 = 0;

         for(int var3 = 0; var3 < var1; ++var3) {
            var2 <<= 8;
            var2 += var0.readUnsignedByte();
         }

         byte[] var5 = new byte[var2];
         var0.read(var5);
         return var5;
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }
   }
}
