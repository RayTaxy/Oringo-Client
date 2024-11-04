package oringo.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class Class16 extends Event {
   public final IBlockState field0;
   public final BlockPos cB_;
   public final IBlockState field2;

   public Class16(BlockPos var1, IBlockState var2, IBlockState var3) {
      this.cB_ = var1;
      this.field2 = var2;
      this.field0 = var3;
   }
}
