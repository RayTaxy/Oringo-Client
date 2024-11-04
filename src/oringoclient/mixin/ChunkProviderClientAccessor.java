package oringo.mixin;

import java.util.List;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ChunkProviderClient.class})
public interface ChunkProviderClientAccessor {
   @Accessor("chunkListing")
   List getChunkListing();
}
