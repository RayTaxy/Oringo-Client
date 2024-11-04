package oringo.module;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class332;
import oringo.event.Class75;

public class TestModule extends Module {
   public boolean field0;

   public TestModule() {
      super("Test", Category.other, SubCategory.other);
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      method0((String)(field58.thePlayer.isInWater() + ""));
   }

   @SubscribeEvent
   public void method0(Class332 var1) {
   }
}
