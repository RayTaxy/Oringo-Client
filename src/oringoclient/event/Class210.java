package oringo.event;

import map.Class34;
import map.Class513;
import map.Class7;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import org.lwjgl.input.Mouse;
import oringo.module.PlusColorChangerModule;

@Cancelable
public class Class210 extends Event {
   public boolean field0;
   public float t_;
   public float bF_;
   public boolean bG_;
   public double i_;
   public boolean field1;
   public boolean field6;
   public double field4;
   public boolean field8;
   public double j_;

   public Class34 s_() {
      return new Class34(this.t_, this.bF_);
   }

   public boolean method0() {
      return !this.H_();
   }

   public static String method2() {
      return PlusColorChangerModule.field2.method44() ? PlusColorChangerModule.field2.E_.method5().toString() : null;
   }

   public Class210 method0(boolean var1) {
      this.field8 = var1;
      return this;
   }

   public Class210 method0(Class34 var1) {
      return this.method0(var1.method5(), var1.method2());
   }

   public boolean H_() {
      return this instanceof Class210.Class1;
   }

   public Class210 method0(double var1) {
      this.i_ = var1;
      return this;
   }

   public Class210 method1(boolean var1) {
      this.bG_ = var1;
      return this;
   }

   public static boolean method0(Entity var0) {
      AxisAlignedBB var1 = var0.getEntityBoundingBox();
      return Class290.method0().isBoundingBoxInFrustum(var1);
   }

   public Vec3 getPosition() {
      return new Vec3(this.j_, this.i_, this.field4);
   }

   public Class210(double var1, double var3, double var5, float var7, float var8, boolean var9, boolean var10, boolean var11) {
      this.j_ = var1;
      this.i_ = var3;
      this.field4 = var5;
      this.t_ = var7;
      this.bF_ = var8;
      this.bG_ = var9;
      this.field1 = var11;
      this.field8 = var10;
   }

   public Class210 method1(double var1) {
      this.j_ = var1;
      return this;
   }

   public Class210 method0(float var1) {
      this.bF_ = var1;
      return this;
   }

   public Class210 method0(double var1, double var3, double var5) {
      this.j_ = var1;
      this.field4 = var5;
      this.i_ = var3;
      return this;
   }

   public Class210 method5() {
      this.field6 = true;
      return this;
   }

   public static int t_() {
      int var0 = Class7.field2.getScaledHeight();
      return var0 - Mouse.getY() * var0 / Class513.field0.displayHeight - 1;
   }

   public Class210 method0(Vec3 var1) {
      return this.method0(var1.xCoord, var1.yCoord, var1.zCoord);
   }

   public Class210 method2(double var1) {
      this.field4 = var1;
      return this;
   }

   public Class210 method2(boolean var1) {
      this.field1 = var1;
      return this;
   }

   public Class210 method1(float var1) {
      this.t_ = var1;
      return this;
   }

   public Class210 method0(float var1, float var2) {
      this.bF_ = var2;
      this.t_ = var1;
      return this;
   }

   @Cancelable
   public static class Class0 extends Class210 {
      public Class0(double var1, double var3, double var5, float var7, float var8, boolean var9, boolean var10, boolean var11) {
         super(var1, var3, var5, var7, var8, var9, var10, var11);
      }

      public Class0(Class210 var1) {
         super(var1.j_, var1.i_, var1.field4, var1.t_, var1.bF_, var1.bG_, var1.field8, var1.field1);
      }
   }

   @Cancelable
   public static class Class1 extends Class210 {
      public Class1(double var1, double var3, double var5, float var7, float var8, boolean var9, boolean var10, boolean var11) {
         super(var1, var3, var5, var7, var8, var9, var10, var11);
      }
   }
}
