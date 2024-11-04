package oringo.mixin;

import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({S3BPacketScoreboardObjective.class})
public interface S3BPacketScoreboardObjectiveAccessor {
   @Accessor("objectiveValue")
   void setDisplayName(String var1);
}
