package map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class Class510 implements Class94 {
   public int al_ = -1;
   public BlockPos cB_;
   public EnumFacing field3;

   public void method1() {
   }

   public Class510(BlockPos var1, EnumFacing var2) {
      this.cB_ = var1;
      this.field3 = var2;
   }

   public boolean method3() {
      return false;
   }

   public void method4() {
      if (this.al_ >= 0 && this.al_ <= 8) {
         field0.thePlayer.inventory.currentItem = this.al_;
      }
   }

   public void method0() {
      Class426.method10();
      if (!field0.playerController.getIsHittingBlock()) {
         field0.thePlayer.swingItem();
         field0.playerController.clickBlock(this.cB_, this.field3);
      }

      if (field0.playerController.getIsHittingBlock() && field0.playerController.onPlayerDamageBlock(this.cB_, this.field3)) {
         field0.thePlayer.swingItem();
      }

   }

   public static ResourceLocation method0(EntityPlayer var0) {
      Class262 var1 = ((Class480)var0).method17();
      return var1 != null && var1.method5() != null ? new ResourceLocation("capes", var1.method5() + ".png") : null;
   }

   public boolean method2() {
      return !field0.thePlayer.isUsingItem();
   }

   public Class510(int var1, BlockPos var2, EnumFacing var3) {
      this.cB_ = var2;
      this.field3 = var3;
      this.al_ = var1;
   }
}
