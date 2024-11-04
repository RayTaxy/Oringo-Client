package map;

import com.google.common.collect.Lists;
import java.util.List;

public class Class430 {
   public static final List field0;
   public static final List field1;
   public static final Class427 field2 = new Class427();
   public static final Class213 field3 = new Class213();
   public static final Class361 field4 = new Class361();

   public static float method0(float var0, float var1) {
      return var0 + (var1 - var0) * Class84.field0.nextFloat();
   }

   static {
      field0 = Lists.newArrayList(new Class83[]{field4, new Class452(), new Class341(), new Class207(), new Class284(), new Class163(), field3, new Class451(), new Class139()});
      field1 = Lists.newArrayList();
      field1.addAll(field0);
      field1.add(field2);
   }
}
