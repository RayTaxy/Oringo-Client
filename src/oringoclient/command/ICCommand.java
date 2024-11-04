package oringo.command;

import java.lang.reflect.Field;
import map.Class14;
import map.Class266;
import map.Class483;
import oringo.Oringo;

public class ICCommand extends Command {
   public String method1() {
      return null;
   }

   public ICCommand() {
      super("icommand", "ic");
   }

   public static Class483 method2() {
      return new Class266();
   }

   public void method0(String[] var1) throws Exception {
      Oringo.field4.method0(new Class14(var1));
   }

   public boolean a_() {
      return true;
   }

   public static void method0(Class var0, int var1, Object var2, Object var3) {
      try {
         Field var4 = var0.getDeclaredFields()[var1];
         var4.setAccessible(true);
         var4.set(var2, var3);
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }
}
