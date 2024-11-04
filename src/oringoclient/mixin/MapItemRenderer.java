package oringo.mixin;

import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Gui.class})
public abstract class MapItemRenderer {
   @Shadow
   public static void drawRect(int var0, int var1, int var2, int var3, int var4) {
   }

   @Shadow
   protected abstract void drawGradientRect(int var1, int var2, int var3, int var4, int var5, int var6);

   @Shadow
   public abstract void drawTexturedModalRect(int var1, int var2, int var3, int var4, int var5, int var6);
}
