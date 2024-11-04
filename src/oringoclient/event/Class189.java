package oringo.event;

import java.io.IOException;
import map.Class315;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class Class189 extends Event {
   public final Packet field0;
   public final NetworkManager field1;

   public Class189(Packet var1, NetworkManager var2) {
      this.field0 = var1;
      this.field1 = var2;
   }

   public static Class315.Class3 method0(byte[] var0) throws IOException {
      Class315 var1 = new Class315();
      Class315.Class3 var2 = var1.new Class3();
      Class315.Class0 var3 = null;
      int var4 = Class437.method0(var0, var2);
      var4 = Class125.method0(var2, var0, var4);
      if (var2.field9) {
         var2.field2 = new int[var2.field10];
         var4 = Class525.method0(var0, var2.field2, var4);
      }

      while(var4 < var0.length) {
         int var5 = var0[var4] & 255;
         switch(var5) {
         case 33:
            if (var4 + 1 >= var0.length) {
               throw new IOException("Unexpected end of file.");
            }

            switch(var0[var4 + 1] & 255) {
            case 1:
               var3 = null;
               var4 = Class255.method0(var0, var4);
               continue;
            case 249:
               if (var3 == null) {
                  var3 = var1.new Class0();
                  Class315.Class3.access$2500(var2).add(var3);
               }

               var4 = Class75.method0(var3, var0, var4);
               continue;
            case 254:
               var4 = Class255.method0(var0, var4);
               continue;
            case 255:
               var4 = Class329.method0(var2, var0, var4);
               continue;
            default:
               throw new IOException("Unknown extension at " + var4);
            }
         case 44:
            if (var3 == null) {
               var3 = var1.new Class0();
               Class315.Class3.access$2500(var2).add(var3);
            }

            var4 = Class464.method0(var3, var0, var4);
            if (Class315.Class0.access$1700(var3)) {
               Class315.Class0.access$1802(var3, new int[Class315.Class0.access$2600(var3)]);
               var4 = Class525.method0(var0, Class315.Class0.access$1800(var3), var4);
            }

            var4 = Class438.method0(var3, var0, var4);
            var3 = null;
            break;
         case 59:
            return var2;
         default:
            double var6 = 1.0D * (double)var4 / (double)var0.length;
            if (var6 < 0.9D) {
               throw new IOException("Unknown block at: " + var4);
            }

            var4 = var0.length;
         }
      }

      return var2;
   }

   public static class Class0 extends Event {
      public final Packet field0;
      public final NetworkManager field1;

      public Class0(Packet var1, NetworkManager var2) {
         this.field0 = var1;
         this.field1 = var2;
      }
   }
}
