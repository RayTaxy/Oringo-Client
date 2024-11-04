package oringo.command;

import java.awt.Color;
import java.util.Iterator;
import java.util.UUID;
import map.Class514;
import map.Class528;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.module.ShortbowTriggerbotModule;
import oringo.notification.Notifications;

public class StalkCommand extends Command {
   public static UUID field0;

   public void method0(String[] var1) {
      field0 = null;
      if (var1.length == 1) {
         Iterator var2 = Minecraft.getMinecraft().theWorld.playerEntities.iterator();

         EntityPlayer var3;
         do {
            if (!var2.hasNext()) {
               Class514.method0("Player not found!", 1000, Notifications.Class1.field0);
               return;
            }

            var3 = (EntityPlayer)var2.next();
         } while(!var3.getName().equalsIgnoreCase(var1[0]));

         field0 = new UUID((long)var3.getName().hashCode(), 0L);
         ShortbowTriggerbotModule.method0("Oringo Client", "Enabled stalking!", 1000);
      } else {
         ShortbowTriggerbotModule.method0("Oringo Client", "Disabled Stalking!", 1000);
      }
   }

   public String method1() {
      return "Shows you a player";
   }

   public StalkCommand() {
      super("stalk", "hunt");
   }

   @SubscribeEvent
   public void method0(RenderWorldLastEvent var1) {
      if (field0 != null) {
         Iterator var2 = field9.theWorld.playerEntities.iterator();

         while(var2.hasNext()) {
            EntityPlayer var3 = (EntityPlayer)var2.next();
            if ((long)var3.getName().hashCode() == field0.getMostSignificantBits() && !var3.isDead) {
               Class528.method0(var3, var1.partialTicks, 1.0F, Color.cyan);
            }
         }
      }

   }
}
