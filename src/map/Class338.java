package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Class338 {
   public static String method0(InputStream var0) throws IOException {
      StringBuilder var1 = new StringBuilder();
      InputStreamReader var2 = new InputStreamReader(var0);
      Throwable var3 = null;

      try {
         BufferedReader var4 = new BufferedReader(var2);
         Throwable var5 = null;

         try {
            String var6;
            try {
               while((var6 = var4.readLine()) != null) {
                  var1.append(var6).append("\n");
               }
            } catch (Throwable var28) {
               var5 = var28;
               throw var28;
            }
         } finally {
            if (var4 != null) {
               if (var5 != null) {
                  try {
                     var4.close();
                  } catch (Throwable var27) {
                     var5.addSuppressed(var27);
                  }
               } else {
                  var4.close();
               }
            }

         }
      } catch (Throwable var30) {
         var3 = var30;
         throw var30;
      } finally {
         if (var2 != null) {
            if (var3 != null) {
               try {
                  var2.close();
               } catch (Throwable var26) {
                  var3.addSuppressed(var26);
               }
            } else {
               var2.close();
            }
         }

      }

      return var1.toString();
   }
}
