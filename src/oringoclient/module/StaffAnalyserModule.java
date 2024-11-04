package oringo.module;

import map.Class447;
import map.Class514;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import oringo.notification.Notifications;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class StaffAnalyserModule extends Module {
   public int field0 = -1;
   public final DoubleSetting aI_ = new DoubleSetting("Delay", 5.0D, 5.0D, 60.0D, 1.0D);
   public final Class447 field2 = new Class447();

   public StaffAnalyserModule() {
      super("Staff Analyser", Category.other, SubCategory.other, "Shows you the amount of staff bans from the official hypixel api (fake af)");
      this.method0(new Setting[]{this.aI_});
   }

   public void lambda$onTick$0() {
      int var1 = ChestStealerModule.method5();
      if (var1 != this.field0 && this.field0 != -1 && var1 > this.field0) {
         Class514.method0(String.format("Staff has banned %s %s in the last %s seconds", var1 - this.field0, var1 - this.field0 > 1 ? "people" : "person", (int)this.aI_.method0()), 5000, var1 - this.field0 > 2 ? Notifications.Class1.field2 : Notifications.Class1.field1);
      }

      this.field0 = var1;
   }

   @SubscribeEvent
   public void method0(ClientTickEvent var1) {
      if (this.field2.a_((long)(this.aI_.method0() * 1000.0D))) {
         this.field2.o_();
         (new Thread(this::lambda$onTick$0)).start();
      }

   }
}
