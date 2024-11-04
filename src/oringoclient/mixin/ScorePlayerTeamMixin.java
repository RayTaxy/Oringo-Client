package oringo.mixin;

import map.Class362;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.module.PVPInfoModule;

@Mixin({ScorePlayerTeam.class})
public class ScorePlayerTeamMixin {
   @Inject(
      method = {"formatPlayerName"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private static void method0(Team var0, String var1, CallbackInfoReturnable var2) {
      if (var1.length() >= 3) {
         PVPInfoModule var3 = Class362.field29;
         if (var3.method44()) {
            if (var3.field0.containsKey(var1)) {
               var2.setReturnValue(var3.field0.get(var1));
            }

         }
      }
   }
}
