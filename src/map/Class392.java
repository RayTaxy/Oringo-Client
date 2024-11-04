package map;

import com.google.common.collect.Sets;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import me.oringo.Key;
import oringo.Oringo;

public class Class392 {
   public static final File field0;
   public static final ExecutorService field1;
   public static final Set field2;
   public static final File field3;
   public static final Set field4;
   public static final File field5;
   public static boolean field6;

   public static void lambda$isCapeReady$0(String var0) {
      try {
         HttpURLConnection var1 = (HttpURLConnection)(new URL(String.format("https://shiyu.moe/capes/?name=%s&key=%s", var0, Key.getKey()))).openConnection();
         if (var1.getResponseCode() == 200) {
            InputStream var2 = var1.getInputStream();
            Throwable var3 = null;

            try {
               Files.copy(var2, (new File(Class265.field0, var0 + ".png")).toPath(), new CopyOption[]{StandardCopyOption.REPLACE_EXISTING});
            } catch (Throwable var13) {
               var3 = var13;
               throw var13;
            } finally {
               if (var2 != null) {
                  if (var3 != null) {
                     try {
                        var2.close();
                     } catch (Throwable var12) {
                        var3.addSuppressed(var12);
                     }
                  } else {
                     var2.close();
                  }
               }

            }
         }
      } catch (IOException var15) {
         var15.printStackTrace();
      }

      field4.add(var0);
   }

   static {
      field5 = new File(Oringo.field5 + "assets");
      field0 = new File(Oringo.field5 + "assets/assets.zip");
      field3 = new File(Oringo.field5 + "capes/");
      field2 = Sets.newConcurrentHashSet();
      field4 = Sets.newConcurrentHashSet();
      field1 = Executors.newSingleThreadExecutor();
   }

   public static StringBuilder method0(StringBuilder var0, String var1) {
      return var0.append(var1);
   }
}
