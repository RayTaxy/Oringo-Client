package oringo.mixin;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({Minecraft.class})
public interface MinecraftAccessor {
   @Accessor("leftClickCounter")
   void setClickDelay(int var1);

   @Accessor("rightClickDelayTimer")
   int getPlaceDelay();

   @Accessor
   List getDefaultResourcePacks();

   @Accessor("rightClickDelayTimer")
   void setPlaceDelay(int var1);

   @Accessor
   Timer getTimer();
}
