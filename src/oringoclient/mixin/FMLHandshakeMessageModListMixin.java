package oringo.mixin;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import map.Class362;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ModList.class})
public class FMLHandshakeMessageModListMixin {
   @Shadow
   private Map modTags;

   @Inject(
      method = {"<init>(Ljava/util/List;)V"},
      at = {@At("RETURN")}
   )
   public void method0(List var1, CallbackInfo var2) {
      if (!Minecraft.getMinecraft().isSingleplayer()) {
         this.modTags.remove("oringoclient");
         if (Class362.field19.method44() && Class362.field19.method5()) {
            this.modTags.entrySet().removeIf(FMLHandshakeMessageModListMixin::lambda$test$0);
         }

      }
   }

   private static boolean lambda$test$0(Entry var0) {
      return !Class362.field19.x_((String)var0.getKey()).method1();
   }
}
