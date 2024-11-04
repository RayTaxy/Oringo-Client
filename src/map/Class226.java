package map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import net.minecraft.util.BlockPos;

public class Class226 {
   public static final BlockPos[] field0;

   static {
      ArrayList var0 = new ArrayList();

      for(int var1 = -6; var1 <= 6; ++var1) {
         for(int var2 = -6; var2 <= 6; ++var2) {
            for(int var3 = -6; var3 <= 6; ++var3) {
               int var4 = var1 * var1 + var2 * var2 + var3 * var3;
               if (var4 <= 49) {
                  var0.add(new BlockPos(var1, var2, var3));
               }
            }
         }
      }

      var0.sort(Comparator.comparingDouble(Class226::lambda$static$0));
      field0 = (BlockPos[])var0.toArray(new BlockPos[0]);
   }

   public static double lambda$static$0(BlockPos var0) {
      return var0.distanceSq(BlockPos.ORIGIN);
   }

   public static BlockPos[] access$000() {
      return field0;
   }

   public static Iterator lambda$getNearbyBlocks$1(BlockPos var0) {
      return new Class226$1(var0);
   }
}
