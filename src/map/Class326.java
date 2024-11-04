package map;

import net.minecraft.entity.Entity;

public class Class326 {
   public static void method0(Entity var0, String var1, double var2, double var4, double var6, int var8) {
      if (var0.getDistanceSqToEntity(Class322.field3.getRenderManager().livingPlayer) <= (double)(var8 * var8)) {
         Class322.field2.add(new Class322.Class0(var1, var2, var4 + (double)var0.height, var6));
      }
   }
}
