package oringo.module;

import map.Class163;
import map.Class34;
import map.Class469;
import map.Class514;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import oringo.notification.Notifications;

public class SecretHitboxesModule extends Module {
   public static Class34[] method0(int var0, double var1, Class34 var3, Class34 var4) {
      if (var0 == 0) {
         return new Class34[0];
      } else {
         float var5 = var3.method2();
         float var6 = MathHelper.wrapAngleTo180_float(var3.method5());
         float var7 = var4.method2() - var5;
         float var8 = Class514.method0(var6, var4.method5());
         var7 = (float)((double)var7 * (var1 / (double)var0));
         var8 = (float)((double)var8 * (var1 / (double)var0));
         Class34[] var9 = new Class34[var0];
         Class34 var10 = var3;

         for(int var11 = 0; var11 < var0; ++var11) {
            float var12 = ShortbowTriggerbotModule.method0(var11, var0 - 1);
            Class34 var13 = new Class34(var10.method5() + var8 * var12, Class163.method0(var10.method2() + var7 * var12, 90.0F, -90.0F));
            var9[var11] = var13;
            var10 = var13;
         }

         if (var1 == 1.0D) {
            var9[var0 - 1] = var4;
         }

         return var9;
      }
   }

   public static void method0(String var0, int var1) {
      Class514.method0(var0, var1, Notifications.Class1.field1);
   }

   public static boolean method0(String... var0) {
      return ShortbowTriggerbotModule.method0(new BlockPos(Class469.field0.thePlayer), var0);
   }

   public SecretHitboxesModule() {
      super("Secret Hitboxes", Category.dungeon, SubCategory.main, "Increases selection boxes of secrets");
   }
}
