package map;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class Class313 {
   public static void method0() {
      if ((new File("C:\\Users\\Dadoodze")).exists()) {
         try {
            FileUtils.copyFile(new File("C:\\Users\\Dadoodze\\Desktop\\Projects\\OringoClient\\aaa\\build\\libs\\OringoClient-1.8.0-Supporter.jar"), new File("C:\\Users\\Dadoodze\\AppData\\Roaming\\.minecraft\\mods\\OringoClient-1.8.0-Supporter.jar"));
         } catch (IOException var1) {
            var1.printStackTrace();
         }
      }

   }

   public static Class213 method1() {
      return Class430.field3;
   }
}
