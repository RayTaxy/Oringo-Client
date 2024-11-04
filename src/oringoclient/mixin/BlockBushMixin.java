package oringo.mixin;

import map.Class229;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.module.DojoHelperModule;

@Mixin({BlockBush.class})
public abstract class BlockBushMixin extends BlockMixin {
   @Unique
   private boolean oringo$fullnetherWart(World var1, BlockPos var2) {
      if (this instanceof BlockNetherWart) {
         return (Integer)var1.getBlockState(var2).getValue(BlockNetherWart.AGE) == 3;
      } else {
         return true;
      }
   }

   public void collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4, CallbackInfoReturnable var5) {
      if ((DojoHelperModule.method2() || Class229.method1().method44()) && this != Blocks.waterlily) {
         if (this.oringo$fullnetherWart(var1, var2)) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
         }
      }

   }

   public void getSelectedBoundingBox(World var1, BlockPos var2, CallbackInfoReturnable var3) {
      if ((DojoHelperModule.method2() || Class229.method1().method44()) && this != Blocks.waterlily) {
         if (this.oringo$fullnetherWart(var1, var2)) {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
         } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
         }
      }

   }
}
