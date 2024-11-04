package oringo.mixin;

import net.minecraft.network.play.server.S12PacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({S12PacketEntityVelocity.class})
public interface S12PacketEntityVelocityAccessor {
   @Accessor
   void setMotionY(int var1);

   @Accessor
   void setMotionZ(int var1);

   @Accessor
   void setMotionX(int var1);
}
