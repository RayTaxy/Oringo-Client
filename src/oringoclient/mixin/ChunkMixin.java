package oringo.mixin;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.event.Class16;
import oringo.event.Class420;
import oringo.module.TNTRunPingModule;

@Mixin({Chunk.class})
public abstract class ChunkMixin {
   @Inject(
      method = {"setBlockState"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void method0(BlockPos var1, IBlockState var2, CallbackInfoReturnable var3) {
      IBlockState var4 = this.getBlockState(var1);
      if (var2 != null && !var2.equals(var4) && MinecraftForge.EVENT_BUS.post(new Class16(var1, var2, var4))) {
         var3.setReturnValue((Object)null);
      }

   }

   @Inject(
      method = {"fillChunk"},
      at = {@At("RETURN")}
   )
   private void method0(byte[] var1, int var2, boolean var3, CallbackInfo var4) {
      (new Class420((Chunk)this)).method7();
   }

   @Inject(
      method = {"getBlockState"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void method0(BlockPos var1, CallbackInfoReturnable var2) {
      if (!TNTRunPingModule.bv_.isEmpty()) {
         if (TNTRunPingModule.bv_.stream().anyMatch(ChunkMixin::lambda$onBlockChange$0)) {
            var2.setReturnValue(Blocks.wool.getDefaultState());
            var2.cancel();
         }

      }
   }

   @Shadow
   public abstract IBlockState getBlockState(BlockPos var1);

   private static boolean lambda$onBlockChange$0(BlockPos var0, TNTRunPingModule.Class0 var1) {
      return var1.field0.equals(var0);
   }
}
