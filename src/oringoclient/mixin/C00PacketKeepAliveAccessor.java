package oringo.mixin;

import net.minecraft.network.play.client.C00PacketKeepAlive;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({C00PacketKeepAlive.class})
public interface C00PacketKeepAliveAccessor {
   @Accessor
   void setKey(int var1);
}
