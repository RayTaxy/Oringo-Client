package oringo.event;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class Class344 extends Event {
   public Entity aM_;

   public static int method0(byte[] var0, int var1) {
      int var2 = var0.length;
      int var3 = 0;
      int var4 = var0[var1] & 255;

      while(var4 > 0) {
         try {
            int var5 = var1 + var4 + 1;
            int var6 = var0[var5] & 255;
            var3 += var4;
            var1 = var5;
            var4 = var6;
         } catch (Exception var7) {
            var4 = var2 - var1 - 1;
            var3 += var4;
            break;
         }
      }

      return var3;
   }

   public Class344(Entity var1) {
      this.aM_ = var1;
   }
}
