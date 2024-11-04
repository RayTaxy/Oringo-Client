package oringo.mixin;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({PlayerControllerMP.class})
public interface PlayerControllerMPAccessor {
   @Accessor
   void setCurrentPlayerItem(int var1);

   @Accessor
   void setBlockHitDelay(int var1);

   @Accessor
   void setCurBlockDamageMP(float var1);

   @Accessor
   float getCurBlockDamageMP();

   @Accessor
   BlockPos getCurrentBlock();

   @Accessor
   int getBlockHitDelay();

   @Accessor
   int getCurrentPlayerItem();
}
