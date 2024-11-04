package oringo.command;

import com.mojang.authlib.GameProfile;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.UUID;
import map.Class271;
import map.Class34;
import map.Class92;
import oringo.Oringo;
import oringo.module.AutoRabbitModule;
import oringo.module.PopupAnimationModule;

public class IRCNameCommand extends Command {
   public void method0(String[] var1) {
      if (var1.length == 1) {
         String var2 = String.join(" ", var1);
         if (!this.method1(var2)) {
            PopupAnimationModule.method2("Invalid IRC name! Only alphanumeric with underscore and space allowed!");
            return;
         }

         Oringo.field4.method0(new Class92(var2));
      } else {
         PopupAnimationModule.method2("Invalid usage! Use: " + AutoRabbitModule.method1() + "ircname <name>");
      }

   }

   public String method1() {
      return "sets your irc name";
   }

   public boolean a_() {
      return true;
   }

   public static GameProfile y_(String var0) {
      try {
         Socket var1 = Class271.method1();
         DataOutputStream var2 = new DataOutputStream(var1.getOutputStream());
         var2.writeByte(1);
         var2.writeUTF(var0);
         var2.flush();
         DataInputStream var3 = new DataInputStream(var1.getInputStream());
         String var4 = var3.readUTF();
         String var5 = var3.readUTF();
         return var4.isEmpty() ? null : new GameProfile(UUID.fromString(var5), var4);
      } catch (Exception var6) {
         var6.printStackTrace();
         return null;
      }
   }

   public IRCNameCommand() {
      super("ircname");
   }

   public boolean method1(String var1) {
      char[] var2 = var1.toCharArray();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         char var5 = var2[var4];
         if (!Character.isLetterOrDigit(var5) && var5 != '_' && var5 != ' ') {
            return false;
         }
      }

      return var1.length() <= 16 && var1.length() >= 3;
   }

   public static double method0(Class34 var0, Class34 var1) {
      return Math.hypot((double)Class92.method0(var0.method5(), var1.method5()), (double)Class92.method0(var0.method2(), var1.method2()));
   }
}
