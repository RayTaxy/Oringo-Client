package oringo.event;

import map.Class315;

public class Class75 extends Event {
   public static int method0(Class315.Class0 var0, byte[] var1, int var2) {
      Class315.Class0.access$2302(var0, (var1[var2 + 3] & 28) >>> 2);
      Class315.Class0.access$402(var0, (var1[var2 + 3] & 1) == 1);
      Class315.Class0.access$2402(var0, var1[var2 + 4] & 255 | (var1[var2 + 5] & 255) << 8);
      Class315.Class0.access$502(var0, var1[var2 + 6] & 255);
      return var2 + 8;
   }
}
