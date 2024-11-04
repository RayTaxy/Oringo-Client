package oringo.module;

import net.minecraft.util.EnumFacing;

class AutoWaterModule$2 {
   static final int[] field0 = new int[EnumFacing.values().length];

   static {
      try {
         field0[EnumFacing.SOUTH.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field0[EnumFacing.WEST.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field0[EnumFacing.EAST.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }

   }
}
