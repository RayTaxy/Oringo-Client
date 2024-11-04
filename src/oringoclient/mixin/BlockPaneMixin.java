package oringo.mixin;

import map.Class95;
import net.minecraft.block.BlockPane;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({BlockPane.class})
public abstract class BlockPaneMixin extends BlockMixin {
   @SideOnly(Side.CLIENT)
   @Overwrite
   public boolean isOpaqueCube() {
      return Class95.method3();
   }
}
