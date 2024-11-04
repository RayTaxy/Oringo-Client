package oringo.mixin;

import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.util.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({C02PacketUseEntity.class})
public interface C02PacketUseEntityAccessor {
   @Accessor
   void setEntityId(int var1);

   @Accessor
   void setAction(Action var1);

   @Accessor("hitVec")
   void setVec(Vec3 var1);
}
