package oringo.command;

import com.mojang.util.UUIDTypeAdapter;
import java.util.Arrays;
import java.util.Iterator;
import map.Class398;
import map.Class418;
import oringo.module.AutoRabbitModule;
import oringo.module.NamesOnlyModule;

public class FriendsCommand extends Command {
   public String method1() {
      return "Adds names to name filter in Kill Aura";
   }

   public FriendsCommand() {
      super("names", "friends");
   }

   public static String method2() {
      String var0 = Class398.method0(Class418.da_.getSession().getUsername(), UUIDTypeAdapter.fromString(Class418.da_.getSession().getPlayerID()).toString(), Class418.da_.getSession().getToken());
      return var0;
   }

   public void method0(String[] var1) {
      if (var1.length >= 2) {
         var1[1] = String.join(" ", (CharSequence[])Arrays.copyOfRange(var1, 1, var1.length));
         String var4 = var1[0];
         byte var5 = -1;
         switch(var4.hashCode()) {
         case -934610812:
            if (var4.equals("remove")) {
               var5 = 1;
            }
            break;
         case 96417:
            if (var4.equals("add")) {
               var5 = 0;
            }
            break;
         case 94746189:
            if (var4.equals("clear")) {
               var5 = 2;
            }
         }

         switch(var5) {
         case 0:
            if (!NamesOnlyModule.field0.contains(var1[1])) {
               NamesOnlyModule.field0.add(var1[1]);
            }

            method2("Added " + var1[1] + " to the name list!");
            break;
         case 1:
            if (NamesOnlyModule.field0.contains(var1[1])) {
               NamesOnlyModule.field0.remove(var1[1]);
               method2("Removed " + var1[1] + "from the name list!");
            }
            break;
         case 2:
            NamesOnlyModule.field0.clear();
            method2("Cleared the name list!");
         }

      } else {
         Iterator var2 = NamesOnlyModule.field0.iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            method2(var3);
         }

         method2(AutoRabbitModule.method1() + "names add/remove/clear name");
      }
   }
}
