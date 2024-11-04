package oringo.mixin;

import map.Class80;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.inventory.ICrafting;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({GuiRepair.class})
public abstract class GuiRepairMixin extends GuiContainerMixin {
   @Shadow
   private GuiTextField nameField;

   @Overwrite
   public void initGui() {
      super.initGui();
      Keyboard.enableRepeatEvents(true);
      int var1 = (this.width - this.xSize) / 2;
      int var2 = (this.height - this.ySize) / 2;
      this.nameField = new Class80(0, this.fontRendererObj, var1 + 62, var2 + 24, 103, 12);
      this.nameField.setTextColor(-1);
      this.nameField.setDisabledTextColour(-1);
      this.nameField.setEnableBackgroundDrawing(false);
      this.nameField.setMaxStringLength(300);
      this.inventorySlots.removeCraftingFromCrafters((ICrafting)this);
      this.inventorySlots.onCraftGuiOpened((ICrafting)this);
   }
}
