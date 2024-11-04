package oringo.event;

import java.io.IOException;
import java.io.InputStream;
import map.Class315;
import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class Class332 extends Event {
   public final Packet field0;

   public Packet method0() {
      return this.field0;
   }

   public Class332(Packet var1) {
      this.field0 = var1;
   }

   public static Class315.Class3 method0(InputStream var0) throws IOException {
      byte[] var1 = new byte[var0.available()];
      var0.read(var1, 0, var1.length);
      return Class189.method0(var1);
   }

   public static class Class0 extends Event {
      public final Packet field0;

      public Class0(Packet var1) {
         this.field0 = var1;
      }
   }
}
