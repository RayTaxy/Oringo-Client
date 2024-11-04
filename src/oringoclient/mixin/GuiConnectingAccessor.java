package oringo.mixin;

import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({GuiConnecting.class})
public interface GuiConnectingAccessor {
   @Accessor("networkManager")
   NetworkManager getNetworkManager();
}
