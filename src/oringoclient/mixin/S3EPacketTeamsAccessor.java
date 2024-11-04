package oringo.mixin;

import net.minecraft.network.play.server.S3EPacketTeams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({S3EPacketTeams.class})
public interface S3EPacketTeamsAccessor {
   @Accessor("prefix")
   void setScoreName(String var1);

   @Accessor("suffix")
   void setScoreName2(String var1);
}
