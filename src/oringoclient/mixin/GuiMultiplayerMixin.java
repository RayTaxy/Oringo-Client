package oringo.mixin;

import map.Class267;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({GuiMultiplayer.class})
public abstract class GuiMultiplayerMixin extends GuiScreenMixin {
   @Shadow
   private GuiButton btnEditServer;
   @Shadow
   private GuiButton btnDeleteServer;
   @Shadow
   private ServerData selectedServer;
   @Shadow
   private GuiButton btnSelectServer;
   @Shadow
   private ServerSelectionList serverListSelector;

   @Inject(
      method = {"actionPerformed"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void method0(GuiButton var1, CallbackInfo var2) {
      if (var1.id == 22) {
         this.mc.displayGuiScreen(new Class267());
         var2.cancel();
      }

   }

   @Overwrite
   public void createButtons() {
      this.buttonList.add(this.btnEditServer = new GuiButton(7, this.width / 2 - 154, this.height - 28, 70, 20, I18n.format("selectServer.edit", new Object[0])));
      this.buttonList.add(this.btnDeleteServer = new GuiButton(2, this.width / 2 - 74, this.height - 28, 70, 20, I18n.format("selectServer.delete", new Object[0])));
      this.buttonList.add(this.btnSelectServer = new GuiButton(1, this.width / 2 - 154, this.height - 52, 100, 20, I18n.format("selectServer.select", new Object[0])));
      this.buttonList.add(new GuiButton(4, this.width / 2 - 50, this.height - 52, 100, 20, I18n.format("selectServer.direct", new Object[0])));
      this.buttonList.add(new GuiButton(3, this.width / 2 + 4 + 50, this.height - 52, 100, 20, I18n.format("selectServer.add", new Object[0])));
      this.buttonList.add(new GuiButton(8, this.width / 2 + 4, this.height - 28, 70, 20, I18n.format("selectServer.refresh", new Object[0])));
      this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 76, this.height - 28, 75, 20, I18n.format("gui.cancel", new Object[0])));
      this.selectServer(this.serverListSelector.func_148193_k());
   }

   @Shadow
   public abstract void selectServer(int var1);
}
