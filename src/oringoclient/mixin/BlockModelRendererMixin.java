package oringo.mixin;

import map.Class362;
import map.Class95;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({BlockModelRenderer.class})
public abstract class BlockModelRendererMixin {
   @Shadow
   public abstract boolean renderModelAmbientOcclusion(IBlockAccess var1, IBakedModel var2, Block var3, BlockPos var4, WorldRenderer var5, boolean var6);

   @Shadow
   public abstract boolean renderModelStandard(IBlockAccess var1, IBakedModel var2, Block var3, BlockPos var4, WorldRenderer var5, boolean var6);

   @Overwrite
   public boolean renderModel(IBlockAccess var1, IBakedModel var2, IBlockState var3, BlockPos var4, WorldRenderer var5, boolean var6) {
      if (Class95.method3() && (var3.getBlock() == Blocks.stained_glass || var3.getBlock() == Blocks.stained_glass_pane)) {
         return false;
      } else {
         boolean var7 = Minecraft.isAmbientOcclusionEnabled() && var3.getBlock().getLightValue() == 0 && var2.isAmbientOcclusion() || Class362.field21.method44();

         try {
            Block var8 = var3.getBlock();
            return var7 ? this.renderModelAmbientOcclusion(var1, var2, var8, var4, var5, var6) : this.renderModelStandard(var1, var2, var8, var4, var5, var6);
         } catch (Throwable var11) {
            CrashReport var9 = CrashReport.makeCrashReport(var11, "Tesselating block model");
            CrashReportCategory var10 = var9.makeCategory("Block model being tesselated");
            CrashReportCategory.addBlockInfo(var10, var4, var3);
            var10.addCrashSection("Using AO", var7);
            throw new ReportedException(var9);
         }
      }
   }

   @Shadow
   public abstract boolean renderModel(IBlockAccess var1, IBakedModel var2, IBlockState var3, BlockPos var4, WorldRenderer var5);
}
