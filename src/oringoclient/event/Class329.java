package oringo.event;

import map.Class315;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;

public class Class329 extends Event {
   public final BlockPos field0;
   public final IBlockState field1;

   public static int method0(Class315.Class3 var0, byte[] var1, int var2) {
      var0.field5 = new String(var1, var2 + 3, 8);
      var0.field6 = new String(var1, var2 + 11, 3);
      var2 += 14;
      int var3 = var1[var2] & 255;
      if (var3 == 3) {
         var0.field3 = var1[var2 + 2] & 255 | var1[var2 + 3] & '\uff00';
         return var2 + 5;
      } else {
         while((var1[var2] & 255) != 0) {
            var2 += (var1[var2] & 255) + 1;
         }

         return var2 + 1;
      }
   }

   public BlockPos method0() {
      return this.field0;
   }

   public IBlockState method1() {
      return this.field1;
   }

   public Class329(BlockPos var1, IBlockState var2) {
      this.field0 = var1;
      this.field1 = var2;
   }
}
