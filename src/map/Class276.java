package map;

import net.minecraft.world.chunk.Chunk;
import oringo.mixin.ChunkAccessor;

public class Class276 {
   public static boolean method0(Chunk var0) {
      return ((ChunkAccessor)var0).getHasEntities();
   }
}
