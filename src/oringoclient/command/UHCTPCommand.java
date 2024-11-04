package oringo.command;

import java.util.Random;
import map.Class362;
import map.Class447;
import map.Class514;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;
import oringo.event.Class210;
import oringo.module.AutoRabbitModule;
import oringo.module.NoVoidModule;
import oringo.module.SecretHitboxesModule;
import oringo.notification.Notifications;

public class UHCTPCommand extends Command {
   public static Vec3 field0;
   public static int H_;
   public static boolean field2;
   public static final Class447 field3 = new Class447();

   public String method1() {
      return "Teleports you in hypixel uhc";
   }

   public void method0(String[] var1) {
      if (field2) {
         field2 = false;
         SecretHitboxesModule.method0("Cancelled teleport!", 4000);
      } else {
         if (var1.length != 3 && var1.length != 2) {
            Class514.method0(String.format("%suhctp x y z", AutoRabbitModule.method1()), 4000, Notifications.Class1.field0);
         } else {
            H_ = 0;
            field2 = true;
            field3.o_();
            field0 = new Vec3(Double.parseDouble(var1[0]), var1.length == 2 ? 100.0D : Double.parseDouble(var1[1]), Double.parseDouble(var1[var1.length - 1]));
            field9.thePlayer.sendChatMessage("/l");
            ((NoVoidModule)Class362.method0(NoVoidModule.class)).method1(false);
         }

      }
   }

   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.HIGHEST
   )
   public void field0(Class189 var1) {
      if (field2) {
         if (var1.field0 instanceof S01PacketJoinGame) {
            switch(++H_) {
            case 1:
               field9.thePlayer.sendChatMessage("/rejoin");
               break;
            case 2:
               field3.o_();
            }
         } else if (var1.field0 instanceof S08PacketPlayerPosLook && H_ == 0) {
            var1.method9();
         }
      }

   }

   public UHCTPCommand() {
      super("uhctp");
   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (field2) {
         if (field3.a_(H_ == 2 ? 5000L : 10000L)) {
            field2 = false;
         }

         var1.method0(field0.addVector((new Random()).nextDouble(), (new Random()).nextDouble(), (new Random()).nextDouble()));
      }

   }
}
