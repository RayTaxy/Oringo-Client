package oringo.mixin;

import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({GuiMainMenu.class})
public abstract class GuiMainMenuMixin extends GuiScreenMixin {
}
