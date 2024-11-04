package oringo.module;

import map.Class256;
import map.Class500;
import map.Class514;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.event.Class332;
import oringo.mixin.C03PacketPlayerAccessor;
import oringo.notification.Notifications;
import oringo.setting.EnumSetting;
import oringo.setting.Setting;

public class NoFallModule extends Module {
   public final EnumSetting field0 = new EnumSetting("Mode", "Hypixel", new String[]{"Edit", "Hypixel", "Packet", "No ground"});
   public boolean bf_;
   public double field2;

   public static void method3() {
      Oringo.field4.method0(new Class256());
   }

   public static String method0(int var0, int var1, int var2) {
      return String.format("§%s%s§r", var0 < var1 ? (var0 < var2 ? "c" : "e") : "a", var0);
   }

   public void method4() {
      Class514.method0("This module is highly banable!", 10000, Notifications.Class1.field2);
   }

   public NoFallModule() {
      super("No Fall", Category.player, SubCategory.player, "Prevents you from taking fall damage");
      this.method0((Setting[])(new Setting[]{this.field0}));
      this.method0((Class500)(this::lambda$new$0));
   }

   public String lambda$new$0() {
      return this.field0.method4();
   }

   @SubscribeEvent(
      priority = EventPriority.LOW
   )
   public void method0(Class210.Class1 var1) {
      if (this.field2 > (double)field58.thePlayer.fallDistance) {
         this.field2 = 0.0D;
      }

      if (this.bf_) {
         this.bf_ = false;
         EnigmaSoulESPModule.method0(1.0F);
      }

      String var2 = this.field0.method4();
      byte var3 = -1;
      switch(var2.hashCode()) {
      case -1911998296:
         if (var2.equals("Packet")) {
            var3 = 2;
         }
         break;
      case -1248403467:
         if (var2.equals("Hypixel")) {
            var3 = 1;
         }
         break;
      case 2155050:
         if (var2.equals("Edit")) {
            var3 = 0;
         }
      }

      switch(var3) {
      case 0:
         if (field58.thePlayer.fallDistance > 2.0F) {
            var1.method1(true);
         }
      case 1:
      default:
         break;
      case 2:
         if ((double)field58.thePlayer.fallDistance - this.field2 > 2.0D) {
            method3(new C03PacketPlayer(true));
            this.field2 = (double)field58.thePlayer.fallDistance;
         }
      }

   }

   @SubscribeEvent
   public void method0(Class332 var1) {
      if (var1.field0 instanceof C03PacketPlayer && this.field0.method4().equals("No ground")) {
         ((C03PacketPlayerAccessor)var1.field0).setOnGround(false);
      }

   }
}
