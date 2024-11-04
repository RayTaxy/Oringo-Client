package oringo.mixin;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({GuiContainer.class})
public interface GuiContainerAccessor {
   @Invoker("getSlotAtPosition")
   Slot getSlotAtPos(int var1, int var2);
}
