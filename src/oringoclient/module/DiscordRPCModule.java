package oringo.module;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.RichPresence;
import map.Class374;
import oringo.Oringo;

public class DiscordRPCModule extends Module {
   public static RichPresence field0;
   public static boolean field1;
   public static IPCClient field2 = null;

   public static boolean access$102(boolean var0) {
      field1 = var0;
      return var0;
   }

   public static RichPresence access$002(RichPresence var0) {
      field0 = var0;
      return var0;
   }

   public static RichPresence access$000() {
      return field0;
   }

   public DiscordRPCModule() {
      super("Discord RPC", Category.render, SubCategory.ui);
   }

   public void b_() {
      try {
         field2.sendRichPresence((RichPresence)null);
      } catch (Exception var2) {
      }

   }

   public static short method6() {
      return Oringo.field9.thePlayer.openContainer.getNextTransactionID(Oringo.field9.thePlayer.inventory);
   }

   public void method4() {
      if (field2 == null) {
         field2 = new IPCClient(929291236450377778L);
      }

      if (!field1) {
         Class374.method1();
      } else {
         try {
            field2.sendRichPresence(field0);
         } catch (Exception var2) {
         }
      }

      super.method4();
   }
}
