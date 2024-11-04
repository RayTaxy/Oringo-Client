package oringo.event;

import map.Class34;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class Class537 extends Event {
   public float field0;
   public boolean field1;
   public float de_;
   public boolean field3;
   public boolean bG_;
   public float t_;
   public float bF_;

   public boolean method0() {
      return !this.H_();
   }

   public boolean H_() {
      return this instanceof Class537.Class1;
   }

   public Class34 s_() {
      return new Class34(this.t_, this.bF_);
   }

   public Class537 method0(float var1, float var2) {
      this.bF_ = var2;
      this.t_ = var1;
      return this;
   }

   public Class537 method0(Class34 var1) {
      return this.method0(var1.method5(), var1.method2());
   }

   public Class537(float var1, float var2, boolean var3, float var4, float var5, boolean var6, boolean var7) {
      this.de_ = var4;
      this.field0 = var5;
      this.t_ = var1;
      this.bF_ = var2;
      this.bG_ = var3;
      this.field1 = var7;
      this.field3 = var6;
   }

   @Cancelable
   public static class Class0 extends Class537 {
      public Class0(Class537 var1) {
         super(var1.t_, var1.bF_, var1.bG_, var1.de_, var1.field0, var1.field3, var1.field1);
      }
   }

   @Cancelable
   public static class Class1 extends Class537 {
      public Class1(float var1, float var2, boolean var3, float var4, float var5, boolean var6, boolean var7) {
         super(var1, var2, var3, var4, var5, var6, var7);
      }
   }
}
