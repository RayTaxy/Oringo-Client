package oringo.mixin;

import net.minecraft.item.ItemSword;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ItemSword.class})
public interface ItemSwordAccessor {
   @Accessor
   float getAttackDamage();
}
