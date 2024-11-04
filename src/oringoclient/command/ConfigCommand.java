package oringo.command;

import java.io.File;
import map.Class376;
import map.Class409;
import map.Class514;
import oringo.module.SecretHitboxesModule;
import oringo.module.ShinyBlocksModule;
import oringo.module.ShortbowTriggerbotModule;
import oringo.notification.Notifications;
import oringo.setting.KeyBindSetting;

public class ConfigCommand extends Command {
   public void method0(String[] var1) throws Exception {
      if (var1.length > 0) {
         String var2 = String.join(" ", var1).replaceFirst(var1[0] + " ", "");
         File var3 = new File(Class376.field2 + var2 + ".json");
         String var4 = var1[0].toLowerCase();
         byte var5 = -1;
         switch(var4.hashCode()) {
         case 3327206:
            if (var4.equals("load")) {
               var5 = 1;
            }
            break;
         case 3522941:
            if (var4.equals("save")) {
               var5 = 0;
            }
         }

         switch(var5) {
         case 0:
            if (KeyBindSetting.method0(var3)) {
               SecretHitboxesModule.method0("Successfully saved config " + var2, 3000);
            } else {
               Class514.method0("Saving " + var2 + " failed", 3000, Notifications.Class1.field0);
            }
            break;
         case 1:
            if (Class409.method0(var3)) {
               ShortbowTriggerbotModule.method0("Oringo Client", "Config " + var2 + " loaded", 3000);
            } else {
               Class514.method0("Loading config " + var2 + " failed", 3000, Notifications.Class1.field0);
            }
         }
      } else {
         try {
            ShortbowTriggerbotModule.method0("Oringo Client", ".config load/save name", 10000);
            ShinyBlocksModule.method0(new File(Class376.field2));
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }

   }

   public String method1() {
      return "Save or load a config. .config to open explorer";
   }

   public ConfigCommand() {
      super("config");
   }
}
