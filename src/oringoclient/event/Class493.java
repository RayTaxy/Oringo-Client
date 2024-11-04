package oringo.event;

public class Class493 extends Event {
   public float de_;
   public boolean bG_;
   public float field2 = 0.91F;
   public float field0;

   public boolean method0() {
      return this.bG_;
   }

   public Class493 method0(boolean var1) {
      this.bG_ = var1;
      return this;
   }

   public void method0(float var1) {
      this.field2 = var1;
   }

   public Class493(boolean var1, float var2, float var3) {
      this.bG_ = var1;
      this.de_ = var2;
      this.field0 = var3;
   }

   public float method1() {
      return this.field2;
   }
}
