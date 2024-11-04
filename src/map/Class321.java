package map;

import com.google.gson.annotations.SerializedName;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import oringo.Oringo;

public class Class321 implements Class23 {
   @SerializedName("data")
   public String field0;

   public void method0(Class173 var1) {
   }

   public static Class34 method0(Entity var0) {
      double var1 = (var0.posX - var0.lastTickPosX) * 0.4D;
      double var3 = (var0.posZ - var0.lastTickPosZ) * 0.4D;
      double var5 = (double)Oringo.field9.thePlayer.getDistanceToEntity(var0);
      var5 -= var5 % 0.8D;
      double var7 = var5 / 0.8D * var1;
      double var9 = var5 / 0.8D * var3;
      double var11 = var0.posX + var7 - Oringo.field9.thePlayer.posX;
      double var13 = var0.posZ + var9 - Oringo.field9.thePlayer.posZ;
      double var15 = Oringo.field9.thePlayer.posY + (double)Oringo.field9.thePlayer.getEyeHeight() - (var0.posY + (double)var0.getEyeHeight());
      double var17 = (double)Oringo.field9.thePlayer.getDistanceToEntity(var0);
      float var19 = (float)Math.toDegrees(Math.atan2(var13, var11)) - 90.0F;
      double var20 = (double)MathHelper.sqrt_double(var11 * var11 + var13 * var13);
      float var22 = (float)(-(Math.atan2(var15, var20) * 180.0D / 3.141592653589793D)) + (float)var17 * 0.11F;
      return new Class34(var19, Class163.method0(-var22, 90.0F, -90.0F));
   }
}
