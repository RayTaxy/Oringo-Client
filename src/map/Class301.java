package map;

import net.minecraft.util.Session;

public class Class301 extends Class343 {
   public static String method0(Session var0) {
      return var0.getPlayerID();
   }

   public float method0() {
      float var1 = this.method2();
      return var1 != 0.0F && var1 != 1.0F ? (float)Math.sin((double)var1 * 3.141592653589793D / 2.0D) : var1;
   }
}
