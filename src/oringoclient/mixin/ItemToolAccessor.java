package oringo.mixin;

import net.minecraft.item.ItemTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ItemTool.class})
public interface ItemToolAccessor {
   @Accessor
   float getDamageVsEntity();

   @Accessor("toolClass")
   String getToolClass();
}
