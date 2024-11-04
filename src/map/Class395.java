package map;

import java.util.function.BooleanSupplier;

public class Class395 implements Class94 {
   public final boolean field1;
   public final Runnable field2;
   public final BooleanSupplier field3;

   public boolean method3() {
      return this.field1;
   }

   public boolean method2() {
      return this.field3.getAsBoolean();
   }

   public void method4() {
   }

   public void method1() {
      this.field2.run();
   }

   public Class395(Runnable var1, boolean var2, BooleanSupplier var3) {
      this.field2 = var1;
      this.field1 = var2;
      this.field3 = var3;
   }

   public void method0() {
   }
}
