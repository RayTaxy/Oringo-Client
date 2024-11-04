package oringo.event;

import map.Class513;
import map.Class7;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import org.lwjgl.input.Mouse;
import oringo.Oringo;

@Cancelable
public class Class148 extends Event {
   public static boolean method0(BlockPos var0, IBlockState var1) {
      AxisAlignedBB var2 = var1.getBlock().getCollisionBoundingBox(Oringo.field9.theWorld, var0, var1);
      if (var2 == null) {
         var2 = new AxisAlignedBB(var0, var0.add(1, 1, 1));
      }

      return Class290.method0().isBoundingBoxInFrustum(var2);
   }

   public static int method6() {
      return Mouse.getX() * Class7.field2.getScaledWidth() / Class513.field0.displayWidth;
   }
}
