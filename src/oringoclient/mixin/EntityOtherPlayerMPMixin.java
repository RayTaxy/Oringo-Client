package oringo.mixin;

import map.Class363;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityOtherPlayerMP.class})
public abstract class EntityOtherPlayerMPMixin extends AbstractClientPlayerMixin implements Class363 {
   private int field38;
   private int field39;

   public int method15() {
      return this.field38;
   }

   @Inject(
      method = {"onUpdate"},
      at = {@At("HEAD")}
   )
   public void oringo$onUpdate(CallbackInfo var1) {
      if (!this.isInvisible()) {
         ++this.field38;
      }

      NetHandlerPlayClient var2 = Minecraft.getMinecraft().getNetHandler();
      if (var2 != null && var2.getPlayerInfo(this.entityUniqueID) != null) {
         ++this.field39;
      }

   }

   public int method16() {
      return this.field39;
   }
}
