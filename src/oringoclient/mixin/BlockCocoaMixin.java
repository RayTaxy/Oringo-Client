package oringo.mixin;

import map.Class229;
import net.minecraft.block.BlockCocoa;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.module.DojoHelperModule;

@Mixin({BlockCocoa.class})
public abstract class BlockCocoaMixin extends BlockMixin {
   public void collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4, CallbackInfoReturnable var5) {
      if (DojoHelperModule.method2() || Class229.method1().method44()) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

   }

   public void getSelectedBoundingBox(World var1, BlockPos var2, CallbackInfoReturnable var3) {
      if (DojoHelperModule.method2() || Class229.method1().method44()) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

   }
}
