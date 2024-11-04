package oringo.mixin;

import net.minecraft.network.play.client.C03PacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({C03PacketPlayer.class})
public interface C03PacketPlayerAccessor {
   @Accessor("z")
   void setZ(double var1);

   @Accessor
   void setOnGround(boolean var1);

   @Accessor
   void setYaw(float var1);

   @Accessor("x")
   void setX(double var1);

   @Accessor("y")
   void setY(double var1);

   @Accessor
   void setPitch(float var1);
}
