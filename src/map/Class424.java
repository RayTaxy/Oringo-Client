package map;

import java.awt.Color;
import oringo.setting.ColorSetting;

public enum Class424 {
   field0(Class46.field4.field2);

   private static final Class424[] field6 = new Class424[]{field1, field2, field0, field3, field5};
   field1(Class46.field7.field2),
   field2(new ColorSetting("Wither color", Color.BLACK, false)),
   field3(Class46.field6.field2);

   public final ColorSetting field4;
   field5(new ColorSetting("Empty color", 0, true));

   public Color method0() {
      return this.field4.method17();
   }

   private Class424(ColorSetting var3) {
      this.field4 = var3;
   }
}
