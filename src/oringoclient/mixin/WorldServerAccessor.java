package oringo.mixin;

import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({WorldServer.class})
public interface WorldServerAccessor {
   @Invoker("saveLevel")
   void saveLevelInvoker();
}
