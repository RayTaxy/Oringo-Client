package map;

import java.util.concurrent.TimeUnit;

public class Class447 {
   public long field4;

   public void method0(long var1) {
      this.field4 = System.currentTimeMillis() - var1;
   }

   public long method0() {
      return System.currentTimeMillis() - this.field4;
   }

   public Class447() {
      this.o_();
   }

   public boolean method0(long var1, TimeUnit var3) {
      return this.method0(var1, var3, false);
   }

   public void o_() {
      this.field4 = System.currentTimeMillis();
   }

   public boolean method0(double var1) {
      return this.a_(Math.round(var1));
   }

   public boolean a_(long var1) {
      return this.method0(var1, false);
   }

   public boolean method0(long var1, TimeUnit var3, boolean var4) {
      return this.method0(var3.toMillis(var1), var4);
   }

   public Class447(long var1) {
      this.field4 = var1;
   }

   public boolean method0(long var1, boolean var3) {
      if (System.currentTimeMillis() - this.field4 >= var1) {
         if (var3) {
            this.o_();
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean method0(double var1, boolean var3) {
      return this.method0(Math.round(var1), var3);
   }

   public long method2() {
      return System.currentTimeMillis() - this.field4;
   }
}
