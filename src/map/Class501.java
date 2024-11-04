package map;

import java.util.List;

public class Class501 implements Class94 {
   public int al_ = -1;

   public Class501(int var1) {
      this.al_ = var1;
   }

   public void method4() {
      if (this.al_ >= 0 && this.al_ <= 8) {
         field0.thePlayer.inventory.currentItem = this.al_;
      }
   }

   public Class501() {
   }

   public boolean method2() {
      return !field0.thePlayer.isUsingItem();
   }

   public boolean method3() {
      return true;
   }

   public void method0() {
      Class426.method10();
      field0.thePlayer.swingItem();
   }

   public static List method5() {
      return Class430.field0;
   }

   public void method1() {
   }
}
