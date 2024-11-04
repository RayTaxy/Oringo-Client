package oringo.module;

import java.util.Arrays;
import java.util.List;
import map.Class376;
import map.Class447;
import map.Class95;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import oringo.Oringo;
import oringo.event.Class210;
import oringo.event.Class75;
import oringo.setting.DoubleSetting;
import oringo.setting.Setting;

public class FDSwapperModule extends Module {
   public boolean field0;
   public final int[] ce_ = new int[]{-1, -1, -1, -1};
   public final Class447 field2 = new Class447();
   public final List field3 = Arrays.asList("FINAL_DESTINATION_BOOTS", "FINAL_DESTINATION_LEGGINGS", "FINAL_DESTINATION_CHESTPLATE", "FINAL_DESTINATION_HELMET");
   public final DoubleSetting field4 = (DoubleSetting)(new DoubleSetting("Swap back delay", 750.0D, 0.0D, 5000.0D, 50.0D)).method2("Too low will cause you to not equip armor");

   public static boolean lambda$onDraw$0(String var0, ItemStack var1) {
      return var0.equals(BlockHitModule.method0(var1));
   }

   public static void method5() {
      GL11.glStencilFunc(519, 1, 1);
      GL11.glStencilOp(7681, 7681, 7681);
      GL11.glColorMask(false, false, false, false);
   }

   public FDSwapperModule() {
      super("FD Swapper", Category.skyblock, SubCategory.slayer, "Auto swaps to FD and uses the ability");
      this.method0((Setting[])(new Setting[]{this.field4}));
   }

   @SubscribeEvent
   public void method0(Class75 var1) {
      if (this.field0 && this.field2.a_((long)this.field4.method3())) {
         this.method6();
         this.field0 = false;
      }
   }

   public static EnumFacing method0(BlockPos var0, Vec3 var1) {
      IBlockState var2 = Oringo.field9.theWorld.getBlockState(var0);
      Vec3 var3 = Oringo.field9.thePlayer.getPositionEyes(1.0F);
      MovingObjectPosition var4 = var2.getBlock().collisionRayTrace(Oringo.field9.theWorld, var0, var3, var1);
      return var4 == null ? null : var4.sideHit;
   }

   public void method6() {
      int[] var1 = this.ce_;
      int var2 = var1.length;

      int var3;
      int var4;
      for(var3 = 0; var3 < var2; ++var3) {
         var4 = var1[var3];
         if (var4 == -1) {
            return;
         }
      }

      var1 = this.ce_;
      var2 = var1.length;

      for(var3 = 0; var3 < var2; ++var3) {
         var4 = var1[var3];
         ItemStack var5 = field58.thePlayer.inventoryContainer.getSlot(var4).getStack();
         if (var5 != null && (var5.getItem() instanceof ItemArmor || var5.getItem() instanceof ItemSkull)) {
            int var6 = 5;
            if (var5.getItem() instanceof ItemArmor) {
               var6 += ((ItemArmor)var5.getItem()).armorType;
            }

            if (var4 != var6) {
               ServerRotationsModule.method0(var4, 0);
               ServerRotationsModule.method0(var6, 0);
               ServerRotationsModule.method0(var4, 0);
            }
         }
      }

   }

   @SubscribeEvent
   public void method0(Class210.Class1 var1) {
      if (this.field2.a_(5050L) && RenderChunkBoundsModule.method5()) {
         if (!Class376.method0("Voidgloom Seraph") || !Class376.method0("Slay the boss!")) {
            return;
         }

         for(int var2 = 0; var2 < this.field3.size(); ++var2) {
            String var3 = (String)this.field3.get(var2);
            int var4 = Class95.method0(FDSwapperModule::lambda$onDraw$0);
            if (var4 == -1) {
               return;
            }

            this.ce_[var2] = var4;
         }

         this.field2.o_();
         this.field0 = true;
         this.method6();
         var1.method2(true);
      }

   }
}
