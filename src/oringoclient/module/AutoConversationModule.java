package oringo.module;

import java.util.Iterator;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.Oringo;

public class AutoConversationModule extends Module {
   @SubscribeEvent
   public void method0(ClientChatReceivedEvent var1) {
      if (var1.message.getUnformattedText().startsWith("§eSelect an option:")) {
         String var2 = null;
         Iterator var3 = var1.message.getSiblings().iterator();

         while(var3.hasNext()) {
            IChatComponent var4 = (IChatComponent)var3.next();
            if (var2 != null) {
               var2 = "";
            } else {
               ChatStyle var5 = var4.getChatStyle();
               if (var5 != null) {
                  ClickEvent var6 = var5.getChatClickEvent();
                  if (var6 != null) {
                     if (!var6.getValue().startsWith("/selectnpcoption")) {
                        return;
                     }

                     var2 = var6.getValue();
                  }
               }
            }
         }

         if (var2 != null && !var2.equals("")) {
            field58.thePlayer.sendChatMessage(var2);
         }

      }
   }

   public static BlockPos method5() {
      return new BlockPos(Oringo.field9.thePlayer.getPositionEyes(1.0F));
   }

   public AutoConversationModule() {
      super("Auto Conversation", Category.skyblock, SubCategory.qol, "Automatically answers in NPC conversations");
   }
}
