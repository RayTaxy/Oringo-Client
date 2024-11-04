package oringo.mixin;

import com.google.common.collect.ImmutableSetMultimap;
import java.util.List;
import java.util.Set;
import map.Class362;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import oringo.module.NoFoliageModule;
import oringo.module.OptimizationsModule;

@Mixin({World.class})
public abstract class WorldMixin {
   @Shadow
   @Final
   public Profiler theProfiler;
   @Shadow
   protected Set activeChunkSet;

   @Shadow
   public abstract void updateEntity(Entity var1);

   @Shadow
   protected abstract boolean isAreaLoaded(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7);

   @Shadow
   public abstract ImmutableSetMultimap getPersistentChunks();

   @Redirect(
      method = {"setActivePlayerChunksAndCheckLight"},
      at = @At(
   value = "INVOKE",
   target = "Ljava/util/List;size()I",
   ordinal = 0
)
   )
   public int onSizeCheck(List var1) {
      if (OptimizationsModule.field0.method44() && !(this instanceof WorldServer)) {
         Minecraft var2 = Minecraft.getMinecraft();
         int var3 = (int)(var2.thePlayer.posX / 16.0D);
         int var4 = (int)(var2.thePlayer.posZ / 16.0D);
         int var5 = this.getRenderDistanceChunks();

         for(int var6 = -var5; var6 <= var5; ++var6) {
            for(int var7 = -var5; var7 <= var5; ++var7) {
               this.activeChunkSet.add(new ChunkCoordIntPair(var6 + var3, var7 + var4));
            }
         }

         return 0;
      } else {
         return var1.size();
      }
   }

   @Shadow
   protected abstract int getRenderDistanceChunks();

   @Shadow
   public abstract Chunk getChunkFromChunkCoords(int var1, int var2);

   @Shadow
   protected abstract boolean isChunkLoaded(int var1, int var2, boolean var3);

   @Redirect(
      method = {"rayTraceBlocks(Lnet/minecraft/util/Vec3;Lnet/minecraft/util/Vec3;ZZZ)Lnet/minecraft/util/MovingObjectPosition;"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/block/Block;canCollideCheck(Lnet/minecraft/block/state/IBlockState;Z)Z"
)
   )
   public boolean onCollisionCheck(Block var1, IBlockState var2, boolean var3) {
      return Class362.field33.method44() && !Class362.field33.aT_.method0(2) && NoFoliageModule.field0.contains(var1) ? false : var1.canCollideCheck(var2, var3);
   }
}
