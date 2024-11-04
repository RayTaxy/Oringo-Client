package oringo.event;

import map.Class315;
import net.minecraft.entity.Entity;

public class Class438 extends Event {
   public final Entity field0;

   public static int method0(Class315.Class0 var0, byte[] var1, int var2) {
      int var3 = var1.length;
      int var4 = var1[var2++] & 255;
      int var5 = 1 << var4;
      Class315.Class0.access$102(var0, var4 + 1);
      Class315.Class0.access$302(var0, var5);
      Class315.Class0.access$202(var0, var5 + 1);
      int var6 = Class344.method0(var1, var2);
      byte[] var7 = new byte[var6 + 2];
      int var8 = 0;
      int var9 = var1[var2] & 255;

      while(var9 > 0) {
         try {
            int var10 = var2 + var9 + 1;
            int var11 = var1[var10] & 255;
            System.arraycopy(var1, var2 + 1, var7, var8, var9);
            var8 += var9;
            var2 = var10;
            var9 = var11;
         } catch (Exception var12) {
            var9 = var3 - var2 - 1;
            System.arraycopy(var1, var2 + 1, var7, var8, var9);
            int var10000 = var8 + var9;
            var2 += var9 + 1;
            break;
         }
      }

      Class315.Class0.access$702(var0, var7);
      ++var2;
      return var2;
   }

   public Class438(Entity var1) {
      this.field0 = var1;
   }
}
