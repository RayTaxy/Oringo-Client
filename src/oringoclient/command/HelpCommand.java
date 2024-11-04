package oringo.command;

import java.util.HashMap;
import oringo.module.AutoRabbitModule;
import oringo.module.NoRenderModule2;
import oringo.module.PopupAnimationModule;

public class HelpCommand extends Command {
   public static final HashMap field0 = new HashMap();

   public void method0(String[] var1) {
      if (var1.length == 0) {
         NoRenderModule2.method0(String.format("§d%shelp command §7for more info", AutoRabbitModule.method1()));
         field0.forEach(HelpCommand::lambda$execute$0);
      } else if (field0.containsKey(var1[0])) {
         String var2 = var1[0];
         Command var3 = (Command)field0.get(var1[0]);
         NoRenderModule2.method0(String.format("§b%s%s §3» §7%s", AutoRabbitModule.method1(), var2, var3.method4()));
      } else {
         PopupAnimationModule.method2(String.format("§cInvalid command \"%shelp\" for §cmore info", AutoRabbitModule.method1()));
      }

   }

   public String method1() {
      return "Shows all commands";
   }

   public HelpCommand() {
      super("help", "commands", "info");
   }

   public static void lambda$execute$0(String var0, Command var1) {
      if (var1.method1() != null) {
         NoRenderModule2.method0(String.format("§d%s%s §3» §7%s", AutoRabbitModule.method1(), var0, var1.method1()));
      }

   }
}
