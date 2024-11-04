package oringo.command;

import java.lang.reflect.Field;
import map.Class34;
import map.Class390;
import map.Class447;
import map.Class465;
import net.minecraft.util.AxisAlignedBB;
import oringo.Oringo;

public class IRCCommand extends Command {
   public static final Class447 field0 = new Class447();
   public static final IRCCommand field1 = new IRCCommand();

   public void method0(String[] var1) {
      String var2 = String.join(" ", var1);
      if (var1.length != 0) {
         Class390.method0(var2);
      }

   }

   public static Class34 method0(AxisAlignedBB var0) {
      return ReplyCommand.method0(Class465.method0(Oringo.field9.thePlayer.getPositionEyes(1.0F), var0));
   }

   public boolean a_() {
      return true;
   }

   public String method1() {
      return "Sends a message to the irc chat!";
   }

   public static Object method0(Object var0, int var1) {
      try {
         Field var2 = var0.getClass().getDeclaredFields()[var1];
         var2.setAccessible(true);
         return var2.get(var0);
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public IRCCommand() {
      super("i", "irc", "oringochat", "oc");
   }
}
