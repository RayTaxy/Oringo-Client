package oringo.command;

import java.awt.Color;
import java.util.Arrays;
import joptsimple.internal.Strings;
import map.Class362;
import oringo.module.ClickGuiModule;
import oringo.module.CustomESPModule;
import oringo.module.PopupAnimationModule;
import oringo.module.ScaffoldModule;

public class ESPCommand extends Command {
   public void method2() {
      CustomESPModule.field1.forEach(ESPCommand::lambda$sendNames$0);
      PopupAnimationModule.method2(String.format("%s%s add/remove/clear name hex color", ClickGuiModule.field25.method1(), this.method5()[0]));
      PopupAnimationModule.method2("Add '*' before the mob name to match mobs without nametags");
   }

   public String method1() {
      return "Adds or removes names to Custom ESP module";
   }

   public static void lambda$sendNames$0(String var0, Color var1) {
      PopupAnimationModule.method2(var0 + " > " + String.format("#%02X%02X%02X", var1.getRed(), var1.getGreen(), var1.getBlue()));
   }

   public ESPCommand() {
      super("esp", "customesp");
   }

   public void method0(String[] var1) {
      if (var1.length == 0) {
         this.method2();
      } else {
         String var2 = var1[0];
         byte var3 = -1;
         switch(var2.hashCode()) {
         case -934610812:
            if (var2.equals("remove")) {
               var3 = 1;
            }
            break;
         case 96417:
            if (var2.equals("add")) {
               var3 = 0;
            }
            break;
         case 94746189:
            if (var2.equals("clear")) {
               var3 = 2;
            }
         }

         String var4;
         switch(var3) {
         case 0:
            if (var1.length >= 3) {
               var4 = Strings.join((String[])Arrays.copyOfRange(var1, 1, var1.length - 1), " ");
               String var5 = var1[var1.length - 1];
               if (!var5.startsWith("#")) {
                  var5 = "#" + var5;
               }

               Color var6 = var5.startsWith("#&") && var5.length() == 3 ? ScaffoldModule.j_(var5.substring(2)) : Color.decode(var5);
               if (CustomESPModule.field1.put(var4, var6) != null) {
                  method2("Successfully changed color!");
               } else {
                  method2("Added " + var4 + " to Custom ESP!");
               }
            } else {
               PopupAnimationModule.method2(String.format("Usage: %s%s add name hex color", ClickGuiModule.field25.method1(), this.method5()[0]));
            }
            break;
         case 1:
            if (var1.length >= 2) {
               var4 = Strings.join((String[])Arrays.copyOfRange(var1, 1, var1.length), " ");
               if (CustomESPModule.field1.remove(var4) == null) {
                  method2("Unknown name!");
               } else {
                  method2("Successfully removed " + var4 + "!");
               }
            }
            break;
         case 2:
            CustomESPModule.field1.clear();
            method2("Cleared Custom ESP!");
            break;
         default:
            this.method2();
         }

         Class362.field41.method6();
      }
   }
}
