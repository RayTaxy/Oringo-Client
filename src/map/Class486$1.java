package map;

import net.minecraft.util.MovingObjectPosition.MovingObjectType;

class Class486$1 {
   static final int[] field0 = new int[MovingObjectType.values().length];

   static {
      try {
         field0[MovingObjectType.ENTITY.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field0[MovingObjectType.BLOCK.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }

   }
}
