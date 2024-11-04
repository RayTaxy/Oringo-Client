package map;

import net.minecraft.util.MovingObjectPosition.MovingObjectType;

class Class349$1 {
   static final int[] field0 = new int[MovingObjectType.values().length];

   static {
      try {
         field0[MovingObjectType.ENTITY.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field0[MovingObjectType.BLOCK.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field0[MovingObjectType.MISS.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }

   }
}
