package oringo.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.event.Class533;

@Mixin(
   value = {GuiIngame.class},
   priority = 1001
)
public abstract class GuiIngameMixin extends Gui {
   @Shadow
   @Final
   protected static ResourceLocation widgetsTexPath;
   @Shadow
   @Final
   protected Minecraft mc;

   @Inject(
      method = {"renderScoreboard"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void renderScoreboard(ScoreObjective var1, ScaledResolution var2, CallbackInfo var3) {
      if ((new Class533(var1, var2)).method7()) {
         var3.cancel();
      }

   }

   @Shadow
   protected abstract void renderHotbarItem(int var1, int var2, int var3, float var4, EntityPlayer var5);

   @Shadow
   public abstract FontRenderer getFontRenderer();
}
