package oringo.mixin;

import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({NetworkManager.class})
public interface NetworkManagerAccessor {
   @Accessor("isEncrypted")
   void setEncrypted(boolean var1);
}
