package oringo.mixin;

import map.Class362;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import oringo.module.ClickGuiModule;

@Mixin(
   value = {Display.class},
   remap = false,
   priority = Integer.MIN_VALUE
)
public class DisplayMixin {
   @ModifyVariable(
      method = {"setTitle"},
      at = @At("HEAD"),
      ordinal = 0,
      argsOnly = true
   )
   private static String mod(String var0) {
      return !ClickGuiModule.field10.F_() && Class362.method2() ? ClickGuiModule.field10.method1().replaceAll("\\$ign", Minecraft.getMinecraft().getSession().getUsername()) : var0;
   }
}
