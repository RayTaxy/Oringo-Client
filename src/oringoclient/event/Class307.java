package oringo.event;

import java.awt.Color;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import oringo.Oringo;

@Cancelable
public class Class307 extends Event {
   public static boolean method0(BlockPos var0) {
      IBlockState var1 = Oringo.field9.theWorld.getBlockState(var0);
      return Class148.method0(var0, var1);
   }

   public static Color method0(Color var0, Color var1, float var2) {
      return Class290.method0(var0, var1, MathHelper.sin((float)((double)var2 * 3.141592653589793D / 2.0D)));
   }
}
