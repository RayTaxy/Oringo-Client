package map;

import oringo.Oringo;
import oringo.command.IRCCommand;
import oringo.module.PopupAnimationModule;

public class Class390 {
   public static void method0(String var0) {
      if (!Class362.field55.method44()) {
         PopupAnimationModule.method2("IRC is disabled!");
      } else {
         if (IRCCommand.field0.method0(300L, true)) {
            Oringo.field4.method0(new Class29(var0));
         }

      }
   }
}
