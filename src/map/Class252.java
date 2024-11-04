package map;

import net.minecraft.client.gui.ScaledResolution;
import oringo.setting.DoubleSetting;

public class Class252 extends Class283 {
   public static final Class252 field2;

   public void method2() {
      super.method2();
      new ScaledResolution(field0);
      this.method1(150.0F, 15.0F);
   }

   static {
      field2 = new Class252(Class362.field7.field5, Class362.field7.field29);
   }

   public Class252(DoubleSetting var1, DoubleSetting var2) {
      super(var1, var2);
   }
}
