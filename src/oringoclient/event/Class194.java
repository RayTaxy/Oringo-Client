package oringo.event;

import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class Class194 extends Event {
   public final BlockPos field0;
   public AxisAlignedBB field1;
   public final Block field2;

   public Class194(Block var1, AxisAlignedBB var2, BlockPos var3) {
      this.field1 = var2;
      this.field2 = var1;
      this.field0 = var3;
   }
}
