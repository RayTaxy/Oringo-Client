package oringo.mixin;

import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(
   value = {EventBus.class},
   priority = 1001,
   remap = false
)
public class EventBusMixin {
   long field4;

   @Redirect(
      method = {"register(Ljava/lang/Object;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraftforge/fml/common/FMLLog;log(Lorg/apache/logging/log4j/Level;Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V"
)
   )
   public void oringo$fixSpam(Level var1, Throwable var2, String var3, Object[] var4) {
   }
}
