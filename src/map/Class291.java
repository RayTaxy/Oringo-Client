package map;

import net.minecraft.entity.Entity;

public class Class291 implements Class94 {
   public final Entity field1;
   public int al_ = -1;

   public boolean method2() {
      return !field0.thePlayer.isUsingItem();
   }

   public static void z_() {
      Class265.field0.mkdirs();
   }

   public void method4() {
      if (this.al_ >= 0 && this.al_ <= 8) {
         field0.thePlayer.inventory.currentItem = this.al_;
      }
   }

   public void method0() {
      Class426.method10();
      field0.thePlayer.swingItem();
      field0.playerController.attackEntity(field0.thePlayer, this.field1);
   }

   public boolean method3() {
      return true;
   }

   public void method1() {
   }

   public Class291(int var1, Entity var2) {
      this.al_ = var1;
      this.field1 = var2;
   }

   public Class291(Entity var1) {
      this.field1 = var1;
   }
}
