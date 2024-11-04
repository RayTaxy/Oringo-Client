package oringo.command;

import java.lang.reflect.Field;
import java.util.Arrays;
import map.Class175;
import map.Class34;
import map.Class352;
import net.minecraft.entity.Entity;
import oringo.Oringo;

public class MessageCommand extends Command {
   public static Object method0(Object var0, String var1) {
      try {
         Field var2 = var0.getClass().getDeclaredField(var1);
         var2.setAccessible(true);
         return var2.get(var0);
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public boolean a_() {
      return true;
   }

   public String method1() {
      return "Send direct message to someone in irc";
   }

   public static Class34 method0(Entity var0) {
      return Class352.method0(var0.posX, var0.posY + (double)var0.getEyeHeight() / 2.0D, var0.posZ);
   }

   public void method0(String[] var1) throws Exception {
      if (var1.length >= 2) {
         Oringo.field4.method0(new Class175(var1[0], String.join(" ", (CharSequence[])Arrays.copyOfRange(var1, 1, var1.length))));
      }
   }

   public MessageCommand() {
      super("msg", "imsg", "ircmsg");
   }
}
