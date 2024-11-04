package oringo.mixin;

import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.event.Class177;

@Mixin(
   value = {GuiChat.class},
   priority = 1
)
public abstract class GuiChatMixin extends GuiScreenMixin {
   @Inject(
      method = {"mouseClicked"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void method0(int var1, int var2, int var3, CallbackInfo var4) {
      if (MinecraftForge.EVENT_BUS.post(new Class177.Class3(var1, var2, var3))) {
         var4.cancel();
      }

   }

   @Inject(
      method = {"onGuiClosed"},
      at = {@At("RETURN")}
   )
   public void method1(CallbackInfo var1) {
      MinecraftForge.EVENT_BUS.post(new Class177.Class0());
   }

   protected void mouseReleased(int var1, int var2, int var3) {
      MinecraftForge.EVENT_BUS.post(new Class177.Class4(var1, var2, var3));
   }

   @Inject(
      method = {"drawScreen"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void method2(int var1, int var2, float var3, CallbackInfo var4) {
      if (MinecraftForge.EVENT_BUS.post(new Class177.Class1(var1, var2))) {
         var4.cancel();
      }

   }

   @Inject(
      method = {"keyTyped"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void method3(char var1, int var2, CallbackInfo var3) {
      if (MinecraftForge.EVENT_BUS.post(new Class177.Class2(var2, var1))) {
         var3.cancel();
      }

   }
}
