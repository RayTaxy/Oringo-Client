package oringo.module;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import map.Class374;
import map.Class413;
import map.Class447;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class75;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class CookieClickerModule extends Module {
   public final Predicate field0 = Pattern.compile("^Cookie Clicker v\\.\\d+$").asPredicate();
   public final DoubleSetting bQ_ = new DoubleSetting("CPS", 10.0D, 1.0D, 100.0D, 1.0D);
   public final Class447 field2 = new Class447();

   @SubscribeEvent
   public void method0(Class75 var1) {
      String var2 = Class374.method0();
      if (var2 != null) {
         if (this.field0.test(var2) && this.field2.method0(1000.0D / this.bQ_.method0())) {
            for(int var3 = 0; (double)var3 < (double)this.field2.method0() / (1000.0D / this.bQ_.method0()); ++var3) {
               field58.playerController.windowClick(field58.thePlayer.openContainer.windowId, 13, 0, 0, field58.thePlayer);
            }

            this.field2.o_();
         }

      }
   }

   public CookieClickerModule() {
      super("Cookie Clicker", 0, Category.player, SubCategory.qol, (String)null);
      this.method0((Setting[])(new Setting[]{this.bQ_}));
   }

   public static void method0(Packet var0) {
      Oringo.field9.addScheduledTask(Class413::lambda$handlePacketNoEvent$0);
   }
}
