package map;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import oringo.module.AutoIceFillModule;

public class Class142 implements Class94 {
   public final Entity aM_;
   public int al_ = -1;

   public boolean method3() {
      return true;
   }

   public boolean method2() {
      return !field0.thePlayer.isUsingItem() && !field0.playerController.getIsHittingBlock();
   }

   public Class142(Entity var1) {
      this.aM_ = var1;
   }

   public void method4() {
      if (this.al_ >= 0 && this.al_ <= 8) {
         field0.thePlayer.inventory.currentItem = this.al_;
      }
   }

   public static boolean method0(boolean[][] var0) {
      for(int var1 = 0; var1 < var0.length; ++var1) {
         for(int var2 = 0; var2 < var0[var1].length; ++var2) {
            if (var0[var1][var2] != AutoIceFillModule.ad_[var1][var2]) {
               return false;
            }
         }
      }

      return true;
   }

   public void method0() {
   }

   public Class142(int var1, Entity var2) {
      this.al_ = var1;
      this.aM_ = var2;
   }

   public void method1() {
      Class426.method10();
      if (!field0.playerController.isPlayerRightClickingOnEntity(field0.thePlayer, this.aM_, new MovingObjectPosition(this.aM_))) {
         field0.playerController.interactWithEntitySendPacket(field0.thePlayer, this.aM_);
      }

   }
}
