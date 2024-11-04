package map;

import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import oringo.module.AutoCraftModule;
import oringo.module.AutoIceFillModule;

public class Class282 implements Class94 {
   public final Vec3 field1;
   public final EnumFacing field2;
   public final BlockPos field3;
   public int al_ = -1;

   public Class282(int var1, BlockPos var2, Vec3 var3, EnumFacing var4) {
      this.al_ = var1;
      this.field3 = var2;
      this.field1 = var3;
      this.field2 = var4;
   }

   public void method1() {
      ItemStack var1 = field0.thePlayer.getHeldItem();
      Class426.method10();
      if (field0.playerController.onPlayerRightClick(field0.thePlayer, field0.theWorld, var1, this.field3, this.field2, this.field1)) {
         field0.thePlayer.swingItem();
      } else if (var1 != null) {
         AutoCraftModule.method3(new C08PacketPlayerBlockPlacement(var1));
      }

   }

   public static boolean method0(int var0, int var1, boolean[][] var2, List var3) {
      if (var0 >= 0 && var1 >= 0 && var0 < AutoIceFillModule.ad_.length && var1 < AutoIceFillModule.ad_[0].length && !var2[var0][var1] && AutoIceFillModule.ad_[var0][var1]) {
         var2[var0][var1] = true;
         if (var0 == AutoIceFillModule.field4 && var1 == AutoIceFillModule.field2 && Class142.method0(var2)) {
            return true;
         } else if (method0(var0 - 1, var1, var2, var3)) {
            var3.add("UP");
            return true;
         } else if (method0(var0 + 1, var1, var2, var3)) {
            var3.add("DOWN");
            return true;
         } else if (method0(var0, var1 - 1, var2, var3)) {
            var3.add("LEFT");
            return true;
         } else if (method0(var0, var1 + 1, var2, var3)) {
            var3.add("RIGHT");
            return true;
         } else {
            var2[var0][var1] = false;
            return false;
         }
      } else {
         return false;
      }
   }

   public void method4() {
      if (this.al_ >= 0 && this.al_ <= 8) {
         field0.thePlayer.inventory.currentItem = this.al_;
      }
   }

   public Class282(BlockPos var1, Vec3 var2, EnumFacing var3) {
      this.field3 = var1;
      this.field1 = var2;
      this.field2 = var3;
   }

   public boolean method3() {
      return true;
   }

   public boolean method2() {
      return !field0.thePlayer.isUsingItem() && !field0.playerController.getIsHittingBlock();
   }

   public void method0() {
   }
}
