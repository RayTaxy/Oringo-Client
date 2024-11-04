package map;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import oringo.module.AutoCraftModule;
import oringo.module.AutoIceFillModule;

public class Class350 implements Class94 {
   public int al_ = -1;

   public boolean method3() {
      return true;
   }

   public static BlockPos method0(BlockPos var0, EnumFacing var1) {
      // $FF: Couldn't be decompiled
   }

   public Class350(int var1) {
      this.al_ = var1;
   }

   public static List method5() {
      ArrayList var0 = new ArrayList();
      boolean[][] var1 = new boolean[AutoIceFillModule.ad_.length][AutoIceFillModule.ad_[0].length];
      return Class282.method0(AutoIceFillModule.field5, AutoIceFillModule.field0, var1, var0) ? var0 : null;
   }

   public void method4() {
      if (this.al_ >= 0 && this.al_ <= 8) {
         field0.thePlayer.inventory.currentItem = this.al_;
      }
   }

   public void method0() {
   }

   public void method1() {
      Class426.method10();
      ItemStack var1 = field0.thePlayer.getHeldItem();
      if (var1 != null) {
         AutoCraftModule.method3(new C08PacketPlayerBlockPlacement(var1));
      }
   }

   public boolean method2() {
      return !field0.thePlayer.isUsingItem() && !field0.playerController.getIsHittingBlock();
   }

   public Class350() {
   }
}
