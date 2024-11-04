package oringo.mixin;

import net.minecraft.util.MovementInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({MovementInput.class})
public class MovementInputMixin {
   @Shadow
   public float moveStrafe;
   @Shadow
   public float moveForward;
   @Shadow
   public boolean sneak;
   @Shadow
   public boolean jump;
}
