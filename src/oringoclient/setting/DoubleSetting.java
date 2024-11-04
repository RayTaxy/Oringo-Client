package oringo.setting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import map.Class1;
import map.Class12;
import map.Class157;
import map.Class208;
import map.Class376;
import map.Class500;
import net.minecraft.util.BlockPos;
import oringo.module.FragHelperModule;
import oringo.module.LividFinderModule;
import oringo.module.PestESPModule;

public class DoubleSetting extends Setting {
   @Expose
   @SerializedName("value")
   public double y_;
   public String field2 = "";
   public final double field3;
   public double field4;
   public double field5;
   public double field6;

   public double method0() {
      return this.y_;
   }

   public float method1() {
      return (float)this.method0();
   }

   public double method2() {
      return this.field5;
   }

   public static int method0(BlockPos var0) {
      Class12 var1 = FragHelperModule.method0(var0.getX(), var0.getZ());
      Class208 var2 = LividFinderModule.method0(var1.method1(), var1.method0());
      Class1 var3 = Class157.method0(var0, var1, var2);
      if (var3 == null) {
         return -1;
      } else {
         var3.field5 = !var3.field5;
         PestESPModule.n_();
         return var3.field5 ? 1 : 0;
      }
   }

   public DoubleSetting(String var1, double var2, double var4, double var6, double var8) {
      super(var1);
      this.y_ = var2;
      this.field4 = var4;
      this.field5 = var6;
      this.field3 = var2;
      this.field6 = var8;
   }

   public int method3() {
      return (int)this.method0();
   }

   public double method4() {
      return this.field6;
   }

   public void method1(double var1) {
      this.field5 = var1;
   }

   public void method2(double var1) {
      this.field4 = var1;
   }

   public void method3(double var1) {
      this.field6 = var1;
   }

   public double method5() {
      return this.field4;
   }

   public void method4(double var1) {
      this.y_ = var1;
   }

   public void method0(double var1) {
      var1 = Class376.method0(var1, this.method5(), this.method2());
      var1 = (double)Math.round(var1 * (1.0D / this.method4())) / (1.0D / this.method4());
      this.y_ = var1;
   }

   public DoubleSetting(String var1, double var2, double var4, double var6, double var8, Class500 var10) {
      super(var1, var10);
      this.y_ = var2;
      this.field4 = var4;
      this.field5 = var6;
      this.field3 = var2;
      this.field6 = var8;
   }

   public DoubleSetting method0(String var1) {
      this.field2 = var1;
      return this;
   }

   public String method6() {
      return this.field2;
   }
}
