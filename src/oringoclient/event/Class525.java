package oringo.event;

import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class Class525 extends Event {
   public boolean field0;

   public static int method0(byte[] var0, int[] var1, int var2) {
      int var3 = var1.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         boolean var5 = true;
         int var6 = var0[var2++] & 255;
         int var7 = var0[var2++] & 255;
         int var8 = var0[var2++] & 255;
         var1[var4] = (('\uff00' | var6) << 8 | var7) << 8 | var8;
      }

      return var2;
   }
}
