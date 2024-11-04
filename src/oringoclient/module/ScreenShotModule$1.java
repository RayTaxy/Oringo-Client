package oringo.module;

import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

class ScreenShotModule$1 {
   static final int[] field0 = new int[ElementType.values().length];

   static {
      try {
         field0[ElementType.CHAT.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field0[ElementType.PLAYER_LIST.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field0[ElementType.DEBUG.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }

   }
}
