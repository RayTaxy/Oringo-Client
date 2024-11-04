package map;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Class526 {
   public static void method0(String var0, String... var1) {
      try {
         Class313.method0();
         List var2 = Class285.method0(var0);
         var2.addAll(Arrays.asList(var1));
         (new ProcessBuilder(new String[0])).directory((new File("a")).getParentFile()).command(var2).start();
         FMLCommonHandler.instance().handleExit(0);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   public static Class361 method0() {
      return Class430.field4;
   }
}
