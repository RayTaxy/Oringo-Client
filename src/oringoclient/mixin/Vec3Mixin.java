package oringo.mixin;

import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Vec3.class})
public class Vec3Mixin {
   @Shadow
   @Final
   public double yCoord;
   @Shadow
   @Final
   public double xCoord;
   @Shadow
   @Final
   public double zCoord;

   public boolean equals(Object var1) {
      if (!(var1 instanceof Vec3)) {
         return false;
      } else {
         Vec3 var2 = (Vec3)var1;
         return var2.xCoord == this.xCoord && var2.yCoord == this.yCoord && var2.zCoord == this.zCoord;
      }
   }
}
