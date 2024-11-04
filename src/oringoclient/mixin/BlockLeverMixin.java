package oringo.mixin;

import map.Class362;
import net.minecraft.block.BlockLever;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BlockLever.class})
public abstract class BlockLeverMixin extends BlockMixin {
   public void collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4, CallbackInfoReturnable var5) {
      if (Class362.field45.method44()) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   public void getSelectedBoundingBox(World var1, BlockPos var2, CallbackInfoReturnable var3) {
      if (Class362.field45.method44()) {
         this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }
   }
}
