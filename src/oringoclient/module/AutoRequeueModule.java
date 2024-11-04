package oringo.module;

import java.util.Iterator;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoRequeueModule extends Module {
   public static void method0(float var0, float var1, float var2, float var3, float var4, float var5, int var6, int var7) {
      ReachModule.method0((double)var0, (double)var1, (double)(var0 + var2), (double)(var1 + var3), (double)var4, var6);
      AutoSimonModule.method0(var0, var1, var2, var3, var4, var5, var7);
   }

   public AutoRequeueModule() {
      super("Auto Requeue", Category.skyblock, SubCategory.main, "Automatically requeues into dungeons");
   }

   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      Iterator var2 = var1.message.getSiblings().iterator();

      while(var2.hasNext()) {
         IChatComponent var3 = (IChatComponent)var2.next();
         ChatStyle var4 = var3.getChatStyle();
         if (var4 != null) {
            ClickEvent var5 = var4.getChatClickEvent();
            if (var5 != null && var5.getAction() == Action.RUN_COMMAND) {
               if (var5.getValue().equals("/instancerequeue")) {
                  field58.thePlayer.sendChatMessage("/instancerequeue");
               }
               break;
            }
         }
      }

   }
}
