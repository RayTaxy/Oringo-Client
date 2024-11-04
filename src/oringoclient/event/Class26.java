package oringo.event;

import map.Class506;
import net.minecraft.client.renderer.culling.ICamera;

public class Class26 extends Event {
   public final char field0;
   public final int field1;

   public static void method0(ICamera var0) {
      Class506.field0 = var0;
   }

   public Class26(int var1, char var2) {
      this.field1 = var1;
      this.field0 = var2;
   }
}
