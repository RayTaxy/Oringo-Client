package oringo.module;

import map.Class407;
import map.Class413;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class270;

public class OptimizationsModule extends Module {
   public static OptimizationsModule field0 = new OptimizationsModule();

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      Class413.method0();
   }

   @SubscribeEvent
   public void method0(Class270 var1) {
      Class407.method5();
   }

   public void e_() {
      method2("You might have to change your current world to see full changes!");
   }

   public OptimizationsModule() {
      super("Optimizations", Category.render, SubCategory.other, "Optimizations");
   }
}
