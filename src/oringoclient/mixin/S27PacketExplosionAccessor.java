package oringo.mixin;

import net.minecraft.network.play.server.S27PacketExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({S27PacketExplosion.class})
public interface S27PacketExplosionAccessor {
   @Accessor("field_149159_h")
   void setMotionZ(float var1);

   @Accessor("field_149152_f")
   void setMotionX(float var1);

   @Accessor("field_149153_g")
   void setMotionY(float var1);
}
