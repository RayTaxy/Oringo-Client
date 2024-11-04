package map;

public class Class296 {
   public long field4;
   public static long field1 = 0L;

   public boolean a_(long var1) {
      return this.method0(var1, false);
   }

   public void method0(long var1) {
      this.field4 = field1 - var1;
   }

   public boolean method0(long var1, boolean var3) {
      if (field1 - this.field4 >= var1) {
         if (var3) {
            this.o_();
         }

         return true;
      } else {
         return false;
      }
   }

   public void o_() {
      this.field4 = field1;
   }

   public Class296() {
      this.o_();
   }

   public Class296(long var1) {
      this.field4 = var1;
   }

   public long method0() {
      return field1 - this.field4;
   }
}
