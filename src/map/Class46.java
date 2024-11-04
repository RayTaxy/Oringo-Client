package map;

import java.awt.Color;
import oringo.setting.ColorSetting;

public enum Class46 {
   field0(new ColorSetting("Rare color", field7.method0().brighter(), false)),
   field1(new ColorSetting("Trap color", new Color(239, 91, 91), false));

   public final ColorSetting field2;
   field3(new ColorSetting("Puzzle color", new Color(161, 65, 255), false)),
   field4(new ColorSetting("Blood color", new Color(244, 0, 0), false)),
   field5(new ColorSetting("Yellow color", new Color(215, 218, 49), false)),
   field6(new ColorSetting("Entrance color", new Color(31744), false)),
   field7(new ColorSetting("Normal color", new Color(7356955), false));

   private static final Class46[] field9 = new Class46[]{field6, field7, field8, field5, field3, field1, field4, field0};
   field8(new ColorSetting("Fairy color", new Color(237, 126, 164), false));

   private Class46(ColorSetting var3) {
      this.field2 = var3;
   }

   public Color method0() {
      return this.field2.method17();
   }
}
