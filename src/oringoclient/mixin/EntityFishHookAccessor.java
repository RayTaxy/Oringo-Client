package oringo.mixin;

import net.minecraft.entity.projectile.EntityFishHook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({EntityFishHook.class})
public interface EntityFishHookAccessor {
   @Accessor("ticksCatchable")
   int getTicksCatchable();

   @Accessor("inGround")
   boolean inGround();
}
