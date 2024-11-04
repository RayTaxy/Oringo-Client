package oringo.mixin;

import map.Class229;
import net.minecraft.block.BlockCrops;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.module.DojoHelperModule;

@Mixin({BlockCrops.class})
public abstract class BlockCropsMixin extends BlockMixin {
   public void getSelectedBoundingBox(World var1, BlockPos var2, CallbackInfoReturnable var3) {
      if (DojoHelperModule.method2() || Class229.method1().method44()) {
         if (this.oringo$fullCrop(var1, var2)) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
         }
      }

   }

   public void collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4, CallbackInfoReturnable var5) {
      if (DojoHelperModule.method2() || Class229.method1().method44()) {
         if (this.oringo$fullCrop(var1, var2)) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
         }
      }

   }

   @Unique
   private boolean oringo$fullCrop(World var1, BlockPos var2) {
      return (Integer)var1.getBlockState(var2).getValue(BlockCrops.AGE) == 7;
   }
}
