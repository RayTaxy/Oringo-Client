package oringo.mixin;

import net.minecraft.client.network.NetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({NetHandlerPlayClient.class})
public interface NetHandlerPlayClientAccessor {
   @Accessor("doneLoadingTerrain")
   boolean isDoneLoadingTerrain();

   @Accessor("doneLoadingTerrain")
   void setDoneLoadingTerrain(boolean var1);
}
