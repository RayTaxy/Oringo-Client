package oringo.mixin;

import net.minecraft.client.gui.GuiDisconnected;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({GuiDisconnected.class})
public interface GuiDisconnectedAccessor {
   @Accessor
   int getField_175353_i();
}
