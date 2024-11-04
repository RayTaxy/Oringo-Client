package map;

import java.util.Objects;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;

public class Class228 {
   public final double field0;
   public final double field1;
   public final double field2;

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         Class228 var2 = (Class228)var1;
         return Double.compare(var2.field2, this.field2) == 0 && Double.compare(var2.field1, this.field1) == 0 && Double.compare(var2.field0, this.field0) == 0;
      } else {
         return false;
      }
   }

   public double method0(Vec3 var1) {
      double var2 = this.field2 - var1.xCoord;
      double var4 = this.field1 - var1.yCoord;
      double var6 = this.field0 - var1.zCoord;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public double method0(Class228 var1) {
      return this.method0(var1.method6());
   }

   public Class228 method0(double var1) {
      double var3 = this.method2();
      return var3 == 0.0D ? new Class228(0.0D, this.field1, 0.0D) : this.method1((var3 - var1) / var3, 1.0D, (var3 - var1) / var3);
   }

   public Class228 method1(Vec3 var1) {
      return new Class228(this.field2 + var1.xCoord, this.field1 + var1.yCoord, this.field0 + var1.zCoord);
   }

   public Class228(double var1, double var3, double var5) {
      this.field2 = var1;
      this.field1 = var3;
      this.field0 = var5;
   }

   public double method0(BlockPos var1) {
      return this.method3((new Vec3(var1)).addVector(0.5D, 0.5D, 0.5D));
   }

   public Class228 method0() {
      double var1 = this.method4();
      return var1 < 1.0E-4D ? new Class228(0.0D, 0.0D, 0.0D) : new Class228(this.field2 / var1, this.field1 / var1, this.field0 / var1);
   }

   public Class228 method1(double var1) {
      return new Class228(this.field2 * var1, this.field1 * var1, this.field0 * var1);
   }

   public BlockPos method1() {
      return new BlockPos((double)Math.round(this.field2), (double)Math.round(this.field1), (double)Math.round(this.field0));
   }

   public Class228 method2(double var1) {
      return new Class228(this.field2, this.field1, var1);
   }

   public double method1(Class228 var1) {
      return Math.sqrt(this.method0(var1));
   }

   public Class228 method2(Class228 var1) {
      return new Class228(this.field2 - var1.field2, this.field1 - var1.field1, this.field0 - var1.field0);
   }

   public Class228 method2(Vec3 var1) {
      return new Class228(this.field2 - var1.xCoord, this.field1 - var1.yCoord, this.field0 - var1.zCoord);
   }

   public Class228 method0(float var1) {
      float var2 = MathHelper.cos(var1);
      float var3 = MathHelper.sin(var1);
      return new Class228(this.field2 * (double)var2 + this.field0 * (double)var3, this.field1, this.field0 * (double)var2 - this.field2 * (double)var3);
   }

   public Class228 method0(double var1, double var3, double var5) {
      return new Class228(this.field2 / var1, this.field1 / var3, this.field0 / var5);
   }

   public String toString() {
      return String.format("x=%.2f, y=%.2f, z=%.2f", this.field2, this.field1, this.field0);
   }

   public double method2() {
      return Math.sqrt(this.field2 * this.field2 + this.field0 * this.field0);
   }

   public Class228 method3(double var1) {
      return new Class228(this.field2, var1, this.field0);
   }

   public BlockPos method3() {
      return new BlockPos(this.field2, this.field1, this.field0);
   }

   public Class228 method4(double var1) {
      return new Class228(var1, this.field1, this.field0);
   }

   public double method3(Class228 var1) {
      return this.method4(var1.method6());
   }

   public Class228 method4(Class228 var1) {
      return new Class228(this.field2 + var1.field2, this.field1 + var1.field1, this.field0 + var1.field0);
   }

   public Class228(Vec3 var1) {
      this.field2 = var1.xCoord;
      this.field1 = var1.yCoord;
      this.field0 = var1.zCoord;
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.field2, this.field1, this.field0});
   }

   public Class228 method5(double var1) {
      return new Class228(this.field2 / var1, this.field1 / var1, this.field0 / var1);
   }

   public Class228(Vec3i var1) {
      this.field2 = (double)var1.getX();
      this.field1 = (double)var1.getY();
      this.field0 = (double)var1.getZ();
   }

   public Class228 method1(double var1, double var3, double var5) {
      return new Class228(this.field2 * var1, this.field1 * var3, this.field0 * var5);
   }

   public Class228 method6(double var1) {
      double var3 = this.method4();
      return var3 == 0.0D ? new Class228(0.0D, 0.0D, 0.0D) : this.method1((var3 - var1) / var3);
   }

   public double method4() {
      return Math.sqrt(this.field2 * this.field2 + this.field1 * this.field1 + this.field0 * this.field0);
   }

   public Class228 method1(float var1) {
      float var2 = MathHelper.cos(var1);
      float var3 = MathHelper.sin(var1);
      return new Class228(this.field2, this.field1 * (double)var2 + this.field0 * (double)var3, this.field0 * (double)var2 - this.field1 * (double)var3);
   }

   public Class228 method7(double var1) {
      double var3 = this.method4();
      return var3 == 0.0D ? new Class228(0.0D, 0.0D, 0.0D) : this.method1((var3 + var1) / var3);
   }

   public Class228(S2APacketParticles var1) {
      this.field2 = var1.getXCoordinate();
      this.field1 = var1.getYCoordinate();
      this.field0 = var1.getZCoordinate();
   }

   public double method3(Vec3 var1) {
      return Math.sqrt(this.method0(var1));
   }

   public double method4(Vec3 var1) {
      double var2 = this.field2 - var1.xCoord;
      double var4 = this.field0 - var1.zCoord;
      return var2 * var2 + var4 * var4;
   }

   public Class34 method5() {
      return Class271.method0(this.field2, this.field1, this.field0);
   }

   public Vec3 method6() {
      return new Vec3(this.field2, this.field1, this.field0);
   }

   public Class228 method2(double var1, double var3, double var5) {
      return new Class228(this.field2 - var1, this.field1 - var3, this.field0 - var5);
   }

   public double method1(BlockPos var1) {
      return this.method3(new Vec3(var1));
   }

   public Class228 method8(double var1) {
      double var3 = this.method2();
      return var3 == 0.0D ? new Class228(0.0D, this.field1, 0.0D) : this.method1((var3 + var1) / var3, 1.0D, (var3 + var1) / var3);
   }

   public Class228 method3(double var1, double var3, double var5) {
      return new Class228(this.field2 + var1, this.field1 + var3, this.field0 + var5);
   }
}
