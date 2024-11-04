package oringo.mixin;

import java.util.List;
import map.Class362;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.Oringo;
import oringo.event.Class194;
import oringo.module.NoFoliageModule;
import oringo.module.XRayModule;

@Mixin(
   value = {Block.class},
   priority = 1
)
public abstract class BlockMixin {
   @Inject(
      method = {"getSelectedBoundingBox"},
      at = {@At("HEAD")}
   )
   public void getSelectedBoundingBox(World var1, BlockPos var2, CallbackInfoReturnable var3) {
   }

   @Overwrite
   public void addCollisionBoxesToList(World var1, BlockPos var2, IBlockState var3, AxisAlignedBB var4, List var5, Entity var6) {
      if (Oringo.field9.thePlayer == var6) {
         Class194 var8 = new Class194((Block)this, this.getCollisionBoundingBox(var1, var2, var3), var2);
         if (!MinecraftForge.EVENT_BUS.post(var8)) {
            if (var8.field1 != null && var4.intersectsWith(var8.field1)) {
               var5.add(var8.field1);
            }

         }
      } else {
         AxisAlignedBB var7 = this.getCollisionBoundingBox(var1, var2, var3);
         if (var7 != null && var4.intersectsWith(var7)) {
            var5.add(var7);
         }

      }
   }

   @Shadow
   public abstract String toString();

   @Shadow
   public abstract void setBlockBounds(float var1, float var2, float var3, float var4, float var5, float var6);

   @Shadow
   public abstract AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3);

   @Inject(
      method = {"getAmbientOcclusionLightValue"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(CallbackInfoReturnable var1) {
      if (Class362.field21.method44()) {
         var1.setReturnValue(1.0F);
      }

   }

   @Inject(
      method = {"shouldSideBeRendered"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(IBlockAccess var1, BlockPos var2, EnumFacing var3, CallbackInfoReturnable var4) {
      if (Class362.field21.method44() && XRayModule.field0.contains((Block)this)) {
         BlockPos var5 = var2.offset(var3, -1);
         if (!XRayModule.field9 && XRayModule.field7.contains(var5)) {
            var4.setReturnValue(true);
            return;
         }

         if (XRayModule.field10.method1() || !XRayModule.field9) {
            boolean var6 = false;
            EnumFacing[] var7 = EnumFacing.values();
            int var8 = var7.length;

            for(int var9 = 0; var9 < var8; ++var9) {
               EnumFacing var10 = var7[var9];
               BlockPos var11 = var5.add(var10.getDirectionVec());
               Block var12 = var1.getBlockState(var11).getBlock();
               if (var12 instanceof BlockAir || var12 instanceof BlockLiquid) {
                  if (!Oringo.field9.theWorld.isBlockLoaded(var11, false)) {
                     return;
                  }

                  var6 = true;
               }
            }

            if (var6) {
               var4.setReturnValue(true);
            }

            return;
         }

         var4.setReturnValue(true);
      }

   }

   @Inject(
      method = {"collisionRayTrace"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/util/Vec3;addVector(DDD)Lnet/minecraft/util/Vec3;"
)}
   )
   public void collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4, CallbackInfoReturnable var5) {
   }

   @Inject(
      method = {"canRenderInLayer"},
      at = {@At("HEAD")},
      cancellable = true,
      remap = false
   )
   public void method0(EnumWorldBlockLayer var1, CallbackInfoReturnable var2) {
      if (Class362.field33.method44() && NoFoliageModule.field0.contains(this) && !Class362.field33.aT_.method0(1)) {
         var2.setReturnValue(false);
      }

      if (Class362.field21.method44()) {
         var2.setReturnValue(var1 == (XRayModule.field0.contains((Block)this) ? EnumWorldBlockLayer.SOLID : EnumWorldBlockLayer.TRANSLUCENT));
      }

   }
}
