package oringo.command;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import map.Class271;
import map.Class34;
import map.Class352;
import map.Class398;
import map.Class474;
import net.minecraft.util.Vec3;
import oringo.Oringo;

public class ReplyCommand extends Command {
   public static Class398 method0(String var0, String var1, byte[] var2) {
      try {
         Socket var3 = Class271.method1();
         DataOutputStream var4 = new DataOutputStream(var3.getOutputStream());
         var4.writeByte(2);
         var4.writeUTF(var0);
         var4.writeUTF(var1);
         ToggleIRCCommand.method0(var4, var2);
         var4.flush();
         DataInputStream var5 = new DataInputStream(var3.getInputStream());
         byte[] var6 = Class352.method0(var5);
         return var6.length == 0 ? null : new Class398(var6, var3);
      } catch (Exception var7) {
         var7.printStackTrace();
         return null;
      }
   }

   public static Class34 method0(Vec3 var0) {
      return Class352.method0(var0.xCoord, var0.yCoord, var0.zCoord);
   }

   public ReplyCommand() {
      super("r", "reply", "ircreply");
   }

   public void method0(String[] var1) throws Exception {
      if (var1.length != 0) {
         Oringo.field4.method0(new Class474(String.join(" ", var1)));
      }
   }

   public boolean a_() {
      return true;
   }

   public String method1() {
      return "Reply to the last direct message in irc";
   }
}
