package map;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class Class465 {
   public static final Map field0 = Maps.newEnumMap(Class465.Class0.class);

   public static Vec3 method0(Vec3 var0, AxisAlignedBB var1) {
      return new Vec3(Class359.method0(var1.minX, var1.maxX, var0.xCoord), Class359.method0(var1.minY, var1.maxY, var0.yCoord), Class359.method0(var1.minZ, var1.maxZ, var0.zCoord));
   }

   static {
      Class465.Class0[] var0 = Class465.Class0.values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         Class465.Class0 var3 = var0[var2];
         field0.put(var3, HashBiMap.create());
      }

      Class229.method0(Class29.class, Class465.Class0.field0);
      Class229.method0(Class175.class, Class465.Class0.field0);
      Class229.method0(Class271.class, Class465.Class0.field0);
      Class229.method0(Class92.class, Class465.Class0.field0);
      Class229.method0(Class474.class, Class465.Class0.field0);
      Class229.method0(Class14.class, Class465.Class0.field0);
      Class229.method0(Class352.class, Class465.Class0.field0);
      Class229.method0(Class256.class, Class465.Class0.field0);
      Class229.method0(Class305.class, Class465.Class0.field0);
      Class229.method0(Class297.class, Class465.Class0.field1);
      Class229.method0(Class359.class, Class465.Class0.field1);
      Class229.method0(Class328.class, Class465.Class0.field1);
      Class229.method0(Class337.class, Class465.Class0.field1);
      Class229.method0(Class314.class, Class465.Class0.field1);
   }

   public static boolean method0(EnumDyeColor var0) {
      // $FF: Couldn't be decompiled
   }

   public static enum Class0 {
      private static final Class465.Class0[] field2 = new Class465.Class0[]{field0, field1};
      field0,
      field1;
   }
}
