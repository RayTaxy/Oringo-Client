package oringo.event;

import java.util.regex.Pattern;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import oringo.module.PlusColorChangerModule;

@Cancelable
public class Class217 extends Event {
   public double j_;
   public double i_;
   public double field4;

   public double method2() {
      return this.field4;
   }

   public double method6() {
      return this.i_;
   }

   public Class217 method0(double var1) {
      this.i_ = var1;
      return this;
   }

   public double w_() {
      return this.j_;
   }

   public static boolean method0(double var0, double var2, double var4, double var6) {
      int var8 = Class148.method6();
      int var9 = Class210.t_();
      return (double)var8 > var0 && (double)var8 < var0 + var4 && (double)var9 > var2 && (double)var9 < var2 + var6;
   }

   public static Pattern method3() {
      if (!PlusColorChangerModule.field58.getSession().getUsername().equals(PlusColorChangerModule.D_)) {
         PlusColorChangerModule.field3 = Pattern.compile("(§.\\[MVP)§.(\\+\\+?§.] " + PlusColorChangerModule.field58.getSession().getUsername() + ")");
         PlusColorChangerModule.D_ = PlusColorChangerModule.field58.getSession().getUsername();
      }

      return PlusColorChangerModule.field3;
   }

   public Class217 method1(double var1) {
      this.field4 = var1;
      return this;
   }

   public Class217 method2(double var1) {
      this.j_ = var1;
      return this;
   }

   public Class217(double var1, double var3, double var5) {
      this.j_ = var1;
      this.field4 = var5;
      this.i_ = var3;
   }

   public Class217 method0(double var1, double var3, double var5) {
      this.j_ = var1;
      this.field4 = var5;
      this.i_ = var3;
      return this;
   }

   public Class217 method4() {
      return this.method0(0.0D, 0.0D, 0.0D);
   }
}
