package map;

import com.google.gson.annotations.SerializedName;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.BlockPos;

public class Class417 implements Class23 {
   @SerializedName("plus_data")
   public Class262 field0;

   public static boolean method0(Class34 var0, float var1) {
      return Class474.method0(var0.method5(), var1);
   }

   public Class262 method0() {
      return this.field0;
   }

   public void method0(Class173 var1) {
   }

   public static boolean method0(double var0, double var2, double var4) {
      WorldClient var6 = Minecraft.getMinecraft().theWorld;
      BlockPos var7 = new BlockPos(var0, var2, var4);

      for(int var8 = 0; var8 < 3; ++var8) {
         var7 = var7.down();
         if (!(var6.getBlockState(var7).getBlock() instanceof BlockAir)) {
            return false;
         }
      }

      return true;
   }

   public Class417(Class262 var1) {
      this.field0 = var1;
   }
}
