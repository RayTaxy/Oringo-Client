package oringo.event;

import java.io.IOException;
import map.Class315;
import net.minecraft.client.gui.Gui;

public class Class437 extends Event {
   public final Gui field0;

   public static int method0(byte[] var0, Class315.Class3 var1) throws IOException {
      if (var0.length < 6) {
         throw new IOException("Image is truncated.");
      } else {
         var1.field4 = new String(var0, 0, 6);
         if (!var1.field4.equals("GIF87a") && !var1.field4.equals("GIF89a")) {
            throw new IOException("Invalid GIF header.");
         } else {
            return 6;
         }
      }
   }

   public Class437(Gui var1) {
      this.field0 = var1;
   }
}
