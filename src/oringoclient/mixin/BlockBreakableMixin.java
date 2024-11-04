package oringo.mixin;

import map.Class95;
import net.minecraft.block.BlockBreakable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({BlockBreakable.class})
public abstract class BlockBreakableMixin extends BlockMixin {
   @SideOnly(Side.CLIENT)
   @Overwrite
   public boolean isOpaqueCube() {
      return Class95.method3();
   }
}
