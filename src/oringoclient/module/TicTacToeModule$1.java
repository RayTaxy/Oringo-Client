package oringo.module;

import net.minecraft.util.EnumFacing;

class TicTacToeModule$1 {
   static final int[] field0 = new int[EnumFacing.values().length];

   static {
      try {
         field0[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field0[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field0[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field0[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }

   }
}
