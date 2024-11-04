package map;

import java.awt.Font;
import java.io.InputStream;
import net.minecraft.util.ResourceLocation;
import oringo.Oringo;

public class Class42 {
   public static Font method0(String var0, float var1) {
      Font var2;
      try {
         if (Class311.field3.containsKey(var0)) {
            var2 = (Font)Class311.field3.get(var0);
            if ((float)var2.getSize() != var1 || var2.getStyle() != 0) {
               var2 = var2.deriveFont(0, var1);
            }
         } else {
            InputStream var3 = Oringo.field9.getResourceManager().getResource(new ResourceLocation("oringoclient", "fonts/" + var0)).getInputStream();
            Throwable var4 = null;

            try {
               var2 = Font.createFont(0, var3);
               Class311.field3.put(var0, var2);
               var2 = var2.deriveFont(0, var1);
            } catch (Throwable var14) {
               var4 = var14;
               throw var14;
            } finally {
               if (var3 != null) {
                  if (var4 != null) {
                     try {
                        var3.close();
                     } catch (Throwable var13) {
                        var4.addSuppressed(var13);
                     }
                  } else {
                     var3.close();
                  }
               }

            }
         }
      } catch (Exception var16) {
         Class311.field9.error("Loading font {} failed!", new Object[]{var0, var16});
         var2 = new Font("default", 0, (int)var1);
      }

      return var2;
   }
}
