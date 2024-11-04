package oringo.mixin;

import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({S08PacketPlayerPosLook.class})
public interface S08PacketPlayerPosLookAccessor {
   @Accessor("y")
   void setY(double var1);

   @Accessor("pitch")
   void setPitch(float var1);

   @Accessor("x")
   void setX(double var1);

   @Accessor("z")
   void setZ(double var1);

   @Accessor("yaw")
   void setYaw(float var1);
}
