package oringo.mixin;

import net.minecraft.network.play.client.C0DPacketCloseWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({C0DPacketCloseWindow.class})
public interface C0DPacketCloseWindowAccessor {
   @Accessor
   int getWindowId();
}
