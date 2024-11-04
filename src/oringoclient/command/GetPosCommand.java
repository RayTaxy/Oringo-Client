package oringo.command;

import map.Class12;
import map.Class208;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import oringo.module.FragHelperModule;
import oringo.module.IceFillHelperModule;

public class GetPosCommand extends Command {
   public String method1() {
      return null;
   }

   public GetPosCommand() {
      super("getpos");
   }

   public void method0(String[] var1) {
      if (field9.objectMouseOver != null && field9.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
         BlockPos var2 = field9.objectMouseOver.getBlockPos();
         Class12 var3 = FragHelperModule.method0(var2.getX(), var2.getZ());
         Class208 var4 = IceFillHelperModule.method0(var3.method1(), var3.method0());
         if (var4 != null) {
            method0(var4.method0(var2.add(-var3.method1(), 0, -var3.method0()), true).toString());
         }
      }

   }
}
