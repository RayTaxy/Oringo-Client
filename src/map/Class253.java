package map;

import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oringo.event.Class189;

public class Class253 {
   public static final Pattern field0 = Pattern.compile("(§.|)((?:[a-z0-9]{2,}://)?(?:(?:[0-9]{1,3}\\.){3}[0-9]{1,3}|[-\\w_.]+\\.[a-z]{2,}?)(?::[0-9]{1,5})?.*?(?=[!\"§ \n]|$))", 2);
   public static long field1 = 0L;

   @SubscribeEvent
   public void field0(Class189 var1) {
      if (field1 > System.currentTimeMillis() && var1.field0 instanceof S02PacketChat && ((S02PacketChat)var1.field0).isChat()) {
         Minecraft.getMinecraft().thePlayer.addChatMessage(((S02PacketChat)var1.field0).getChatComponent());
         var1.method9();
      }

   }

   public static class Class0 extends ChatComponentText {
      public int ca_;

      public Class0(IChatComponent var1, int var2) {
         super("");
         this.appendSibling(var1);
         this.ca_ = var2 % 33554432;
      }

      public Class0(String var1, int var2) {
         super(var1);
         this.ca_ = var2 % 33554432;
      }
   }
}
