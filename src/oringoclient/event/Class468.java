package oringo.event;

import net.minecraft.util.MovementInput;

public class Class468 extends Event {
   public float field0;
   public float de_;
   public boolean field2;
   public boolean field3;

   public Class468 method0(float var1) {
      this.de_ = var1;
      return this;
   }

   public Class468 method0(MovementInput var1) {
      if (var1 == null) {
         return this;
      } else {
         this.field2 = var1.sneak;
         this.field3 = var1.jump;
         this.de_ = var1.moveForward;
         this.field0 = var1.moveStrafe;
         return this;
      }
   }

   public Class468 method0(boolean var1) {
      this.field2 = var1;
      return this;
   }

   public Class468 method1(boolean var1) {
      this.field3 = var1;
      return this;
   }

   public boolean I_() {
      return this.field2;
   }

   public boolean method1() {
      return this.field3;
   }

   public float method2() {
      return this.de_;
   }

   public Class468(float var1, float var2, boolean var3, boolean var4) {
      this.de_ = var1;
      this.field3 = var3;
      this.field0 = var2;
      this.field2 = var4;
   }

   public float G_() {
      return this.field0;
   }

   public Class468 method1(float var1) {
      this.field0 = var1;
      return this;
   }

   public Class468 method4() {
      this.de_ = 0.0F;
      this.field0 = 0.0F;
      this.field3 = false;
      this.field2 = false;
      return this;
   }

   public MovementInput method0() {
      MovementInput var1 = new MovementInput();
      var1.moveForward = this.de_;
      var1.moveStrafe = this.field0;
      var1.sneak = this.field2;
      var1.jump = this.field3;
      return var1;
   }
}
