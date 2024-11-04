package oringo.module;

import net.minecraft.item.EnumDyeColor;

class GemstoneESPOldModule$1 {
   static final int[] field0 = new int[EnumDyeColor.values().length];

   static {
      try {
         field0[EnumDyeColor.RED.ordinal()] = 1;
      } catch (NoSuchFieldError var7) {
      }

      try {
         field0[EnumDyeColor.ORANGE.ordinal()] = 2;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field0[EnumDyeColor.LIME.ordinal()] = 3;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field0[EnumDyeColor.MAGENTA.ordinal()] = 4;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field0[EnumDyeColor.LIGHT_BLUE.ordinal()] = 5;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field0[EnumDyeColor.PURPLE.ordinal()] = 6;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field0[EnumDyeColor.YELLOW.ordinal()] = 7;
      } catch (NoSuchFieldError var1) {
      }

   }
}
