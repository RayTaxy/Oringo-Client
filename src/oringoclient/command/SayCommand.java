package oringo.command;

import joptsimple.internal.Strings;
import map.Class253;
import map.Class428;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class SayCommand extends Command {
   public boolean a_() {
      return true;
   }

   public void method0(String[] var1) {
      if (var1[0].equals("_")) {
         field9.thePlayer.addChatMessage(new Class253.Class0(Strings.join(var1, " ").replace('&', '§').replace("_ ", ""), Class428.method17().getRGB()));
      } else {
         field9.getNetHandler().addToSendQueue(new C01PacketChatMessage(Strings.join(var1, " ")));
      }

   }

   public SayCommand() {
      super("say");
   }

   public String method1() {
      return null;
   }
}
