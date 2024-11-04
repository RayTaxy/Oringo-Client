package oringo.mixin;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.event.Class468;

@Mixin(
   value = {MovementInputFromOptions.class},
   priority = 1
)
public abstract class MovementInputFromOptionsMixin extends MovementInputMixin {
   @Shadow
   @Final
   private GameSettings gameSettings;

   @Inject(
      method = {"updatePlayerMoveState"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/util/MovementInputFromOptions;sneak:Z",
   ordinal = 1
)},
      cancellable = true
   )
   public void method0(CallbackInfo var1) {
      Class468 var2 = new Class468(this.moveForward, this.moveStrafe, this.jump, this.sneak);
      var2.method7();
      this.jump = var2.method1();
      this.sneak = var2.I_();
      this.moveForward = var2.method2();
      this.moveStrafe = var2.G_();
   }
}
