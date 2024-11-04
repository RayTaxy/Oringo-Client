package map;

public abstract class Class343 {
   public float field0 = 0.0F;
   public final Class447 field1 = new Class447();
   public long field2 = -1L;
   public long field3 = 0L;

   public void method0(boolean var1) {
      this.field1.method0(var1 ? (long)((float)this.field3 * this.method2()) : 0L);
   }

   public void o_() {
      this.field1.method0(this.method3() ? (long)((float)this.field3 * (1.0F - this.method2())) : 0L);
   }

   public boolean a_(long var1) {
      return this.field1.a_(var1);
   }

   public Class343 method1(long var1) {
      this.field3 = var1;
      return this;
   }

   public Class343 method0(float var1) {
      this.field0 = var1;
      return this;
   }

   public float method2() {
      return this.field2 != -1L && this.field1.a_(this.field2) ? Class163.method0(1.0F - ((float)(this.field1.method0() - this.field2) / (float)this.field3 * (1.0F - this.field0) + this.field0), 1.0F, 0.0F) : Class163.method0((float)this.field1.method0() / (float)this.field3 * (1.0F - this.field0) + this.field0, 1.0F, 0.0F);
   }

   public abstract float method0();

   public Class343 method2(long var1) {
      this.field2 = var1;
      return this;
   }

   public boolean method3() {
      return this.field2 != -1L && this.field1.a_(this.field2);
   }

   public static StringBuilder method0(StringBuilder var0, String var1) {
      return var0.append(var1);
   }

   public boolean method4() {
      return this.field1.a_(this.field3);
   }
}
