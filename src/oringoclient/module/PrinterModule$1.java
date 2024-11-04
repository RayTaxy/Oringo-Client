package oringo.module;

import net.minecraft.util.EnumFacing;

class PrinterModule$1 {
   static final int[] field0 = new int[EnumFacing.values().length];

   static {
      try {
         field0[EnumFacing.UP.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field0[EnumFacing.DOWN.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field0[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field0[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field0[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field0[EnumFacing.EAST.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }

   }
}
