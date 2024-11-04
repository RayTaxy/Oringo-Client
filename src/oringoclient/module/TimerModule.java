package oringo.module;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import oringo.setting.BooleanSetting;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class TimerModule extends Module {
   public static final DoubleSetting field0 = new DoubleSetting("Timer", 1.0D, 0.1D, 10.0D, 0.1D);
   public static final BooleanSetting cH_ = new BooleanSetting("Only on ground", false);

   public TimerModule() {
      super("Timer", Category.other, SubCategory.other, "Modifies the game speed");
      this.method0(new Setting[]{field0, cH_});
   }

   public void b_() {
      if (FrozenTreasureESPModule.method5() != null) {
         FairySoulESPModule.method6();
      }

   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (var1.phase == Phase.START) {
         if (cH_.method1() && (field58.thePlayer == null || !field58.thePlayer.onGround)) {
            EnigmaSoulESPModule.method0(1.0F);
         } else {
            EnigmaSoulESPModule.method0((float)field0.method0());
         }

      }
   }
}
