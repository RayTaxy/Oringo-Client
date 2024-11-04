package oringo.mixin;

import net.minecraft.client.resources.Locale;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({Locale.class})
public class LocaleMixin {
   @Redirect(
      method = {"formatMessage"},
      at = @At(
   value = "INVOKE",
   target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;"
)
   )
   public String optimize(String var1, Object[] var2) {
      return var2.length == 0 ? var1 : String.format(var1, var2);
   }
}
